
package com.abdulaziz.studentmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Path FILE_PATH = Path.of("students.csv");

    public static void main(String[] args) {

        loadStudents();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Find student by ID");
            System.out.println("4. Edit student");
            System.out.println("5. Delete student");
            System.out.println("0. Exit");

            int choice = readInt("Choose: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> listStudents();
                case 3 -> {
                    Student student = findById(readInt("Student ID: "));
                    if (student == null) {
                        System.out.println("Student not found.");
                    } else {
                        System.out.println(student);
                    }
                }
                case 4 -> editStudent();
                case 5 -> deleteStudent();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addStudent() {
        int id = readInt("Student ID: ");

        if (findById(id) != null) {
            System.out.println("ID already exists.");
            return;
        }

        String name = readName("Student name: ");
        students.add(new Student(id, name));

        saveStudents();
        System.out.println("Student added.");
    }

    private static void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void editStudent() {
        Student student = findById(readInt("Student ID: "));

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.setName(readName("New name: "));
        saveStudents();

        System.out.println("Student updated.");
    }

    private static void deleteStudent() {
        Student student = findById(readInt("Student ID: "));

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        saveStudents();

        System.out.println("Student deleted.");
    }

    private static Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readName(String message) {
        while (true) {
            System.out.print(message);
            String name = scanner.nextLine().trim();

            if (!name.isEmpty() && !name.contains(",")) {
                return name;
            }

            System.out.println(
                    "Name cannot be empty or contain commas."
            );
        }
    }

    private static void saveStudents() {
        List<String> lines = new ArrayList<>();

        for (Student student : students) {
            lines.add(student.getId() + "," + student.getName());
        }

        try {
            Files.write(FILE_PATH, lines);
            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            System.out.println(
                    "Error saving students: " + e.getMessage()
            );
        }
    }

    private static void loadStudents() {
        if (!Files.exists(FILE_PATH)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line : lines) {
                String[] parts = line.split(",", 2);

                if (parts.length != 2) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();

                    if (!name.isEmpty() && findById(id) == null) {
                        students.add(new Student(id, name));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid student ID.");
                }
            }

            System.out.println("Students loaded successfully.");

        } catch (IOException e) {
            System.out.println(
                    "Error loading students: " + e.getMessage()
            );
        }
    }
}
