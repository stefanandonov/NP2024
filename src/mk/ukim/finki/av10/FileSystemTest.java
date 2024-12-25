package mk.ukim.finki.av10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface IFile { //Component
    String getFileName();

    long getFileSize(); //execute - rekurzija

    String getFileInfo(String indent); //execute - rekurzija
}

class File implements IFile {
    String name;
    long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo(String indent) {
        return String.format("%sFile name: %s File size: %d", indent, getFileName(), getFileSize());
    }
}

class Folder implements IFile {
    String name;

    List<IFile> children;

    public Folder(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    void addFile(IFile file) { //i file i folder
        children.add(file);
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return children.stream().mapToLong(f -> f.getFileSize()).sum();
    }

    @Override
    public String getFileInfo(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%sFolder name: %s Folder size: %d\n", indent, getFileName(), getFileSize()));
        for (IFile child : children) {
            sb.append(child.getFileInfo(indent+"    ")).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getFileInfo("");
    }
}

public class FileSystemTest {

    public static Folder readFolder(Scanner sc) {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < totalFiles; i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String[] parts = fileInfo.split("\\s+");

                folder.addFile(new File(parts[0], Long.parseLong(parts[1])));

            } else {

                folder.addFile(readFolder(sc));

            }
        }

        return folder;
    }

    public static void main(String[] args) {

        //file reading from input

        Scanner sc = new Scanner(System.in);

        System.out.println("===READING FILES FROM INPUT===");
        Folder fileSystem = new Folder("root");

        fileSystem.addFile(readFolder(sc));


        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

//        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
//        fileSystem.sortBySize();
//        System.out.println(fileSystem.toString());

//        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
//        System.out.println(fileSystem.findLargestFile());


    }
}