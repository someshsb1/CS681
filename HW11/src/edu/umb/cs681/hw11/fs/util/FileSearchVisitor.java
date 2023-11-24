package edu.umb.cs681.hw11.fs.util;

import java.util.LinkedList;

import edu.umb.cs681.hw11.fs.*;

public class FileSearchVisitor implements FSVisitor {

    String fileName;

    LinkedList<File> foundFiles;

    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
        this.foundFiles = new LinkedList<File>();
    }
 
    public void visit(Link link) {

        return;

    }
    public void visit(Directory dir) {

        return;

    }
    public void visit(File file) {

        if (file.getName().equals(fileName)) 
            foundFiles.add(file);
        
    }

    public LinkedList<File> getFoundFiles() {
        return foundFiles;

    }
    
}
