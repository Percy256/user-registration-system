package org.pahappa.systems.registrationapp.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.pahappa.systems.registrationapp.models.User;
import org.pahappa.systems.registrationapp.services.UserService;

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
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    private void registerUser() throws NoSuchFieldException, ParseException {
        System.out.print("Enter first name: "); // Prompt user for input
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter date of birth: ");
        String dateOfBirth = scanner.nextLine();

        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date date_of_birth = df.parse(dateOfBirth);


        User user = new User(); // Create a new User object
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setUsername(username);
        user.setDateOfBirth(date_of_birth);
        UserService.users.add(user);

        System.out.println("User registered successfully.");


    }

    private void displayAllUsers() {
        if(!UserService.users.isEmpty()) {
            for (User u : UserService.users) {
                System.out.println("User name: " + u.getUsername() + " has names " + u.getFirstname() + " " + u.getLastname() + "and date of birth " + u.getDateOfBirth());
            }
        }
        else {
            System.out.println("There are no users registered.");
        }
    }

    private void getUserOfUsername() {
        System.out.print("Enter username for the user you wish to search: ");

        for (User u : UserService.users) {
            if (u.getUsername().equals(scanner.nextLine())) {
                System.out.println("User name: " + u.getUsername() + " has names " + u.getFirstname() + " " + u.getLastname()+ "and date of birth " + u.getDateOfBirth());
            }
            else {
                System.out.println("Username does not match any user.");
            }
        }

    }

    private void updateUserOfUsername() throws ParseException {
        System.out.print("Enter username for the user you wish to update ");
        String username = scanner.nextLine();
        for (User u : UserService.users) {
            if (u.getUsername().equals(username)) {
                System.out.print("Enter new first name: "); // Prompt user for input
                String firstName = scanner.nextLine();
                System.out.print("Enter new last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter new  date of birth: ");
                String dateOfBirth = scanner.nextLine();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date_of_birth = df.parse(dateOfBirth);

                u.setLastname(lastName);
                u.setFirstname(firstName);
                u.setDateOfBirth(date_of_birth);
                System.out.println("User updated successfully.");
            }
            else {
                System.out.println("Username does not match any user.");
            }
        }


    }

    private void deleteUserOfUsername() {
        System.out.print("Enter username for the user you wish to delete ");
        for (User u : UserService.users) {
            if (u.getUsername().equals(scanner.nextLine())) {
                UserService.users.remove(u);
                System.out.println("User deleted successfully.");
            }
            else {
                System.out.println("Username does not match any user.");
            }
        }
    }

    private void deleteAllUsers() {
        if(!UserService.users.isEmpty()) {
            UserService.users.clear();
            System.out.println("All users deleted successfully.");
        }
        else {
            System.out.println("There are no users to delete.");
        }

    }
}

