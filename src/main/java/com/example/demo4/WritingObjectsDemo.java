package com.example.demo4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WritingObjectsDemo {
    static File dataFile = new File("students.obf");
    static File metaFile = new File("students.txt");

    public static void main(String... args) throws IOException {
        for (int i = 0; i < 10; i++) {
            Student student = new Student("id" + i, "name" + i);
            School.getStudents().add(student);
        }
        for (int i = 0; i < School.getStudents().size(); i++) {
            System.out.println(School.getStudents().get(i).getName());
        }
        writeMetaDataFile();
        writeDataFile();
        readDataFile();

    }

    private static void writeMetaDataFile() throws FileNotFoundException {
        try(PrintWriter printWriter = new PrintWriter(metaFile))
        {
            printWriter.print(School.getStudents().size());
        }
    }

    private static int readMetaDataFile() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(metaFile))
        {
             return scanner.nextInt();
        }
    }

    private static void readDataFile() throws FileNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(dataFile)){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                for (int i = 0; i < readMetaDataFile(); i++) {
                    System.out.println(objectInputStream.readObject());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeDataFile() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(dataFile)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                for (int i = 0; i < School.getStudents().size(); i++) {
                    objectOutputStream.writeObject(School.getStudents().get(i));
                    System.out.println("writing object");
                }
            }
        }
    }
}


class School implements Serializable {
    private static final List<Student> students = new ArrayList<>();

    public static List<Student> getStudents() {
        return students;
    }
}

class Student implements Serializable {
    private final String name;
    private final String id;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
