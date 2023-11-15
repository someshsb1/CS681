package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {

    FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        super(parent, name, size, creationTime, target, lock);
        this.target = target;
    }

    public void setTarget(FSElement target) {
        lock.lock();
        try {
            this.target = target;
        } finally {
                lock.unlock();
        }
    }

    public FSElement getTarget() {
        lock.lock();
        try {
            return target;
        } finally {
                lock.unlock();
        }
    }

    public boolean isLink() {
        lock.lock();
        try {
            return true;
        } finally {
                lock.unlock();
        }
    }
}
