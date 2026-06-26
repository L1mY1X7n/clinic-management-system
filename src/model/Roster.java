package model;

public class Roster {
    private String rosterId;
    private String date;
    private String doctor1Id;
    private String doctor2Id;
    private String nurse1Id;
    private String nurse2Id;
    private String nurse3Id;

    public Roster(String rosterId, String date, String doctor1Id,
            String doctor2Id, String nurse1Id, String nurse2Id,
            String nurse3Id) {
        this.rosterId = rosterId;
        this.date = date;
        this.doctor1Id = doctor1Id;
        this.doctor2Id = doctor2Id;
        this.nurse1Id = nurse1Id;
        this.nurse2Id = nurse2Id;
        this.nurse3Id = nurse3Id;
    }

    public String getRosterId() {
        return rosterId;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor1Id() {
        return doctor1Id;
    }

    public String getDoctor2Id() {
        return doctor2Id;
    }

    public String getNurse1Id() {
        return nurse1Id;
    }

    public String getNurse2Id() {
        return nurse2Id;
    }

    public String getNurse3Id() {
        return nurse3Id;
    }

    public static Roster fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid roster format");
        }
        return new Roster(parts[0], parts[1], parts[2], parts[3], parts[4],
                parts[5], parts[6]);
    }
}
