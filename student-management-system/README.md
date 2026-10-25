
# Student Management System (Java)

A command-line application developed in Java to manage student records.

This project demonstrates object-oriented programming, data structures, file handling, and input validation.

## Features

- Add students with unique numeric IDs
- Display all students
- Search for students by ID
- Update student names
- Delete students
- Prevent duplicate student IDs
- Validate user input
- Automatically save student records to a CSV file
- Load saved records when the application starts

## Technologies

- Java 17+
- Object-Oriented Programming (OOP)
- ArrayList
- Java File I/O
- CSV file storage
- IntelliJ IDEA

## How to Run

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Open `Main.java`.
4. Run the `main()` method.
5. Use the console menu to manage students.

Alternatively, compile and run from the project root:

```bash
javac -d out src/main/java/com/abdulaziz/studentmanager/*.java
java -cp out com.abdulaziz.studentmanager.Main
```

## Example

```text
=== Student Management System ===
1. Add student
2. List students
3. Find student by ID
4. Edit student
5. Delete student
0. Exit

Choose: 1
Student ID: 100
Student name: Max
Students saved successfully.
Student added.
```

## Data Storage

Student records are automatically saved to `students.csv`.

The file is created in the application's working directory and loaded when the application starts.

The CSV file is excluded from Git using `.gitignore`.

## Project Structure

```text
src/main/java/com/abdulaziz/studentmanager/
    Main.java
    Student.java

README.md
.gitignore
```

## Future Improvements

- [x] CSV file persistence
- [ ] Automated unit tests with JUnit
- [ ] Search students by name
- [ ] Sort students by ID or name
- [ ] Improved error handling

## Learning Objectives

This project was created to practice Java programming, object-oriented design, collections, file operations, and basic CRUD functionality.
