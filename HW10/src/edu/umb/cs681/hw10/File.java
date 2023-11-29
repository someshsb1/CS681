package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class File extends FSElement {

    public File(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        super(parent, name, size, creationTime, target, lock);
        this.parent = parent;
    }

    public boolean isFile() {
        return true; //read-only
    }
    
}
