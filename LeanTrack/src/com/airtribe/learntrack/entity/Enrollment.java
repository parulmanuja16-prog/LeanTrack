package com.airtribe.learntrack.entity;

import java.time.LocalDate;

import com.airtribe.learntrack.constants.LeanTrackConstants.EnrollmentStatus;
import com.airtribe.learntrack.util.IdGenerator;

/**
 * Represents an enrollment record linking a student to a course.
 * <p>
 * Enrollment records include the enrollment identifier, student ID, course ID,
 * enrollment date, and current status.
 */
public class Enrollment {
    private final String id;
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    /**
     * Constructs a new enrollment instance.
     *
     * @param studentId      the ID of the enrolled student
     * @param courseId       the ID of the course being enrolled in
     */
    public Enrollment(String studentId, String courseId) {
        this.id = IdGenerator.getNextEnrollmentId();
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = java.time.LocalDate.now();
        this.status = EnrollmentStatus.ACTIVE; // New enrollments are active by default
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
        if(courseId == null || "".equals(courseId)) {
            throw new IllegalArgumentException("Course ID cannot be null or empty.");
        }
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
        if(enrollmentDate == null) {
            throw new IllegalArgumentException("Enrollment date cannot be null.");
        }
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Returns the current enrollment status.
     *
     * @return the enrollment status
     */
    public EnrollmentStatus getStatus() {
        return status;
    }

    /**
     * Sets the enrollment status.
     *
     * @param status the status to set
     */
    public void setStatus(EnrollmentStatus status) {
        if(status == null) {
            throw new IllegalArgumentException("Enrollment status cannot be null.");
        }
        this.status = status;
    }

}

