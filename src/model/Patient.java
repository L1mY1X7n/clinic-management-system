/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wwndr
 */
public class Patient extends User {

    public Patient() {
        super();
        setRole("Patient");
    }

    public Patient(String userId, String username, String password,
            String fullName, String phone, String email) {
        super(userId, username, password, "Patient", fullName, phone, email);
    }

    @Override
    public String getUserType() {
        return "Patient";
    }
}
