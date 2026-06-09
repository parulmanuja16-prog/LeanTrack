package com.airtribe.learntrack.entity;

/**
 * Represents a student in the LearnTrack application.
 * <p>
 * A student is a specialized {@link Person} with batch information and an
 * active status.
 */
public class Student extends Person {

    private String batch;
    private boolean active;

    /**
     * Constructs a new student with an email address.
     *
     * @param id        the student identifier
     * @param firstname the student's first name
     * @param lastname  the student's last name
     * @param email     the student's email address
     * @param batch     the batch associated with the student
     * @param active    whether the student is currently active
     */
    public Student(String id, String firstname, String lastname, String email, String batch, boolean active) {
        super(id, firstname, lastname, email);
        this.batch = batch;
        this.active = active;
    }

    /**
     * Constructs a new student without an email address.
     *
     * @param id        the student identifier
     * @param firstname the student's first name
     * @param lastname  the student's last name
     * @param batch     the batch associated with the student
     * @param active    whether the student is currently active
     */
    public Student(String id, String firstname, String lastname, String batch, boolean active) {
        super(id, firstname, lastname);
        this.batch = batch;
        this.active = active;
    }

    /**
     * Returns the student's batch assignment.
     *
     * @return the batch name
     */
    public String getBatch() {
        return batch;
    }

    /**
     * Updates the student's batch assignment.
     *
     * @param batch the new batch name
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * Returns whether the student is active.
     *
     * @return {@code true} if the student is active, otherwise {@code false}
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the student's active status.
     *
     * @param active {@code true} to activate the student, {@code false} to deactivate
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Student: {ID='" + this.getId() + "', Name='" + this.getDisplayName() + "', Email='" + this.getEmail() + "', Batch='" + batch + "', Active=" + active + '}';
    }

    /**
     * Returns the student's display name.
     *
     * @return the student's first and last name joined by a space
     */
    @Override
    public String getDisplayName() {
        String name = this.getFirstname() + " " + this.getLastname();
        return name;
    }
}
