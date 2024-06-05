package org.pahappa.systems.registrationapp.services;

import org.pahappa.systems.registrationapp.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserService {
    public static List<User> users = new ArrayList<>();

    public UserService() {
        this.users= new ArrayList<>(); //initialize the list when UserService object is created
    }

    public void addUser(User user) {
        users.add(user);
    }

    // Method to retrieve a user by username, returning an Optional<User> to handle
    // potential absence of user
    public Optional<User> getUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    // Method to get a copy of the list of all users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // Method to delete a user by username, returning true if deletion is successful
    public boolean deleteUser(String username) {
        return users.removeIf(user -> user.getUsername().equals(username));
    }
}
