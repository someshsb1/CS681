package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;


public class File extends FSElement {
    
    public File(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        super(parent, name, size, creationTime, target, lock);
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

