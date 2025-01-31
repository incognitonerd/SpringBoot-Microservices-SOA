package com.sandbox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JourneySimulator implements Runnable {

	@Autowired
	private JmsTemplate template;

	private ExecutorService threadPool;

	public void run() 
	{
		try 
		{
			this.runVehicleSimulation();
		} 
		catch (InterruptedException e) 
		{
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * For each vehicle, a thread is started which simulates a journey for that vehicle. 
	 * When all vehicles have completed, we start all over again. 
	 * @throws InterruptedException 
	 */
	public void runVehicleSimulation() throws InterruptedException 
	{
		Map<String, List<String>> reports = setUpData();
		threadPool = Executors.newCachedThreadPool();		
		boolean stillRunning = true;
		while (stillRunning)
		{
			List<Callable<Object>> calls = new ArrayList<>();

			for (String vehicleName : reports.keySet())
			{
				// kick off a message sending thread for this vehicle.
				calls.add(new Journey(vehicleName, reports.get(vehicleName), template));
			}
			
			threadPool.invokeAll(calls);
			if (threadPool.isShutdown())
			{
				stillRunning = false;
				System.out.println("Asked to finish!");
			}
			else
			{
				System.out.println("All journeys complete - starting again");
			}
		}
	}

	/**
	 * Read the data from the resources directory - should work for an executable Jar as
	 * well as through direct execution
	 */
	private Map<String, List<String>> setUpData() 
	{
		Map<String, List<String>> reports = new HashMap<>();
		PathMatchingResourcePatternResolver path = new PathMatchingResourcePatternResolver();

		try
		{
			for (Resource nextFile : path.getResources("tracks/*"))
			{
				URL resource = nextFile.getURL();
				File f = new File(resource.getFile()); 
				String vehicleName = f.getName();
				InputStream is = PositionSimulatorApplication.class.getResourceAsStream("/tracks/" + f.getName());
				try (Scanner sc = new Scanner(is))
				{
					List<String> thisVehicleReports = new ArrayList<>();
					while (sc.hasNextLine())
					{
						String nextReport = sc.nextLine();
						thisVehicleReports.add(nextReport);
					}
					reports.put(vehicleName,thisVehicleReports);
				}
			}
			return Collections.unmodifiableMap(reports);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public void finish() 
	{
		threadPool.shutdownNow();
	}


}
