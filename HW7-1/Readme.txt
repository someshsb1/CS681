For HW 7-1

Revised RunnableCancellablePrimeGenerator to be thread-safe.
– Added a ReentrantLock 'lock' as a data field to guard the shared variable 'done'
– Revised generatePrimes() and setDone() methods to access the variable 'done' with the lock making sure to guard the shared variable
by surrounding the login in both methods try-finally blocks with lock() and unlock().
