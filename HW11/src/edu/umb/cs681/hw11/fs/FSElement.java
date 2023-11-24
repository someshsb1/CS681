package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;

public class FSElement {

    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;
    FSElement target;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        return parent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void accept(FSVisitor v) {

    }

    public static void main(String args[]) {

        System.out.println("File System Implemented with Visitor Design Pattern");
    }
}
