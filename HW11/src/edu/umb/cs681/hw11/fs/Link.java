package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;


public class Link extends FSElement {
    
    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
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

    public boolean isLink() {
        return true;
    }

    public FSElement getTarget() {
        lock.lock();
        try {
        return this.target;
        } finally {
            lock.unlock();
        }
    }

    public void setTarget(FSElement target) {
        lock.lock();
        try {
        this.target = target;
        } finally {
            lock.unlock();
        }
    }

}
