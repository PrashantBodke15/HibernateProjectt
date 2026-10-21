package org.example.operation;

import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class StudentOperation {

    private Configuration configuration;
    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;

    public StudentOperation(Configuration configuration,
                            SessionFactory sessionFactory,
                            Session session,
                            Transaction transaction) {
        this.configuration = configuration;
        this.sessionFactory = sessionFactory;
        this.session = session;
        this.transaction = transaction;
    }

    Scanner sc = new Scanner(System.in);

    // 1. Insert Student
    public void insertStudent() {

        System.out.println("Enter the id: ");
        int id = sc.nextInt();

        System.out.println("Enter the name: ");
        String name = sc.next();

        System.out.println("Enter the marks: ");
        double marks = sc.nextDouble();

        if (session.find(Student.class, id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        Student student = new Student(id, name, marks);
        session.persist(student);

        transaction.commit();

        System.out.println("Student added successfully");
    }

    // 2. Display Student by ID
    public void displayStudent() {

        System.out.println("Enter id: ");
        int id = sc.nextInt();

        Student student = session.find(Student.class, id);

        if (student != null) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Marks: " + student.getMarks());
        } else {
            System.out.println("Student not found");
        }
    }

    // 3. Update Student
    public void updateData() {

        System.out.println("Enter the id: ");
        int id = sc.nextInt();

        Student student = session.find(Student.class, id);

        if (student == null) {
            System.out.println("Student not found");
            return;
        }

        System.out.println("Enter the name: ");
        String name = sc.next();

        System.out.println("Enter the marks: ");
        double marks = sc.nextDouble();

        student.setName(name);
        student.setMarks(marks);

        System.out.println("Student updated successfully");
    }

    // 4. Delete Student
    public void deleteStudent() {

        System.out.println("Enter the id: ");
        int id = sc.nextInt();

        Student student = session.find(Student.class, id);

        if (student != null) {
            session.remove(student);
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    // 5. Display All Students
    public void displayAllStudents() {

        Query<Student> query = session.createQuery(
                "FROM Student",
                Student.class
        );

        List<Student> studentList = query.getResultList();

        if (studentList.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("All Students:");

            for (Student s : studentList) {
                System.out.println(
                        "ID: " + s.getId()
                                + " | Name: " + s.getName()
                                + " | Marks: " + s.getMarks()
                );
            }
        }
    }

    // 6. Sort Students in Ascending Order
    public void sortAsc() {

        Query<Student> query = session.createQuery(
                "FROM Student ORDER BY marks ASC",
                Student.class
        );

        List<Student> studentList = query.getResultList();

        System.out.println("Students in Ascending Order:");

        for (Student s : studentList) {
            System.out.println(
                    "ID: " + s.getId()
                            + " | Name: " + s.getName()
                            + " | Marks: " + s.getMarks()
            );
        }
    }

    // 7. Sort Students in Descending Order
    public void sortDesc() {

        Query<Student> query = session.createQuery(
                "FROM Student ORDER BY marks DESC",
                Student.class
        );

        List<Student> studentList = query.getResultList();

        System.out.println("Students in Descending Order:");

        for (Student s : studentList) {
            System.out.println(
                    "ID: " + s.getId()
                            + " | Name: " + s.getName()
                            + " | Marks: " + s.getMarks()
            );
        }
    }
}