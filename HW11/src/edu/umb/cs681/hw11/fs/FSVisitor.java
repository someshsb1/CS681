package edu.umb.cs681.hw11.fs;

public interface FSVisitor {

    public void visit(Link link);
    public void visit(Directory dir);
    public void visit(File file);
    
}
