package edu.umb.cs681.hw8;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{

    protected String name;
    protected int size;
    protected LocalDateTime creationTime;

    private LinkedList<FSElement> childrens;
    private Directory parent;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime, target);

        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
        this.parent = parent;

        childrens = new LinkedList<FSElement>();
    }

    public Directory getDirectory() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LinkedList<FSElement> getChildrens() {
        return childrens;
    }

    public void appendChild(FSElement child) {
        childrens.add(child);
    }

    public int countChildren() {
        return childrens.size();
    }

    public boolean isDirectory() {
        return true;
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<Directory>();
        for (FSElement child: childrens) {
            if (child.isDirectory()) {
                subDirectories.add((Directory) child);
            }
        }
        return subDirectories;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<File>();
        for (FSElement child: childrens) {
            if (child.isFile()) {
                files.add((File) child);
            }
        }
        return files;
    }

    public int getTotalSize() {
        int size = 0;
        for (FSElement child: childrens) {
            if (child.isDirectory()) {
                size+= ((Directory) child).getTotalSize();
            } else {
                size+= child.getSize();
            }
        }
        return size;
    }
}
