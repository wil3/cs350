package edu.bu.cs350.discussions.d10.deadlocks;

//https://www.geeksforgeeks.org/deadlock-in-java-multithreading/
public class Thread1 extends Thread {
	 private Shared s1; 
	    private Shared s2; 
	  
	    // constructor to initialize fields 
	    public Thread1(Shared s1, Shared s2) 
	    { 
	        this.s1 = s1; 
	        this.s2 = s2; 
	    } 
	  
	    // run method to start a thread 
	    @Override
	    public void run() 
	    { 
	        // taking object lock of s1 enters 
	        // into test1 method 
	        s1.test1(s2); 
	    } 
}
