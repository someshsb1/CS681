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
            this.target = target;
    }

    public FSElement getTarget() {
            return target;
    }

    public boolean isLink() {
            return true;
    }
}
