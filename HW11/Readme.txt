HW 11: Multi-threaded Visitors

Modified accept()thread-safe in Directory, File and Link classes.
• Prepared 5 trees/drives.
• Have one thread each for crawling 5 drives with an instance of FileCrawlingVisitor.

Placed FileCrawlingVisitor instances in a ThreadLocal as each FileCrawlingVisitor instance is used only by a particular thread.

After crawling particular tree/drive, each thread adds the found files to a shared list. idetifiedFilesSharedList.

The main thread terminates the 5 threads in 2 steps, used a volatile flag done.

Post thread termination, crawling stops and adds the set of file identified so far in the shared list.
