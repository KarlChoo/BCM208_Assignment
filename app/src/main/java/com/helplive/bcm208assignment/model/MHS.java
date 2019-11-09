package com.helplive.bcm208assignment.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class MHS {
    private ArrayList<User> users;
    private ArrayList<Residence> residences;

    public MHS(ArrayList<User> users, ArrayList<Residence> residences) {
        this.users = new ArrayList<>();
        this.residences = new ArrayList<>();
    }

    /**
     * Adding an applicant with unique username to the system.
     *
     * @param username unique user name for an applicant
     * @parampassword password of an applicant
     * @paramfullName full name of an applicant
     * @paramemail email of an applicant
     * @paramsalary monthly salary of an applicant
     *
     * @return boolean True if applicant is successfully
     *  created, false otherwise
     */
    public boolean addApplicant(String username, String password,
                                String fullName, String email, double salary) {
        return getUsers().add(new Applicant(username, password,
                fullName, email, salary));
    }

    /**
     * Adding an applicant with unique username to the system.
     *
     * @paramusername unique user name for a housing officer
     * @parampassword password of a housing officer
     * @paramfullName full name of a housing officer
     *
     * @return boolean True if housing officer is successfully
     *  created, false otherwise
     */
    public boolean addHousingOfficer(String username, String password,
                                     String fullName) {
        return getUsers().add(new HousingOfficer(username, password,
                fullName));
    }

    /**
     * Find the User object from user list by matching the username
     *
     * @paramusername A parameter that used to match username of user in
     * userList
     * @return User This returns user with the username that matches the input
     * parameter
     */
    public User findUser(String username) {
        for (User user : getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Find the residence with the parametric residence ID
     * @paramresidenceID The residence ID to search
     * @return Residence The residence matching the parametric
     * residence ID if found, other returns null
     */
    public Residence findResidence(String residenceID) {
        for (Residence r: getResidences())
            if (r.getResidenceID().equalsIgnoreCase(residenceID))
                return r;
        return null;
    }

    /**
     * Find a submitted application based on the parametric ID
     * @paramappID ID of the application
     * @return Application that matches the ID, null otherwise
     */
    public Application findApplication(String appID) {
        for (Residence r: getResidences()) {
            Application app = r.findApplication(appID);
            if (app != null)
                return app;
        }
        return null;
    }

    /**
     * Return a list of allocations
     * @return All allocations that associated an applicant and
     * the residence he/she is assigned to
     */
    public ArrayList<Allocation> getAllAllocations() {
        ArrayList<Allocation> all = new ArrayList<>();
        for (Residence r: getResidences()) {
            ArrayList<Residence.Unit> units = r.getUnits();
            for (Residence.Unit aUnit: units) {
                if (!aUnit.isAvailability()) {
                    all.add(aUnit.getAllocation());
                }
            }
        }
        return all;
    }

    public void addResidence(Residence residence) {
        getResidences().add(residence);
    }

    public boolean addResidence(HousingOfficer housingOfficer,
                                String address, int numOfUnits, int sizePerUnit,
                                double monthlyRental) {
        Residence res = new Residence(address, numOfUnits, sizePerUnit,
                monthlyRental);
        res.setStaffID(housingOfficer.getStaffID());
        housingOfficer.addResidence(res);
        return getResidences().add(res);
    }

    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @paramusers the users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * @return the residences
     */
    public ArrayList<Residence> getResidences() {
        return residences;
    }

    /**
     * @paramresidences the residences to set
     */
    public void setResidences(ArrayList<Residence> residences) {
        this.residences = residences;
    }

    public int noOfUsers() {
        return getUsers().size();
    }

    public int noOfResidences() {
        return getResidences().size();
    }

    public boolean hasAllocations(String username) {
        ArrayList<Allocation> allocations = getAllAllocations();
        if (allocations.size() != 0)
            for (Allocation alloc: allocations)
                if (alloc.getApplication().getApplicant().getUsername().
                        equalsIgnoreCase(username))
                    return true;
        return false;
    }

    public void addApplicationToResidenceAndApplicant(Residence res,
                                                      Applicant applicant, LocalDate appDate, int monthRequired,
                                                      int yearRequired) {

        Application app = new Application(appDate, monthRequired, yearRequired);
        app.setApplicant(applicant);
        applicant.addApplication(app);
        res.addApplication(app);
    }
    /*
    public ArrayList<Residence> getResidencesBy(String staffID) {
        return null;


        ArrayList<Residence> residences = new ArrayList<>();
        for (Residence res: getResidences())
            if (res.getStaffID().equalsIgnoreCase(staffID))
                residences.add(res);

        return residences;

    }
    */

    public HousingOfficer findHO(String staffID) {
        for (User user: getUsers())
            if (user instanceof HousingOfficer) {
                HousingOfficer ho = (HousingOfficer) user;
                if (ho.getStaffID().equalsIgnoreCase(staffID))
                    return ho;
            }
        return null;
    }

    public ArrayList<Application> getApplicationsOf(String staffID) {
        // SHOULD DO THIS IN DRIVER????
        HousingOfficer ho = findHO(staffID);
        if (ho == null)
            return null;
        //////////// ++++++++++


        ArrayList<Residence> residences = ho.getResidences();
        if (residences.size() == 0)
            return null;
        ArrayList<Application> apps = new ArrayList<>();
        for (Residence res: residences) {
            ArrayList<Application> ap = res.getApplications();
            for (Application anApp: ap)
                if (anApp.getStatus().equals("New") ||
                        anApp.getStatus().equals("WaitList"))
                    apps.add(anApp);
        }
        return apps;
    }

    public ArrayList<Application> getAllApplications() {
        if (noOfResidences() == 0)
            return null;
        ArrayList<Application> apps = new ArrayList<>();
        for (Residence r: getResidences()) {
            ArrayList<Application> a = r.getApplications();
            if (a.size() != 0)
                apps.addAll(a);
        }
        return apps;
    }

    public String getResidencesByHO() {
        String s = "";
        for (User user: getUsers()) {
            if (user instanceof HousingOfficer) {
                HousingOfficer ho = (HousingOfficer) user;
                if (ho.noOfResidences() != 0) {
                    s += "Residences by " + ho.getFullname() + ":\n";
                    for (Residence r: ho.getResidences())
                        s += "   " + r + "\n";
                }
            }
        }
        return s;
    }
}

}
