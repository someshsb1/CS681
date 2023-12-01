package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
    
    private LinkedList<FSElement> childrens;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        super(parent, name, size, creationTime, target, lock);
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

    public LinkedList<FSElement> getChildren() {
        lock.unlock(); 
        try {
        return childrens;
        } finally {
            lock.unlock();
        }
        
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
        childrens.add(child);
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.unlock();
        try {
        return childrens.size();
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<Directory>();
        lock.lock();
        try {
            for (FSElement child : childrens) {
                if (child.isDirectory()) {
                    subDirectories.add((Directory) child);
                }
            }
        } finally {
            lock.unlock();
            }
        return subDirectories;
    }


    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<File>();
        lock.lock();
        try {
        for (FSElement child : childrens) {
            if(child.isFile()) {
                files.add((File) child);
            }
        }
    } finally {
        lock.unlock();
    }
        return files;
    }

    public int getTotalSize() {
        int size = 0;
        lock.unlock();
        try {
        for (FSElement child : childrens) {
            if(child.isDirectory()) {
                size += ((Directory) child).getTotalSize();
            } else {
                size += child.getSize();
            }
        }
    } finally {
        lock.unlock();
    }
        return size;
    }



       //to capture the name of the subdirectories under the parent
       public Directory subDirectoryName(String name) {
        lock.lock();
        try {
        for (FSElement fs: childrens) {
            if (fs instanceof Directory && fs.getName().equals(name)) {
                return (Directory) fs;
            }
        }
        } finally {
            lock.unlock();
        }
            return null;
        }
    }
