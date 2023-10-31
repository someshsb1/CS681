package edu.umb.cs681.hw8;

import java.time.LocalDateTime;

public class File extends FSElement {

    Directory parent;

    public File(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime, target);
        this.parent = parent;
    }

    public boolean isFile() {
        return true;
    }
    
}
