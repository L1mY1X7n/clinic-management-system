
package model;

public class User {
 
    private String userId;
    private String username;
    private String password;
    private String role;
    private String fullName;
    private String phone;
    private String email;

    public User() {
        userId = "";
        username = "";
        password = "";
        role = "";
        fullName = "";
        phone = "";
        email = "";
    }

    public User(String userId, String username, String password, String role,
            String fullName, String phone, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return "General User";
    }

    public String toFileString() {
        return emptyIfNull(userId) + ","
                + emptyIfNull(username) + ","
                + emptyIfNull(password) + ","
                + emptyIfNull(role) + ","
                + emptyIfNull(fullName) + ","
                + emptyIfNull(phone) + ","
                + emptyIfNull(email);
    }

    public static Admin fromFileString(String line) {
        String[] parts = line.split(",", -1);

        if (parts.length >= 7) {
            if (parts[3].equalsIgnoreCase("Admin")) {
                return new Admin(parts[0], parts[1], parts[2], parts[4], parts[5], parts[6]);
            } else {
                return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
            }
        }

        return null;
    }

    private String emptyIfNull(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }
}