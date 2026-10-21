package org.example.model;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

//        try (SessionFactory sessionFactory =
//                     configuration.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//
//            HQLOperation hqlOperation =
//                    new HQLOperation(session);
//
//            Transaction transaction = session.beginTransaction();
//
//            // HQL Operations

        // hqlOperation.whereQuery();
        // hqlOperation.namedParameter();
//            hqlOperation.count();
        // hqlOperation.sortAsc();
        // hqlOperation.sortDesc();

//            transaction.commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();

//        Session session = HibernateUtil.getFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Passport passport = new Passport();
//        passport.setPassportId(2);
//        passport.setPassportNumber("IND123789");
//
//        Person person = new Person();
//        person.setPersonId(102);
//        person.setPersonName("Nayana Patil");
//        person.setPassport(passport);
//
//        session.persist(person);
//        transaction.commit();
//        session.close();
//        HibernateUtil.getFactory().close();
//        System.out.println("Data Saved Successfully One to one");
//

        Transaction transaction = null;

        try (Session session = HibernateUtil.getFactory().openSession()) {

            transaction = session.beginTransaction();

            Employee e1 = new Employee(1, "Aditi");
            Employee e2 = new Employee(2, "Rahul");
            Employee e3 = new Employee(3, "Priya");

            Department department =
                    new Department(101, "IT Department");

            department.getEmployees().add(e1);
            department.getEmployees().add(e2);
            department.getEmployees().add(e3);

            session.persist(e1);
            session.persist(e2);
            session.persist(e3);
            session.persist(department);

            transaction.commit();

            System.out.println("One-to-Many Data Saved Successfully!");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.err.println("Error: " + e.getMessage());
        } finally {
            HibernateUtil.getFactory().close();
        }
    }
}