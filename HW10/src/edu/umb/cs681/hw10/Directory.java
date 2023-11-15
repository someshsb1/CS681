package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{

    protected String name;
    protected int size;
    protected LocalDateTime creationTime;

    private LinkedList<FSElement> childrens;
    private Directory parent;

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

    public void setName(String name) {
        lock.lock();
        this.name = name;
        lock.unlock();
    }

    public String getName() {
        lock.lock();
        try {
            return name;
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

    public boolean isDirectory() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<Directory>();
            for (FSElement child: childrens) {
                lock.lock();
                try {
                    if (child.isDirectory()) {
                        subDirectories.add((Directory) child);
                    }
                } finally {
                    lock.unlock();
                }
            }
        return subDirectories;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<File>();
            for (FSElement child: childrens) {
                lock.lock();
                try {
                    if (child.isFile()) {
                        files.add((File) child);
                    }
                } finally {
                    lock.unlock();
                }
            }
            return files;
    }

    public int getTotalSize() {
        int size = 0;
            for (FSElement child: childrens) {
                lock.lock();
                try {
                    if (child.isDirectory()) {
                        size+= ((Directory) child).getTotalSize();
                    } else {
                        size+= child.getSize();
                    }
                } finally {
                    lock.unlock();
                }
            }
        return size;
    }
}
