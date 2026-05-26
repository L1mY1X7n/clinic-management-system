/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;
import service.LoginSystem;

public class LoginPage extends JFrame {

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginSystem loginSystem;

    public LoginPage() {
        loginSystem = new LoginSystem();
        setupFrame();
        setupComponents();
    }

    private void setupFrame() {
        setTitle("Clinic Management System - Login");
        setSize(360, 230);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setupComponents() {
        titleLabel = new JLabel("Clinic Management System");
        titleLabel.setBounds(90, 20, 200, 25);
        add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 65, 100, 25);
        add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(140, 65, 160, 25);
        add(usernameTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 105, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 105, 160, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(140, 145, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed();
            }
        });
    }

    private void loginButtonActionPerformed() {
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Enter your username and password.");
        } else {
            User user = loginSystem.login(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "The username or password is incorrect.");
            } else {
                showLoginSuccess(user);
            }
        }
    }

    private void showLoginSuccess(User user) {
        if (user.getRole().equalsIgnoreCase("Admin")) {
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Signed in as " + user.getRole() + ".");
        }
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}
