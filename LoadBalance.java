package loadBalance;

import java.util.ArrayList;

/*
 * Author: Andrew Lytle
 * May 15, 2013
 * 
 * Disclaimer: This application was created as an academic exercise, and is
 * 		not intended to be production stable or scalable past a few thousand connections.
 * 
 */

public class LoadBalance {
	
	// servers list is the list of servers we are going to load balance across public static ArrayList<ServerObject> servers = new ArrayList<ServerObject>();

	public static void main(String[] args) {
		// Read Config file, construct listener with given options
		// Static data includes server addresses
		
		// Hard code the servers in for now. TODO: Read serverlist file
		servers.add(new ServerObject(80, "en.wikipedia.org", 4000, 1.00));
		servers.add(new ServerObject(80, "google.com", 4000, 1.00));
		servers.add(new ServerObject(80, "facebook.com", 4000, 1.00));
		servers.add(new ServerObject(80, "reddit.com", 4000, 1.00));
		servers.add(new ServerObject(80, "yahoo.com", 4000, 1.00));
		servers.add(new ServerObject(80, "wsu.edu", 4000, 1.00));
		servers.add(new ServerObject(80, "eecs.wsu.edu", 4000, 1.00));
		servers.add(new ServerObject(80, "ebay.com", 4000, 1.00));
		servers.add(new ServerObject(80, "amazon.com", 4000, 1.00));
		servers.add(new ServerObject(80, "aws.amazon.com", 4000, 1.00));
		
		
		
		// Construct listener and select port here
		LoadBalanceListener listener = new LoadBalanceListener(15100);
		
		// Pass in configuration options here
		listener.configure();
		
		Thread listenerThread = new Thread(listener);
		listenerThread.run();
		
		
		
		
			

	}

}
