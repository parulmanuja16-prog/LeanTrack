package com.airtribe.learntrack.util;

/**
 * Generates simple unique identifiers for LearnTrack entities.
 * <p>
 * This utility class is not instantiable and provides static methods for
 * generating sequential IDs for students, courses, and enrollments.
 */
public class IdGenerator {
    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

    private static int studentCounter = 0;
    private static int courseCounter = 0;
    private static int enrollmentCounter = 0;

    /**
     * Generates the next student identifier.
     *
     * @return a new student ID in the format S##
     */
    public static String getNextStudentId() {
        studentCounter++;
        return "S" + String.format("%02d", studentCounter);
    }

    /**
     * Generates the next course identifier.
     *
     * @return a new course ID in the format C##
     */
    public static String getNextCourseId() {
        courseCounter++;
        return "C" + String.format("%02d", courseCounter);
    }

    /**
     * Generates the next enrollment identifier.
     *
     * @return a new enrollment ID in the format ENR##
     */
    public static String getNextEnrollmentId() {
        enrollmentCounter++;
        return "ENR" + String.format("%02d", enrollmentCounter);
    }

}
