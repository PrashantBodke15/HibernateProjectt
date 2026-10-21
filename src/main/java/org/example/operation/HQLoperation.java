package org.example.operation;

import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HQLoperation {

    private final Session session;

    public HQLoperation(Session session) {
        this.session = session;
    }

    // 1. WHERE Query
    public void whereQuery() {

        Query<Student> query = session.createQuery(
                "FROM Student WHERE marks > 80",
                Student.class
        );

        List<Student> list = query.list();

        System.out.println("Students above marks 80:");

        for (Student s : list) {
            System.out.println("Id: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Marks: " + s.getMarks());
            System.out.println("--------------------");
        }
    }

    // 2. Named Parameters
    public void namedParameter() {

        Query<Student> query = session.createQuery(
                "FROM Student WHERE id = :sid",
                Student.class
        );

        query.setParameter("sid", 103);

        Student s = query.uniqueResult();

        if (s != null) {
            System.out.println("ID: " + s.getId()
                    + " | Name: " + s.getName()
                    + " | Marks: " + s.getMarks());
        } else {
            System.out.println("Student not found!");
        }
    }

    // 3. Count Query
    public void count() {

        Query<Long> query = session.createQuery(
                "SELECT COUNT(s) FROM Student s",
                Long.class
        );

        Long total = query.uniqueResult();

        System.out.println("Total Students: " + total);
    }

    // 4. Sort in Ascending Order
    public void sortAsc() {

        Query<Student> query = session.createQuery(
                "FROM Student ORDER BY marks ASC",
                Student.class
        );

        List<Student> list = query.list();

        System.out.println("Students in Ascending Order:");

        for (Student s : list) {
            System.out.println("Id: " + s.getId()
                    + " | Name: " + s.getName()
                    + " | Marks: " + s.getMarks());
        }
    }

    // 5. Sort in Descending Order
    public void sortDesc() {

        Query<Student> query = session.createQuery(
                "FROM Student ORDER BY marks DESC",
                Student.class
        );

        List<Student> list = query.list();

        System.out.println("Students in Descending Order:");
        int i = 1;
        for (Student s : list) {
            System.out.println(i +"Id: " + s.getId()
                    + " | Name: " + s.getName()
                    + " | Marks: " + s.getMarks());
            i++;
        }
    }
}