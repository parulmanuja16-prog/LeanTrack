package com.airtribe.learntrack.entity;

/**
 * Represents a trainer in the LearnTrack application.
 * <p>
 * A trainer is a specialized {@link Person} with an expertise field used to
 * describe the trainer's subject area.
 */
public class Trainer extends Person {
    private String expertise;

    /**
     * Constructs a trainer with an email address.
     *
     * @param id        the unique trainer identifier
     * @param firstname the trainer's first name
     * @param lastname  the trainer's last name
     * @param email     the trainer's email address
     * @param expertise the trainer's area of expertise
     */
    public Trainer(String id, String firstname, String lastname, String email, String expertise) {
        super(id, firstname, lastname, email);
        this.expertise = expertise;
    }

    /**
     * Constructs a trainer without an email address.
     *
     * @param id        the unique trainer identifier
     * @param firstname the trainer's first name
     * @param lastname  the trainer's last name
     * @param expertise the trainer's area of expertise
     */
    public Trainer(String id, String firstname, String lastname, String expertise) {
        super(id, firstname, lastname);
        this.expertise = expertise;
    }

    /**
     * Returns the trainer's expertise.
     *
     * @return the expertise area
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * Sets the trainer's expertise.
     *
     * @param expertise the expertise area to set
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    /**
     * Returns the trainer's display name.
     *
     * @return the trainer's full name
     */
    @Override
    public String getDisplayName() {
        return "Trainer Name: " + this.getFirstname() + " " + this.getLastname();
    }

}
