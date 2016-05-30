package com.ds.iwish.bean;


public class Profile {

    private long id;
    private String passwordHash;
    private String email;
    private String firstName;
    private String lastName;

    private long defaultCategory;
    private long defaultLayout;
    private long defaultTemplate;

    private transient String password;
    private transient String passwordClone;
    private transient boolean rememberMe;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return id == profile.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(long defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public long getDefaultLayout() {
        return defaultLayout;
    }

    public void setDefaultLayout(long defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    public long getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(long defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordClone() {
        return passwordClone;
    }

    public void setPasswordClone(String passwordClone) {
        this.passwordClone = passwordClone;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
