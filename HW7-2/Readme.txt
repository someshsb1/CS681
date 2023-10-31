For HW 7-2

Implemented RunnableCancellablePrimeFactorizer by extending RunnablePrimeFactorizer.
– Implemented flag-based thread termination
– Added a flag variable done
– Added a ReentrantLock to guard the shared variable done
– Revised generatePrimeFactors() and setDone() to access done with the lock
– Called unlock() in a finally block.
