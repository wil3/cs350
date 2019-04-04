## Discussion April 02 2019	
It is important in an academic setting to understand the basic building blocks
of how fundamental programming structures work. However in a professional
setting it is preferred to use built in methods and structures that accomplish this for
you. There is no reason to reinvent the wheel. This week we will review the Java techniques to provide mutual exclusion that should be used in practise as well as other thread safe methods. Additionally we will discuss deadlocks, walk through an example of one occurring in a Java application and then see how we can diagnose our application to detect deadlocks.

### Agenda

1) Synchronize key word vs semaphore mutex

Synchronize key word in Java allows you to synchronize an entire method or an object. With semaphores as a mutex,

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
For the mutex example, if we change the order of the try/catch/finally will it
result in the same out come? There are multiple ways to accomplish the same
thing, some discussion on the topic can be read [here](https://stackoverflow.com/questions/12104978/need-to-semaphore-relase-if-semaphore-acquire-gets-interruptedexception
).

**Note: A major difference between these two methods are that synchronized
methods and blocks nest!!!** See
[here](https://www.oracle.com/technetwork/articles/javase/index-140767.html)
for more details. This can be demonstrated in the accompanying code. 

Additional Reading:
* https://stackoverflow.com/questions/5291041/is-there-a-mutex-in-java
* https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
* https://www.oracle.com/technetwork/articles/javase/index-140767.html
* https://stackoverflow.com/questions/12104978/need-to-semaphore-relase-if-semaphore-acquire-gets-interruptedexception



2)  Other Concurrent Utilities in Java
A list of all other classes that are thread same can be viewed [here](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/package-summary.html).

3) What are deadlocks?

**Deadlocks**
>Permanent blocking of a set of processes that either compete for system resources or communicated with each other.

![https://i.stack.imgur.com/3XVzK.png](https://i.stack.imgur.com/3XVzK.png)


4) Deadlock Example

See code which is originally from [here](https://www.geeksforgeeks.org/deadlock-in-java-multithreading/). This link also has some additional reading on the subject.


5) Detecting deadlocks in Java

[jcmd](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr006.html) utility is used to send diagnostic command requests to the JVM.
```
jcmd <pid> Thread.print
```
The first thing we must identity is the process ID of our Java application.
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
