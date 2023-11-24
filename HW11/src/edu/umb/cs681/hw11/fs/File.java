package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;


public class File extends FSElement {

    private static ReentrantLock lock = new ReentrantLock();
    
    public File(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime, target);
    }
    public void accept(FSVisitor v) {
        lock.lock();
        try {
            v.visit(this);
        } finally {
            lock.unlock();
        }
    }

    public boolean isFile() {
        return true;
    }

}

