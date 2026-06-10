package com.airtribe.learntrack.entity;

/**
 * Base entity for a person in the LearnTrack application.
 * <p>
 * This class stores identity and contact information for a person and
 * provides accessors for first name, last name, email, and display name.
 */
public class Person {
    private final String id;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Returns the person identifier.
     *
     * @return the person's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Constructs a new person with a full name and email address.
     *
     * @param id        the unique person identifier
     * @param firstName the person's first name
     * @param lastName  the person's last name
     * @param email     the person's email address
     */
    public Person(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Constructs a new person without an email address.
     *
     * @param id        the unique person identifier
     * @param firstName the person's first name
     * @param lastName  the person's last name
     */
    public Person(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

     /**
     * Returns the person's display name.
     * <p>
     * This method also prints the display name to standard output.
     *
     * @return the first name and last name concatenated with a space
     */
    public String getDisplayName() {
        System.out.println("Person: " + firstName + " " + lastName);
        return firstName + " " + lastName;
    }


    /**
     * Returns the person's first name.
     *
     * @return the first name
     */
    public String getFirstname() {
        return firstName;
    }

    /**
     * Sets the person's first name.
     *
     * @param firstname the first name to set
     */
    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    /**
     * Returns the person's last name.
     *
     * @return the last name
     */
    public String getLastname() {
        return lastName;
    }

    /**
     * Sets the person's last name.
     *
     * @param lastname the last name to set
     */
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    /**
     * Returns the person's email address.
     *
     * @return the email address, or {@code null} if not set
     */
    public String getEmail() {
        if(email == null) {
            return "-";
        }
        return email;
    }

    /**
     * Sets the person's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

   
}
