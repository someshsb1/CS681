package edu.umb.cs681.hw11.fs.util;

import java.util.LinkedList;

import edu.umb.cs681.hw11.fs.*;

public class FileCrawlingVisitor implements FSVisitor {

    @Override
    public void visit(Link link) {
        return;
    }

    @Override
    public void visit(Directory dir) {
        return;
    }

    private LinkedList<File> files;

    public FileCrawlingVisitor() {
        files = new LinkedList<File>();
    }

    @Override
    public void visit(File file) {
        
        files.add(file);
    }

    public LinkedList<File> getFiles() {
        return files;
        
    }  
    
    public LinkedList<String> getFileName() {
        LinkedList<String> fileName = new LinkedList<String>();
        for(File file: files) {
            fileName.add(file.getName());
        }
        return fileName;
    }
}
