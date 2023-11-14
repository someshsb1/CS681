For HW10

Made all other classes to be thread-safe.
– Defined a ReentrantLock as a data field in FSElement
– Used it in each subclasses of FSElement to perform thread
synchronization.
• Revised FileSystem to use AtomicReference for implementing a singleton class.
• Processed 10+ extra threads to access fs data.
• Implemented 2-step termination to have the main thread terminate those 10+ threads.
– Used AtomicBoolean to implement a flag.
