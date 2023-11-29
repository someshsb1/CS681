package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{

    private LinkedList<FSElement> childrens;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target, ReentrantLock lock) {
        super(parent, name, size, creationTime, target, lock);

        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
        this.parent = parent;

        childrens = new LinkedList<FSElement>();
    }

    public Directory getDirectory() {
        lock.lock();
        try {
            return parent;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<FSElement> getChildrens() {
        lock.lock();
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
        lock.lock();
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
            for (FSElement child: childrens) {
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
            for (FSElement child: childrens) {
                if (child.isFile()) {
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
        lock.lock();
        try {
            for (FSElement child: childrens) {
                if (child.isDirectory()) {
                    size+= ((Directory) child).getTotalSize();
                } else {
                    size+= child.getSize();
                }
            }
        } finally {
            lock.unlock();
        }
        return size;
    }
}
