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
            return this.name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public int getSize() {
            return this.size;
    }

    public void setSize(int size) {
            this.size = size;
    }

    public Directory getParent() {
            return parent;
    }

    public LocalDateTime getCreationTime() {
            return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isDirectory() {
            return false;
    }

    public boolean isFile() {
            return false;
    }

    public static void main(String args[]) {
        System.out.println("FSElement implemented");
    }
}
