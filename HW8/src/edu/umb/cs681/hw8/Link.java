package edu.umb.cs681.hw8;

import java.time.LocalDateTime;

public class Link extends FSElement {

    FSElement target;
    Directory parent;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime, target);
        this.target = target;
        this.parent = parent;
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
