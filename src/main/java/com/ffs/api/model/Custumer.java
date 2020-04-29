package com.ffs.api.model;

/**
 *
 * @author francisco
 */
public class Custumer {

    private final String name;
    private final String email;
    private final String telephone;
    private Boolean active = false;

    public Custumer(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

}
