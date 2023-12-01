package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class FSElement {

    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;
    protected FSElement target;
    protected ReentrantLock lock = new ReentrantLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public int getSize() {
        lock.lock();
        try {
            return size;
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

    public boolean isDirectory() {
        return false;
    }

    public boolean isFile() {
        return false;
    }

    public boolean isLink() {
        return false;
    }
    
    public Directory getParent() {
        lock.lock();
        try {
        return parent;
        } finally {
            lock.unlock();
        }
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

    public void accept(FSVisitor v) {

    }

    public static void main(String args[]) {

        System.out.println("File System Implemented with Visitor Design Pattern");
    }
}
