package edu.umb.cs681.hw11.fs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import edu.umb.cs681.hw11.fs.util.*;

// <<SINGLETON CLASS>>
public class FileSystem {

    private LinkedList<Directory> rootDirs;
    private static FileSystem ins = null;
    private List<File> sharedList;
    private static ReentrantLock lock = new ReentrantLock();
    private volatile boolean done = false;


    private FileSystem() {
        rootDirs = new LinkedList<Directory>();
        sharedList = new ArrayList<>();
    }

    public static FileSystem getFileSystem() {
        if (ins == null) {
            ins = new FileSystem();
        }
        return ins;
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;

    }

    public void appendRootDir(Directory prjRoot) {
        lock.lock();
        rootDirs.add(prjRoot);
        lock.unlock();
    }

    public List<File> getSharedList() {
        lock.lock(); //thread-synchronization preventing concurrency
        try {
            return sharedList;
        } finally {
            lock.unlock();
        }
    }

    public void setDone() {
        done = true;
    }
    public static void main(String args[]) {

        FileSystem fs = new FileSystem();
        List<File> identifiedFilesSharedList = fs.getSharedList();

        //Drive 1
        Directory Applications = new Directory(null, "Applications", 0, LocalDateTime.now(), null);
        Directory Mails = new Directory(Applications, "Mails", 0, LocalDateTime.now(), null);
        File Facetime = new File(Applications, "Facetime", 0, LocalDateTime.now(), null);
        File Facebook = new File(Applications, "Facebook", 0, LocalDateTime.now(), null);
        Link Attachment = new Link(Mails, "Attachment", 0, LocalDateTime.now(), Mails);

        Applications.appendChild(Mails);
        Mails.appendChild(Facetime);
        Applications.appendChild(Attachment);
        Applications.appendChild(Facebook);

        //Drive 2
        Directory Home = new Directory(null, "Home", 0, LocalDateTime.now(), null);
        Directory CS681 = new Directory(Home, "CS681", 0, LocalDateTime.now(), null);
        File HW11 = new File(CS681, "HW11", 0, LocalDateTime.now(), null);
        File HW12 = new File(CS681, "HW12", 0, LocalDateTime.now(), null);
        Link HW01 = new Link(Home, "HW11", 0, LocalDateTime.now(), HW12);

        Home.appendChild(CS681);
        CS681.appendChild(HW11);
        CS681.appendChild(HW12);
        Home.appendChild(HW01);

        //Drive 3
        Directory Downloads = new Directory(null, "Downloads", 0, LocalDateTime.now(), null);
        Directory Wallpapers = new Directory(Downloads, "Wallpapers", 0, LocalDateTime.now(), null);
        Directory Songs = new Directory(Downloads, "Songs", 0, LocalDateTime.now(), null);
        File Song1 = new File(Songs, "Song1", 0, LocalDateTime.now(), null);
        File Wallpaper1 = new File(Wallpapers, "Wallpaper1", 0, LocalDateTime.now(), Song1);
        Link AlbumArt = new Link(Wallpapers, "AlbumArt", 0, LocalDateTime.now(), Song1);

        Downloads.appendChild(Wallpapers);
        Downloads.appendChild(Songs);
        Songs.appendChild(Song1);
        Wallpapers.appendChild(Wallpaper1);
        Downloads.appendChild(AlbumArt);

        //Drive 4
        Directory Macintosh = new Directory(null, "Macintosh", 0, LocalDateTime.now(), null);
        Directory Shared = new Directory(Macintosh, "Shared", 0, LocalDateTime.now(), null);
        File Word = new File(Macintosh, "Word", 0, LocalDateTime.now(), null);
        File Excel = new File(Macintosh, "Excel", 0, LocalDateTime.now(), null);
        File PowerPoint = new File(Macintosh, "PPT", 0, LocalDateTime.now(), null);
        Link Img = new Link(Shared, "img.jpg", 0, LocalDateTime.now(), PowerPoint);
        Link Charts = new Link(Shared, "charts.xlsx", 0, LocalDateTime.now(), Excel);

        Macintosh.appendChild(Shared);
        Macintosh.appendChild(Word);
        Macintosh.appendChild(Excel);
        Macintosh.appendChild(PowerPoint);
        Shared.appendChild(Charts);
        Shared.appendChild(Img);

        //Drive 5
        Directory newFolder = new Directory(null, "NewFolder", 0, LocalDateTime.now(), null);
        Link newFile = new Link(newFolder, "newFile", 0, LocalDateTime.now(), null);

        newFolder.appendChild(newFile);

        //append all root directories.
        fs.appendRootDir(Applications);
        fs.appendRootDir(Home);
        fs.appendRootDir(Downloads);
        fs.appendRootDir(Macintosh);
        fs.appendRootDir(newFolder);

        //placing FileCrawlingVisitor instances in ThreadLocal
        ThreadLocal<FileCrawlingVisitor> ThreadsVisitor1 = ThreadLocal.withInitial(FileCrawlingVisitor::new);
        ThreadLocal<FileCrawlingVisitor> ThreadsVisitor2 = ThreadLocal.withInitial(FileCrawlingVisitor::new);
        ThreadLocal<FileCrawlingVisitor> ThreadsVisitor3 = ThreadLocal.withInitial(FileCrawlingVisitor::new);
        ThreadLocal<FileCrawlingVisitor> ThreadsVisitor4 = ThreadLocal.withInitial(FileCrawlingVisitor::new);
        ThreadLocal<FileCrawlingVisitor> ThreadsVisitor5 = ThreadLocal.withInitial(FileCrawlingVisitor::new);

        Thread crawlingThread1 = new Thread(() -> {
            FileCrawlingVisitor visitDrive1 = ThreadsVisitor1.get();
            while(!fs.done) {
            Applications.accept(visitDrive1);
            identifiedFilesSharedList.addAll(visitDrive1.getFiles()); //adding identified files to the shared list.

            System.out.println("Applications Directory Crawling: " + visitDrive1.getFiles());
            }
        });
        crawlingThread1.start();

        Thread crawlingThread2 = new Thread(() -> {
            FileCrawlingVisitor visitDrive2 = ThreadsVisitor2.get();
            while(!fs.done) {
            Home.accept(visitDrive2);
            identifiedFilesSharedList.addAll(visitDrive2.getFiles());

            System.out.println("Home Directory Crawling: " + visitDrive2.getFiles());
            }
        });
        crawlingThread2.start();

        Thread crawlingThread3 = new Thread(() -> {
            FileCrawlingVisitor visitDrive3 = ThreadsVisitor3.get();
            while(!fs.done) {
            Downloads.accept(visitDrive3);
            identifiedFilesSharedList.addAll(visitDrive3.getFiles());

            System.out.println("Downloads Directory Crawling: " + visitDrive3.getFiles());
        }
        });
        crawlingThread3.start();

        Thread crawlingThread4 = new Thread(() -> {
            FileCrawlingVisitor visitDrive4 = ThreadsVisitor4.get();
            // System.out.println("xxxxxx");
            while(!fs.done) {
            Macintosh.accept(visitDrive4);
            identifiedFilesSharedList.addAll(visitDrive4.getFiles());

            System.out.println("Macintosh Directory Crawling: " + visitDrive4.getFiles());
            }
        });
        crawlingThread4.start();

        Thread crawlingThread5 = new Thread(() -> {
            FileCrawlingVisitor visitDrive5 = ThreadsVisitor5.get();
            while(!fs.done) {
                newFolder.accept(visitDrive5);
                identifiedFilesSharedList.addAll(visitDrive5.getFiles());

                System.out.println("newFolder Directory Crawling: " + visitDrive5.getFiles());
            }
        });
        crawlingThread5.start();

        //2 step thread termination, setting done to true
        fs.setDone();

        //wait for threads to finish and join
        try {
            crawlingThread1.join();
            crawlingThread2.join();
            crawlingThread3.join();
            crawlingThread4.join();
            crawlingThread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //all identified files so far put in shared list
        System.out.println("All files in shared list: " + identifiedFilesSharedList);
    }

}
