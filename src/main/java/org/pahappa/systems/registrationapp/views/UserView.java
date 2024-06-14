package org.pahappa.systems.registrationapp.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.Scanner;
import org.pahappa.systems.registrationapp.models.User;
import org.pahappa.systems.registrationapp.services.UserService;
import org.pahappa.systems.registrationapp.dao.UserDAO;

public class UserView {

    private final Scanner scanner;

    public UserView(){
        this.scanner = new Scanner(System.in);
    }


    public void displayMenu() {
        System.out.println("********* User Registration System *********");
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Register a user");
            System.out.println("2. Display all users");
            System.out.println("3. Get a user of username");
            System.out.println("4. Update user details of username");
            System.out.println("5. Delete User of username");
            System.out.println("6. Delete all users");
            System.out.println("7. Exit");
            try{
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        displayAllUsers();
                        break;
                    case 3:
                        getUserOfUsername();
                        break;
                    case 4:
                        updateUserOfUsername();
                        break;
                    case 5:
                        deleteUserOfUsername();
                        break;
                    case 6:
                        deleteAllUsers();
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid choice. Please try again.");
                System.out.println(e);
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
    private void registerUser() throws NoSuchFieldException, ParseException {
        String firstName;
        do {
            System.out.print("Enter first name (no spaces or digits): ");
            firstName = scanner.nextLine().trim(); // Remove leading/trailing spaces
        } while (firstName.isEmpty() || firstName.matches(".*\\d+.*")); // Check for empty or digits


        String lastName;
        do {
            System.out.print("Enter last name (no spaces or digits): ");
            lastName = scanner.nextLine().trim();
        } while (lastName.isEmpty() || lastName.matches(".*\\d+.*"));

        String username;
        do {
            System.out.print("Enter username (no spaces or digits, must be unique): ");
            username = scanner.nextLine().trim();
        } while (username.isEmpty() || username.matches(".*\\d+.*")); // Check for empty, digits


        System.out.println("Enter Date of Birth: mm/dd/yyyy");
        String dateOfBirth = scanner.nextLine();
        if(dateOfBirth.isEmpty()){
            System.out.println("Date of Birth cannot be empty.");
            scanner.nextLine();
        }

        else {
            UserService.addUser(firstName,lastName,username,dateOfBirth);
        }
    }


    private void displayAllUsers() {
        System.out.println("System has the following users");
        UserService.ReturnAllUsers();
    }

    private void getUserOfUsername() {
        System.out.print("Enter username for the user you wish to search: ");
        String username = scanner.nextLine();
        UserService.ReturnUser(username);
    }

    private void updateUserOfUsername() throws ParseException {
        System.out.print("Enter username for the user you wish to update ");
        String username = scanner.nextLine();
        System.out.println("Enter first name of user you wish to delete");
        String first_name = scanner.nextLine();
        System.out.println("Enter last name of user you wish to delete");
        String last_name = scanner.nextLine();
        System.out.println("Enter date of birth of user you wish to delete");
        String date_of_birth = scanner.nextLine();
        UserService.updateUser(username, first_name,last_name,date_of_birth);


    }

    private void deleteUserOfUsername() {
        System.out.println("Enter user name of user you wish to delete");
        String username = scanner.nextLine();
        UserService.deleteUser(username);
    }

    private void deleteAllUsers() {
        UserService.deleteUsers();
        System.out.println("Users deleted from database");

    }
}

