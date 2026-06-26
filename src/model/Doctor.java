package model;


public class Doctor extends User {
    private String specialization;

    public Doctor(String userId, String username, String password, String role,
            String fullName, String phone, String email) {
        this(userId, username, password, role, fullName, phone, email, "General");
    }

    public Doctor(String userId, String username, String password, String role,
            String fullName, String phone, String email, String specialization) {
        super(userId, username, password, role, fullName, phone, email);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String getUserType() {
        return "Doctor";
    }

    @Override
    public String toFileString() {
        /*
         * users.txt is locked to 7 fields, so specialization is intentionally not
         * written there. It can later be loaded from a doctor-owned file such as
         * doctors_profile.txt without changing the team leader's users.txt format.
         */
        return getUserId() + "," + getUsername() + "," + getPassword() + ","
                + getRole() + "," + getFullName() + "," + getPhone() + ","
                + getEmail();
    }

    @Override
    public String toString() {
        return getUserId() + " - " + getFullName() + " (" + specialization + ")";
    }
}
