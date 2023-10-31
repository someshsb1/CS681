For HW 8

Revised FileSystem.getFileSystem()to be thread-safe –
Implemented a lock/unlock in FileSystem class.
Used the lock in getInstance() to guard the instance in data field with try-finally blocks
Executed 4 threads to call getInstance() & Made sure that only one instance was created for the 4 task/threads.
