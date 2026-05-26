/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Admin;
import model.User;
import utils.FileHandler;

public class LoginSystem {

    private ArrayList<User> users;
    private final String USER_FILE = "users.txt";

    public LoginSystem() {
        users = new ArrayList<User>();
        loadUsers();

        if (users.size() == 0) {
            addDefaultAdmin();
            saveUsers();
        }
    }

    public void loadUsers() {
        users.clear();

        ArrayList<String> lines = FileHandler.readFile(USER_FILE);

        for (int i = 0; i < lines.size(); i++) {
            User user = User.fromFileString(lines.get(i));

            if (user != null) {
                users.add(user);
            }
        }
    }

    public void saveUsers() {
        ArrayList<String> lines = new ArrayList<String>();

        for (int i = 0; i < users.size(); i++) {
            lines.add(users.get(i).toFileString());
        }

        FileHandler.writeFile(USER_FILE, lines);
    }

    public User login(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public boolean addUser(User newUser) {
        if (newUser == null) {
            return false;
        }

        if (!isStaffRole(newUser.getRole())) {
            return false;
        }

        if (isUserIdExists(newUser.getUserId())) {
            return false;
        }

        if (isUsernameExists(newUser.getUsername())) {
            return false;
        }

        users.add(newUser);
        saveUsers();
        return true;
    }

    public boolean updateUser(User updatedUser) {
        if (updatedUser == null) {
            return false;
        }

        if (!isStaffRole(updatedUser.getRole())) {
            return false;
        }

        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);

            if (currentUser.getUserId().equals(updatedUser.getUserId())) {
                if (isUsernameUsedByAnotherUser(updatedUser.getUsername(), updatedUser.getUserId())) {
                    return false;
                }

                users.set(i, updatedUser);
                saveUsers();
                return true;
            }
        }

        return false;
    }

    public boolean deleteUser(String userId) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.getUserId().equals(userId)) {
                if (!isStaffRole(user.getRole())) {
                    return false;
                }

                users.remove(i);
                saveUsers();
                return true;
            }
        }

        return false;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public boolean isUserIdExists(String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    public boolean isUsernameExists(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean isStaffRole(String role) {
        if (role == null) {
            return false;
        } else if (role.equalsIgnoreCase("Doctor")) {
            return true;
        } else if (role.equalsIgnoreCase("Nurse")) {
            return true;
        } else if (role.equalsIgnoreCase("Receptionist")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isUsernameUsedByAnotherUser(String username, String userId) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.getUsername().equals(username)
                    && !user.getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    private void addDefaultAdmin() {
        Admin admin = new Admin("A001", "admin", "admin123",
                "System Admin", "", "admin@clinic.local");
        users.add(admin);
    }
}
