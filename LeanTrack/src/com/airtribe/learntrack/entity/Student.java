package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

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
     * <p>
     * The student is assigned a generated ID and is active by default.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param email     the student's email address
     * @param batch     the batch associated with the student
     */
    public Student(String firstName, String lastName, String email, String batch) {
        super(IdGenerator.getNextStudentId(), firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    /**
     * Constructs a new student without an email address.
     * <p>
     * The student is assigned a generated ID and is active by default.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param batch     the batch associated with the student
     */
    public Student(String firstName, String lastName, String batch) {
        super(IdGenerator.getNextStudentId(), firstName, lastName);
        this.batch = batch;
        this.active = true;
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
        return "Student: {ID='" + this.getId() + "', '" + this.getDisplayName() + "', Email='" + this.getEmail() + "', Batch='" + batch + "', Active=" + active + '}';
    }

    /**
     * Returns the student's display name.
     *
     * @return the student's first and last name joined by a space
     */
    @Override
    public String getDisplayName() {
        String name = "Student Name: " + this.getFirstname() + " " + this.getLastname();
        return name;
    }
}
