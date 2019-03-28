package edu.bu.cs350.discussions.d9;

import java.util.Random;

class ConnectionPoolExample  extends Thread {
	Pool p;
	public ConnectionPoolExample(Pool p) {
		this.p = p;
	}
	@Override
	public void run() {
		try {
			Connection conn = p.getItem();
			System.out.println("Thread " + this.getName() + " Obtaining connection " + conn.getName() + " and doing work...");
			//Do some work
			Thread.sleep((long)(10000 * new Random().nextDouble()));
			p.putItem(conn);
			System.out.println("\t Thread " + this.getName() + " Releasing connection " + conn.getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//Play with these values
		int poolSize = 30;
		int numThreads = 100;

		//Create our pool
		Pool p = new Pool(poolSize);
		Thread[] threads = new Thread[numThreads];
		for (int i=0; i<numThreads; i++) {
			//Init the threads and give them access to the pool
			threads[i] = new ConnectionPoolExample(p);
			threads[i].start();
		}
		//Join em all up
		for (int i=0; i<numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("All threads joined.");
	}
}