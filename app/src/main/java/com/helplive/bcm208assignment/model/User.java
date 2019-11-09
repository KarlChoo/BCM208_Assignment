package com.helplive.bcm208assignment.model;

public class User {
    private String username;
    private String password;
    private String fullname;

    public User(String username, String password, String fullname) {
        setUsername(username);
        setPassword(password);
        setFullname(fullname);
    }

    public User() {
        this("not set", "not set", "not set");
    }

    public void viewApplications(){
        System.out.println("Yet to completed");
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @paramusername the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @parampassword the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @paramfullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String toString() {
        return getUsername() + " (" + getPassword() + ") : " + getFullname();
    }
}

}
