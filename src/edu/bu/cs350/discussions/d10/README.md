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

```
jcmd 15985 Thread.print
```
