package com.helplive.bcm208assignment;

public abstract class User {

    private String username;
    private String password;
    private String fullname;

    public User(String username,String password,String fullname){
        setUsername(username);
        setPassword(password);
        setFullname(fullname);
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


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername()
                + " | Password: " + getPassword()
                + " | Full name: " + getFullname();
    }


}
