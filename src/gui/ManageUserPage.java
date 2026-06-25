package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.User;
import service.LoginSystem;

public class ManageUserPage extends JFrame {

    private JLabel titleLabel;
    private JLabel userIdLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel roleLabel;
    private JLabel fullNameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel usernameErrorLabel;
    private JLabel passwordErrorLabel;
    private JLabel confirmPasswordErrorLabel;
    private JLabel fullNameErrorLabel;
    private JLabel phoneErrorLabel;
    private JLabel emailErrorLabel;

    private JTextField userIdTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleComboBox;
    private JTextField fullNameTextField;
    private JTextField phoneTextField;
    private JTextField emailTextField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton backButton;

    private JTable userTable;
    private JScrollPane userScrollPane;
    private DefaultTableModel userTableModel;
    private LoginSystem loginSystem;

    public ManageUserPage() {
        loginSystem = new LoginSystem();
        setupFrame();
        setupComponents();
        displayUsers();
    }

    private void setupFrame() {
        setTitle("Manage Users");
        setSize(800, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setupComponents() {
        titleLabel = new JLabel("Manage Users");
        titleLabel.setBounds(340, 20, 120, 25);
        add(titleLabel);

        userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(40, 65, 100, 25);
        add(userIdLabel);

        userIdTextField = new JTextField();
        userIdTextField.setBounds(150, 65, 180, 25);
        userIdTextField.setEditable(false);
        add(userIdTextField);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(410, 65, 100, 25);
        add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(520, 65, 210, 25);
        add(usernameTextField);

        usernameErrorLabel = new JLabel("");
        usernameErrorLabel.setBounds(520, 90, 210, 15);
        setupErrorLabel(usernameErrorLabel);
        add(usernameErrorLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 115, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 115, 180, 25);
        add(passwordField);

        passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setBounds(150, 140, 180, 15);
        setupErrorLabel(passwordErrorLabel);
        add(passwordErrorLabel);

        confirmPasswordLabel = new JLabel("Confirm:");
        confirmPasswordLabel.setBounds(410, 115, 100, 25);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(520, 115, 210, 25);
        add(confirmPasswordField);

        confirmPasswordErrorLabel = new JLabel("");
        confirmPasswordErrorLabel.setBounds(520, 140, 210, 15);
        setupErrorLabel(confirmPasswordErrorLabel);
        add(confirmPasswordErrorLabel);

        roleLabel = new JLabel("Role:");
        roleLabel.setBounds(40, 165, 100, 25);
        add(roleLabel);

        roleComboBox = new JComboBox<String>();
        roleComboBox.setBounds(150, 165, 180, 25);
        roleComboBox.addItem("Doctor");
        roleComboBox.addItem("Nurse");
        roleComboBox.addItem("Receptionist");
        add(roleComboBox);

        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(410, 165, 100, 25);
        add(fullNameLabel);

        fullNameTextField = new JTextField();
        fullNameTextField.setBounds(520, 165, 210, 25);
        add(fullNameTextField);

        fullNameErrorLabel = new JLabel("");
        fullNameErrorLabel.setBounds(520, 190, 210, 15);
        setupErrorLabel(fullNameErrorLabel);
        add(fullNameErrorLabel);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(40, 215, 100, 25);
        add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(150, 215, 180, 25);
        add(phoneTextField);

        phoneErrorLabel = new JLabel("");
        phoneErrorLabel.setBounds(150, 240, 180, 15);
        setupErrorLabel(phoneErrorLabel);
        add(phoneErrorLabel);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(410, 215, 100, 25);
        add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(520, 215, 210, 25);
        add(emailTextField);

        emailErrorLabel = new JLabel("");
        emailErrorLabel.setBounds(520, 240, 210, 15);
        setupErrorLabel(emailErrorLabel);
        add(emailErrorLabel);

        addButton = new JButton("Add User");
        addButton.setBounds(40, 280, 130, 35);
        add(addButton);

        updateButton = new JButton("Update User");
        updateButton.setBounds(190, 280, 130, 35);
        add(updateButton);

        deleteButton = new JButton("Delete User");
        deleteButton.setBounds(340, 280, 130, 35);
        add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(490, 280, 110, 35);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(620, 280, 110, 35);
        add(backButton);

        String[] columns = {"User ID", "Username", "Role", "Full Name", "Phone", "Email"};
        userTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userTable = new JTable(userTableModel);
        userTable.setAutoCreateRowSorter(true);
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userScrollPane = new JScrollPane(userTable);
        userScrollPane.setBounds(40, 340, 700, 230);
        add(userScrollPane);

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed();
            }
        });

        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed();
            }
        });

        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed();
            }
        });

        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed();
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed();
            }
        });

        userTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                tableRowSelected();
            }
        });

        setNextUserId();
    }

    private void addButtonActionPerformed() {
        setNextUserId();

        if (!isInputValid()) {
            return;
        } else if (loginSystem.isUsernameExists(usernameTextField.getText().trim())) {
            usernameErrorLabel.setText("Username is taken");
            return;
        } else {
            User user = createUserFromInput();
            boolean success = loginSystem.addUser(user);

            if (success) {
                JOptionPane.showMessageDialog(this, "User added successfully.");
                clearFields();
                displayUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Please check the details and try again.");
            }
        }
    }

    private void updateButtonActionPerformed() {
        if (userTable.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Select a user from the table before updating.");
        } else if (!loginSystem.isUserIdExists(userIdTextField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Selected user no longer exists.");
        } else if (!isInputValid()) {
            return;
        } else {
            User user = createUserFromInput();
            boolean success = loginSystem.updateUser(user);

            if (success) {
                JOptionPane.showMessageDialog(this, "User updated successfully.");
                clearFields();
                displayUsers();
            } else {
                usernameErrorLabel.setText("Username is taken");
            }
        }
    }

    private void deleteButtonActionPerformed() {
        if (userTable.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Select a user from the table before deleting.");
            return;
        }

        String userId = userIdTextField.getText().trim();
        User selectedUser = findUserById(userId);

        if (selectedUser != null && selectedUser.getRole().equalsIgnoreCase("Admin")) {
            JOptionPane.showMessageDialog(this, "Admin account cannot be deleted.");
            return;
        }

        if (userId.equalsIgnoreCase("A001")) {
            JOptionPane.showMessageDialog(this, "Admin account cannot be deleted.");
            return;
        }

        if (!loginSystem.isUserIdExists(userId)) {
            JOptionPane.showMessageDialog(this, "Selected user no longer exists.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete selected user?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = loginSystem.deleteUser(userId);

            if (success) {
                JOptionPane.showMessageDialog(this, "User deleted successfully.");
                clearFields();
                displayUsers();
            } else {
                JOptionPane.showMessageDialog(this, "This account cannot be deleted here.");
            }
        }
    }

    private void clearButtonActionPerformed() {
        clearFields();
    }

    private void backButtonActionPerformed() {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
        dispose();
    }

    private void tableRowSelected() {
        if (userTable.getSelectedRow() < 0) {
            return;
        }

        int selectedRow = userTable.getSelectedRow();
        int modelRow = userTable.convertRowIndexToModel(selectedRow);
        String userId = userTableModel.getValueAt(modelRow, 0).toString();
        User user = findUserById(userId);

        if (user != null) {
            fillFields(user);
        }
    }

    private boolean isInputEmpty() {
        if (userIdTextField.getText().trim().equals("")) {
            return true;
        } else if (usernameTextField.getText().trim().equals("")) {
            return true;
        } else if (new String(passwordField.getPassword()).trim().equals("")) {
            return true;
        } else if (new String(confirmPasswordField.getPassword()).trim().equals("")) {
            return true;
        } else if (fullNameTextField.getText().trim().equals("")) {
            return true;
        } else if (phoneTextField.getText().trim().equals("")) {
            return true;
        } else if (emailTextField.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInputValid() {
        clearErrorLabels();

        String username = usernameTextField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
        String fullName = fullNameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String email = emailTextField.getText().trim();

        if (isInputEmpty()) {
            setRequiredErrors();
            return false;
        } else if (!isUsernameValid(username)) {
            usernameErrorLabel.setText("Use 4-15 letters/numbers");
            return false;
        } else if (password.length() < 4 || password.length() > 20) {
            passwordErrorLabel.setText("Use 4-20 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordErrorLabel.setText("Passwords must match");
            return false;
        } else if (fullName.length() > 50) {
            fullNameErrorLabel.setText("Keep under 50 characters");
            return false;
        } else if (!isPhoneValid(phone)) {
            phoneErrorLabel.setText("Use 10-12 digits");
            return false;
        } else if (!isEmailValid(email)) {
            emailErrorLabel.setText("Enter a valid email");
            return false;
        } else {
            return true;
        }
    }

    private boolean isUsernameValid(String username) {
        if (username.length() < 4 || username.length() > 15) {
            return false;
        }

        for (int i = 0; i < username.length(); i++) {
            char letter = username.charAt(i);

            if (!isLetterOrNumber(letter)) {
                return false;
            }
        }

        return true;
    }

    private boolean isLetterOrNumber(char letter) {
        if (letter >= 'A' && letter <= 'Z') {
            return true;
        } else if (letter >= 'a' && letter <= 'z') {
            return true;
        } else if (letter >= '0' && letter <= '9') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPhoneValid(String phone) {
        if (phone.length() < 10 || phone.length() > 12) {
            return false;
        }

        for (int i = 0; i < phone.length(); i++) {
            char number = phone.charAt(i);

            if (number < '0' || number > '9') {
                return false;
            }
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        int atPosition = email.indexOf("@");
        int dotPosition = email.lastIndexOf(".");

        if (email.length() < 5 || email.length() > 40) {
            return false;
        } else if (email.indexOf(" ") >= 0) {
            return false;
        } else if (atPosition <= 0) {
            return false;
        } else if (dotPosition <= atPosition + 1) {
            return false;
        } else if (dotPosition == email.length() - 1) {
            return false;
        } else {
            return true;
        }
    }

    private User createUserFromInput() {
        String userId = userIdTextField.getText().trim();
        String username = usernameTextField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = roleComboBox.getSelectedItem().toString();
        String fullName = fullNameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String email = emailTextField.getText().trim();

        User user = new User(userId, username, password, role, fullName, phone, email);
        return user;
    }

    private void displayUsers() {
        loginSystem.loadUsers();
        userTableModel.setRowCount(0);

        ArrayList<User> users = loginSystem.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (loginSystem.isStaffRole(user.getRole())) {
                Object[] row = {
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getEmail()
                };

                userTableModel.addRow(row);
            }
        }
    }

    private void clearFields() {
        usernameTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        fullNameTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        roleComboBox.setSelectedIndex(0);
        userTable.clearSelection();
        clearErrorLabels();
        setNextUserId();
    }

    private void setupErrorLabel(JLabel label) {
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.PLAIN, 11));
    }

    private void clearErrorLabels() {
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");
        confirmPasswordErrorLabel.setText("");
        fullNameErrorLabel.setText("");
        phoneErrorLabel.setText("");
        emailErrorLabel.setText("");
    }

    private void setRequiredErrors() {
        if (usernameTextField.getText().trim().equals("")) {
            usernameErrorLabel.setText("Please fill this in");
        }

        if (new String(passwordField.getPassword()).trim().equals("")) {
            passwordErrorLabel.setText("Please fill this in");
        }

        if (new String(confirmPasswordField.getPassword()).trim().equals("")) {
            confirmPasswordErrorLabel.setText("Please fill this in");
        }

        if (fullNameTextField.getText().trim().equals("")) {
            fullNameErrorLabel.setText("Please fill this in");
        }

        if (phoneTextField.getText().trim().equals("")) {
            phoneErrorLabel.setText("Please fill this in");
        }

        if (emailTextField.getText().trim().equals("")) {
            emailErrorLabel.setText("Please fill this in");
        }
    }

    private void setNextUserId() {
        userIdTextField.setText(getNextUserId());
    }

    private String getNextUserId() {
        loginSystem.loadUsers();

        ArrayList<User> users = loginSystem.getAllUsers();
        int biggestId = 0;

        for (int i = 0; i < users.size(); i++) {
            String userId = users.get(i).getUserId();

            try {
                int currentId = Integer.parseInt(userId);

                if (currentId > biggestId) {
                    biggestId = currentId;
                }
            } catch (Exception e) {
                // Ignore IDs like A001 because staff IDs use numbers.
            }
        }

        return "" + (biggestId + 1);
    }

    private User findUserById(String userId) {
        loginSystem.loadUsers();

        ArrayList<User> users = loginSystem.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    private void fillFields(User user) {
        userIdTextField.setText(user.getUserId());
        usernameTextField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        confirmPasswordField.setText(user.getPassword());
        roleComboBox.setSelectedItem(user.getRole());
        fullNameTextField.setText(user.getFullName());
        phoneTextField.setText(user.getPhone());
        emailTextField.setText(user.getEmail());
        clearErrorLabels();
    }

    public static void main(String[] args) {
        ManageUserPage manageUserPage = new ManageUserPage();
        manageUserPage.setVisible(true);
    }
}
