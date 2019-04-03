package edu.bu.cs350.discussions.d10.deadlocks;
//From https://www.geeksforgeeks.org/deadlock-in-java-multithreading/
public class Util {

    // Util class to sleep a thread 
    static void sleep(long millis) 
    { 
        try
        { 
            Thread.sleep(millis); 
        } 
        catch (InterruptedException e) 
        { 
            e.printStackTrace(); 
        } 
    } 
}
