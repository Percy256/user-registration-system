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
                System.out.println(e.getMessage());
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
    private void registerUser() throws NoSuchFieldException, ParseException {
        System.out.print("Enter first name: "); // Prompt user for input
        String firstName = scanner.nextLine();
        if(firstName.isEmpty()){
            System.out.println("First name cannot be empty.");
            scanner.nextLine();

        }
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        if(lastName.isEmpty()){
            System.out.println("Last name cannot be empty.");
            scanner.nextLine();
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if(username.isEmpty()){
            System.out.println("Username cannot be empty.");
            scanner.nextLine();
        }
        System.out.println("Enter Date of Birth: mm/dd/yyyy");
        String dateOfBirth = scanner.nextLine();
        if(dateOfBirth.isEmpty()){
            System.out.println("Date of Birth cannot be empty.");
            scanner.nextLine();
        }

        else {


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


                boolean user_name_present = false;
                for (User x : UserService.getUsers()) {
                    if ((x.getUsername().equals(username))) {
                        user_name_present = true;
                    }
                }
                if (user_name_present) {

                    System.out.println("Username exists. Please try again.");

                } else {
                    User user = new User(); // Create a new User object
                    user.setFirstname(firstName);
                    user.setLastname(lastName);
                    user.setUsername(username);
                    user.setDateOfBirth(DOB);
                    UserService.getUsers().add(user);
                    System.out.println("User registered successfully.");
                }
            }


        }



    }

    private void displayAllUsers() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if(!UserService.getUsers().isEmpty()) {
            for (User u : UserService.getUsers()) {
                String formattedDate = dateFormat.format(u.getDateOfBirth());
                System.out.println("User name: " + u.getUsername() + " has names " + u.getFirstname() + " " + u.getLastname() + "and date of birth " + formattedDate);
            }
        }
        else {
            System.out.println("There are no users registered.");
        }
    }

    private void getUserOfUsername() {
        System.out.print("Enter username for the user you wish to search: ");

        for (User u : UserService.getUsers()) {
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
        for (User u : UserService.getUsers()) {
            if (u.getUsername().equals(username)) {
                System.out.print("Enter new first name: "); // Prompt user for input
                String firstName = scanner.nextLine();
                System.out.print("Enter new last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter new  date of birth: ");
                String dateOfBirth = scanner.nextLine();
                DateFormat birthDate = new SimpleDateFormat("MM/dd/yyyy");
                Date date_of_birth = birthDate.parse(dateOfBirth);

                //Convert date to localDate and format without time
                LocalDate localDateOfBirth= date_of_birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String formattedDateOfBirth= localDateOfBirth.format(formatter);
                Date date= (Date) formatter.parse(formattedDateOfBirth);


                u.setLastname(lastName);
                u.setFirstname(firstName);
                u.setDateOfBirth(date);
                System.out.println("User updated successfully.");
            }
            else {
                System.out.println("Username does not match any user.");
            }
        }


    }

    private void deleteUserOfUsername() {
        System.out.print("Enter username for the user you wish to delete ");
        for (User u : UserService.getUsers()) {
            if (u.getUsername().equals(scanner.nextLine())) {
                UserService.getUsers().remove(u);
                System.out.println("User deleted successfully.");
            }
            else {
                System.out.println("Username does not match any user.");
            }
        }
    }

    private void deleteAllUsers() {
        if(!UserService.getUsers().isEmpty()) {
            UserService.getUsers().clear();
            System.out.println("All users deleted successfully.");
        }
        else {
            System.out.println("There are no users to delete.");
        }

    }
}

