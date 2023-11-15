package edu.umb.cs681.hw10;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {

    private LinkedList<Directory> rootDirs;
    private static AtomicReference<FileSystem> instance = null; //using AtomicReference to implement singleton class.
    private static AtomicBoolean done = new AtomicBoolean(false); //implementing AtomicBoolean flag to terminated 10+ threads.
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        rootDirs = new LinkedList<Directory>();
    }

    //introduced lock/unlock in try-finally block to prevent race conditions.
    private static AtomicReference<FileSystem> getFileSystem() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new AtomicReference<FileSystem>(new FileSystem()); //instance created if instance = null
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        lock.lock();
        try {
            return rootDirs;
        } finally {
            lock.unlock();
        }
    }

    public void appendRootDir(Directory prjRoot) {
        lock.lock();
        try {
            rootDirs.add(prjRoot);
        } finally {
            lock.unlock();
        }
    }

    public void setAtomicFlag() {
        done.set(true);
    }

    public static void main(String args[]) throws InterruptedException {
        Runnable run = () -> {
            AtomicReference<FileSystem> fs = FileSystem.getFileSystem(); //only one instance is created using AtomicReference
             while (!done.get()) { // 2 steps, thread-safe
                System.out.println("Only one instance in use: " + fs.get());
            }
        };

        Thread[] thread = new Thread[20]; //Running 10+ extra threads toaccess fs data
        for (int i = 0; i < 20; i++) {
            thread[i] = new Thread(run);
            thread[i].start();
        }


        done.set(true); // 2 steps, thread-safe

        for (Thread threads : thread) {
            threads.join();
        }

    }

}
