package org.pahappa.systems.registrationapp.services;

import org.pahappa.systems.registrationapp.dao.UserDAO;
import org.pahappa.systems.registrationapp.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class UserService {

    public static void addUser(String firstName, String lastName, String username, String dateOfBirth) throws ParseException {
        DateFormat birthDate = new SimpleDateFormat("MM/dd/yyyy");
        Date date_of_birth = birthDate.parse(dateOfBirth);

        //Convert date to localDate and format without time
        LocalDate localDateOfBirth= date_of_birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //Get the current date
        LocalDate currentDate = LocalDate.now();

        if(localDateOfBirth.isAfter(currentDate)) {
            System.out.println("Date of Birth can not be in the future.");
        }
        else {
            // Convert LocalDate to Date
            Date DOB = Date.from(localDateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());
            User user = UserDAO.getAUser(username);

            if (user == null) {
                user = new User();
                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setUsername(username);
                user.setDateOfBirth(DOB);
                UserDAO.saveUser(user);
            } else {
                System.out.println("User already exists");
            }

        }

    }


    public static void ReturnAllUsers() {
        List<User> users = UserDAO.getAllUsers();
        if (!users.isEmpty()) {

            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users available");

        }
    }

    public static void ReturnUser(String user_name){
        List<User> users = UserDAO.getAllUsers();
        if(!users.isEmpty()){
           User user = UserDAO.getAUser(user_name);
           if(user != null){
               System.out.println(user.toString());
           }
           else {
               System.out.println("User not present");
           }


        }else {
            System.out.println("Database currently empty");
        }

    }

    public static void deleteUser(String user_name){
        List<User> users = UserDAO.getAllUsers();
        if(!users.isEmpty()){
            User user = UserDAO.getAUser(user_name);
            if(user != null){
                UserDAO.DeleteUser(user_name);
            }
            else {
                System.out.println("User not present");
            }
        }
        else {
            System.out.println("Data base currently empty");
        }
    }

    public static void deleteUsers() {
        List<User> users = UserDAO.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No users available");
        }else{
            UserDAO.DeleteUsers();
        }
    }

    public static void updateUser(String username, String Firstname, String Lastname, String dateOfBirth ) throws ParseException {
        List<User> users = UserDAO.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No users available");
        }else{
            User user = UserDAO.getAUser(username);
            if(user != null){
                DateFormat birthDate = new SimpleDateFormat("MM/dd/yyyy");
                Date date_of_birth = birthDate.parse(dateOfBirth);
                UserDAO.updateUser(username, Firstname, Lastname,  date_of_birth );
                System.out.println("User updated successfuly");
            }
            else{
                System.out.println("User not present");
            }

        }

    }

}

