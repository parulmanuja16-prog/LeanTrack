package com.airtribe.learntrack.entity;

/**
 * Base entity for a person in the LearnTrack application.
 * <p>
 * This class stores identity and contact information for a person and
 * provides accessors for first name, last name, email, and display name.
 */
public class Person {
    private final String id;
    private String firstname;
    private String lastname;
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
     * @param firstname the person's first name
     * @param lastname  the person's last name
     * @param email     the person's email address
     */
    public Person(String id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    /**
     * Constructs a new person without an email address.
     *
     * @param id        the unique person identifier
     * @param firstname the person's first name
     * @param lastname  the person's last name
     */
    public Person(String id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Returns the person's first name.
     *
     * @return the first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the person's first name.
     *
     * @param firstname the first name to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns the person's last name.
     *
     * @return the last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the person's last name.
     *
     * @param lastname the last name to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Returns the person's email address.
     *
     * @return the email address, or {@code null} if not set
     */
    public String getEmail() {
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

    /**
     * Returns the person's display name.
     * <p>
     * This method also prints the display name to standard output.
     *
     * @return the first name and last name concatenated with a space
     */
    public String getDisplayName() {
        System.out.println("Person: " + firstname + " " + lastname);
        return firstname + " " + lastname;
    }

}
