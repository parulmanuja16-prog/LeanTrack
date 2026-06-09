package com.airtribe.learntrack.entity;

import java.time.LocalDate;

import com.airtribe.learntrack.constants.LeanTrackConstants.EnrollmentStatus;

/**
 * Represents an enrollment record linking a student to a course.
 * <p>
 * Enrollment records include the enrollment identifier, student ID, course ID,
 * enrollment date, and current status.
 */
public class Enrollment {
    private String id;
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;
    private Enum<EnrollmentStatus> status;

    /**
     * Constructs a new enrollment instance.
     *
     * @param id             the unique enrollment identifier
     * @param studentId      the ID of the enrolled student
     * @param courseId       the ID of the course being enrolled in
     * @param enrollmentDate the date the enrollment was created
     * @param status         the current enrollment status
     */
    public Enrollment(String id, String studentId, String courseId, LocalDate enrollmentDate,
            Enum<EnrollmentStatus> status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    /**
     * Returns the enrollment ID.
     *
     * @return the enrollment identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the enrollment identifier.
     *
     * @param id the enrollment ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the student ID tied to this enrollment.
     *
     * @return the student identifier
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID for this enrollment.
     *
     * @param studentId the student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns the course ID tied to this enrollment.
     *
     * @return the course identifier
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID for this enrollment.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns the enrollment creation date.
     *
     * @return the enrollment date
     */
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * Sets the enrollment date.
     *
     * @param enrollmentDate the date to set for enrollment
     */
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Returns the current enrollment status.
     *
     * @return the enrollment status
     */
    public Enum<EnrollmentStatus> getStatus() {
        return status;
    }

    /**
     * Sets the enrollment status.
     *
     * @param status the status to set
     */
    public void setStatus(Enum<EnrollmentStatus> status) {
        this.status = status;
    }

}

