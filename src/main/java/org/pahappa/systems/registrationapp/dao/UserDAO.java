package org.pahappa.systems.registrationapp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pahappa.systems.registrationapp.config.SessionConfiguration;
import org.pahappa.systems.registrationapp.models.User;

import java.util.Date;
import java.util.List;

public class UserDAO {
    //saveUser
    //getAllUsers
    //getUserById
    //Update User
    //Delete Student

    public static void saveUser(User user) {
        try{
            SessionFactory sf = SessionConfiguration.getSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();

        }
        catch (Exception e){
            System.out.println("User save failed");
        }
    }

    public static List<User> getAllUsers() {
        SessionFactory sf = SessionConfiguration.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> user_list = query.list();
        tx.commit();
        session.close();
        return  user_list;
    }
    public static User getAUser(String user_name) {
        SessionFactory sf = SessionConfiguration.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User where username = :user_name");
        query.setParameter("user_name", user_name);
        User user = (User) query.uniqueResult();
        tx.commit();
        session.close();
        return  user;
    }


    public static void DeleteUser(String userName) {
        SessionFactory sf = SessionConfiguration.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete User where username = :user_name");
        query.setParameter("user_name", userName);
        query.executeUpdate();
        tx.commit();
    }

    public static void DeleteUsers() {
        SessionFactory sf = SessionConfiguration.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete from User ");
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public static void updateUser(String user_name, String Firstname, String Lastname, Date dateOfBirth) {
        SessionFactory sf = SessionConfiguration.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update User set firstname= :Firstname, lastname = :Lastname, dateOfBirth= :dateOfBirth where username = :user_name ");
        query.setParameter("user_name", user_name);
        query.setParameter("Firstname", Firstname);
        query.setParameter("Lastname", Lastname);
        query.setParameter("dateOfBirth", dateOfBirth);
        query.executeUpdate();
        tx.commit();
        session.close();

    }
}