package edu.bu.cs350.discussions.d9;

import java.util.concurrent.Semaphore;

/**
 * Modified Pool from https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html
 *
 */
class Pool {
	   private final Semaphore available;
	   protected Connection[] connections;
	   protected boolean[] used;
	   private final int poolSize;

	   public Pool(int poolSize) {
		   this.poolSize = poolSize;
		   available = new Semaphore(poolSize, true);
		   used = new boolean[poolSize];

		   // Init the contensts of the pool, essentially caching the connections
		   // Could be replaced with any type of resource
		   connections = new Connection[poolSize];
		   for (int i=0; i<poolSize; i++) {
			   connections[i] = new Connection("Connection-" + i);
		   }
	   }
	   
	   public Connection getItem() throws InterruptedException {
	     available.acquire();
	     return getNextAvailableItem();
	   }

	   public void putItem(Connection x) {
	     if (markAsUnused(x))
	       available.release();
	   }

	   protected synchronized Connection getNextAvailableItem() {
	     for (int i = 0; i < this.poolSize; ++i) {
	       if (!used[i]) {
	          used[i] = true;
	          return connections[i];
	       }
	     }
	     return null; // not reached because one will become available since this is only called after acquire
	   }

	   protected synchronized boolean markAsUnused(Object item) {
	     for (int i = 0; i < this.poolSize; ++i) {
	       if (item == connections[i]) {
	          if (used[i]) {
	            used[i] = false;
	            return true;
	          } else
	            return false;
	       }
	     }
	     return false;
	   }

	 }