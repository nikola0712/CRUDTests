package models.user;

import enums.Gender;
import enums.Status;

public class User {

    private final String name;
    private final Gender gender;
    private final String email;
    private final Status status;

    public User(String name, Gender gender, String email, Status status) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public Status getStatus() {
        return status;
    }
}
