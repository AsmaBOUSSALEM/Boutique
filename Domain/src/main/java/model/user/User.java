package model.user;

import model.shared.Entity;

/**
 * Created by asmaboussalem on 15/11/2016.
 */
public class User implements Entity<User> {

    private String firstName;
    private String lastName;
    private String address;
    private String telNumber;

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.telNumber = "";
    }

    public User(String firstName, String lastName, String address, String telNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telNumber = telNumber;
    }

    public boolean sameIdentityAs(User other) {
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTelNumber() {
        return this.telNumber;
    }

}
