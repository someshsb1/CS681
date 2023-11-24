package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
    
    private static ReentrantLock lock = new ReentrantLock();
    private LinkedList<FSElement> childrens;
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime, target);
        this.childrens = new LinkedList<FSElement>();
    }

    public void accept(FSVisitor v) {
        lock.lock();
        try {
            v.visit(this);
            for (FSElement e: childrens) {
                e.accept(v);
            }
        } finally {
            lock.unlock();
        }
    }

    public Directory getParent() {
        return parent;
    }
    
    public LinkedList<FSElement> getChildren() {
        return childrens;
        
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void appendChild(FSElement child) {
        childrens.add(child);
    }

    public int countChildren() {
        return childrens.size();
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<Directory>();
        for (FSElement child : childrens) {
            if (child.isDirectory()) {
                subDirectories.add((Directory) child);
            }
        }
        return subDirectories;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<File>();
        for (FSElement child : childrens) {
            if(child.isFile()) {
                files.add((File) child);
            }
        }
        return files;
    }

    public int getTotalSize() {
        int size = 0;
        for (FSElement child : childrens) {
            if(child.isDirectory()) {
                size += ((Directory) child).getTotalSize();
            } else {
                size += child.getSize();
            }
        }
        return size;
    }


    public boolean isDirectory() {
        return true;
    }

       //to capture the name of the subdirectories under the parent
       public Directory subDirectoryName(String name) {
        for (FSElement fs: childrens) {
            if (fs instanceof Directory && fs.getName().equals(name)) {
                return (Directory) fs;
            }
        }
        return null;
    }
    

}
