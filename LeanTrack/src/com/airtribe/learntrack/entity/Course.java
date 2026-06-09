package com.airtribe.learntrack.entity;

/**
 * Represents a course in the LearnTrack application.
 * <p>
 * A course contains a unique identifier, name, description, duration, and an
 * active status flag.
 */
public class Course {
    private final String id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    /**
     * Constructs a new course instance.
     *
     * @param id              the unique course identifier
     * @param courseName      the human-readable course name
     * @param description     a short description of the course
     * @param durationInWeeks the duration of the course in weeks
     * @param active          whether the course is active
     */
    public Course(String id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", Course Name=" + courseName + ", Description=" + description + ", Duration In Weeks="
                + durationInWeeks + ", Active Status=" + active + "]";
    }

    /**
     * Returns the course identifier.
     *
     * @return the course ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the course name.
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name.
     *
     * @param courseName the new course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the course duration in weeks.
     *
     * @return the duration in weeks
     */
    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    /**
     * Sets the course duration.
     *
     * @param durationInWeeks the duration in weeks
     */
    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    /**
     * Returns whether the course is active.
     *
     * @return {@code true} if active, otherwise {@code false}
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active status of the course.
     *
     * @param active {@code true} to activate the course, {@code false} to deactivate
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the course description.
     *
     * @return the course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the course description.
     *
     * @param description the new course description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
