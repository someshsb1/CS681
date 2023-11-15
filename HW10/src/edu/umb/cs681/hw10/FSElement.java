package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class FSElement {

    protected ReentrantLock lock = new ReentrantLock();
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    private Directory parent;
    FSElement target;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
        this.target = target;
    }

    public String getName() {
        lock.lock();
        try {
            return this.name;
        } finally {
                lock.unlock();
        }
    }

    public void setName(String name) {
        lock.lock();
        try {
            this.name = name;
        } finally {
                lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return this.size;
        } finally {
                lock.unlock();
        }
    }

    public void setSize(int size) {
        lock.lock();
        try {
            this.size = size;
        } finally {
                lock.unlock();
        }
    }

    public Directory getParent() {
        lock.lock();
        try {
            return parent;
        } finally {
                lock.unlock();
        }
    }

    public LocalDateTime getCreationTime() {
        lock.lock();
        try {
            return creationTime;
        } finally {
                lock.unlock();
        }
    }

    public void setCreationTime(LocalDateTime creationTime) {
        lock.lock();
        try {
        this.creationTime = creationTime;
        } finally {
                lock.unlock();
        }
    }

    public boolean isDirectory() {
        lock.lock();
        try {
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean isFile() {
        lock.lock();
        try {
            return false;
        } finally {
                lock.unlock();
        }
    }

    public static void main(String args[]) {
        System.out.println("FSElement implemented");
    }
}
