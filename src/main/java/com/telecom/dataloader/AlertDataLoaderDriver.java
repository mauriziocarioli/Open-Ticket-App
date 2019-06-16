/**
 * 
 */
package com.telecom.dataloader;

public class AlertDataLoaderDriver {

	/**
	 */
	public static void main(String[] args) {

		System.out.println("Telecom Device Alerts - Automated Ticket Opening");
		System.out.println("********************************");
				
		AlertDataLoaderThread dataLoader = new AlertDataLoaderThread();
		dataLoader.run();
		
	}

}
