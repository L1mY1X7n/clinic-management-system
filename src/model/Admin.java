/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Admin extends User {

    public Admin() {
        super();
        setRole("Admin");
    }

    public Admin(String userId, String username, String password,
            String fullName, String phone, String email) {
        super(userId, username, password, "Admin", fullName, phone, email);
    }

    @Override
    public String getUserType() {
        return "Admin";
    }
}
