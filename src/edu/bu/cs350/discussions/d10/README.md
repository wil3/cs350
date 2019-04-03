## Discussion April 02 2019	

This week we will review the Java classes that can provide mutual exclusion and also thread pools that should be used in practise. Additionally we will discuss how thread pools can result in a deadlock.

### Agenda

1) Synchronize vs Mutex

Synchronize key word in Java allows you to synchronize an entire method or an object. With semaphmores as a mutex,

```Java
try {
  mutex.acquire();
  try {
    // do something
  } finally {
    mutex.release();
  }
} catch(InterruptedException ie) {
  // ...
}
```

Vs.

```Java
Object lock = new Object()
synchronized (lock){
    // do something
}
```
The built in synchronized keyword will provide cleaner more readable code as
well as being less error prone.  In most cases this is preferred.  

Additional Reading:
* https://stackoverflow.com/questions/5291041/is-there-a-mutex-in-java
* https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
* https://www.oracle.com/technetwork/articles/javase/index-140767.html



2)  Other Concurrent Utilities in Java


3) What are deadlocks?

**Deadlocks**
>Permanent blocking of a set of processes that either compete for system resources or communicated with each other.

![https://i.stack.imgur.com/3XVzK.png](https://i.stack.imgur.com/3XVzK.png)


Detecting deadlocks in Java,

[jcmd](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr006.html) utility is used to send diagnostic command requests to the JVM.
```
jcmd <pid> Thread.print
```
The first thing we must idenfity is the process ID of our Java application.
There are multiple ways to do this but one quick way in Linux is using the `ps`
command. For example,

```
ps -aux | grep java
```
This command will pipe the `ps` output to the grep command. Grep allows us to
search and filter only results that include the word `java`. From this output
we will take the output of the second value which will be the integer
representing the process ID. (Note: to prove this to your self type `ps -aux |
grep less` which will show the column headers). Once we have the
process ID we can now execute the command, for example,
```
jcmd 15985 Thread.print
```
