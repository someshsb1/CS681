package edu.umb.cs681.hw8;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {

    private LinkedList<Directory> rootDirs;
    private static FileSystem instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        rootDirs = new LinkedList<Directory>();
    }

    //introduced lock/unlock in try-finally block to prevent race conditions.
    private static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new FileSystem();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }

    public void appendRootDir(Directory prjRoot) {
        rootDirs.add(prjRoot);
    }

    public static void main(String args[]) {
        Runnable run = () -> {
            FileSystem fs = FileSystem.getFileSystem(); //only one instance is created
            System.out.println("Only one instance in use: " + fs);
        };

        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        Thread t3 = new Thread(run);
        Thread t4 = new Thread(run);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
