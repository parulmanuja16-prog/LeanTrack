package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputUtil;
import com.airtribe.learntrack.util.InputValidation;

/**
 * Console-based user interface for the LearnTrack application.
 * <p>
 * This class provides a simple menu-driven UI for managing students, courses,
 * and enrollments using the underlying service layer.
 */
public class Main {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

    /**
     * Application entrypoint that displays the main menu and processes user actions.
     *
     * @param args runtime arguments (ignored)
     */
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            InputUtil.printLine("--------------------------------LearnTrack Console UI---------------------------------------");
            InputUtil.printLine("***********************Student Management******************");
            InputUtil.printLine("1. Add student");
            InputUtil.printLine("2. View all students");
            InputUtil.printLine("3. Search student by ID");
            InputUtil.printLine("4. Deactivate a student");

            InputUtil.printLine("************************Course Management****************");
            InputUtil.printLine("5. Add course");
            InputUtil.printLine("6. View all courses");
            InputUtil.printLine("7. Activate/Deactivate a course");

            InputUtil.printLine("********************Enrollment Management*********************");
            InputUtil.printLine("8. Enroll a student in a course");
            InputUtil.printLine("9. View enrollments for a student");
            InputUtil.printLine("10. Mark enrollment as completed/cancelled");

            InputUtil.printLine("0. Exit");

            int choice = InputUtil.readInt("Select an option:");
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    deactivateStudent();
                    break;
                case 5:
                    addNewCourse();
                    break;
                case 6:
                    viewAllCourses();
                    break;
                case 7:
                    deactivateCourse();
                    break;
                case 8:
                    enrollStudent();
                    break;
                case 9:
                    viewAllEnrollmentsForStudent();
                    break;
                case 10:
                    markEnrollmentAsCompleted();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    InputUtil.printLine("Invalid selection. Please try again.");
            }
        }
    }

    /**
     * Reads student details from the console and adds a new student.
     * <p>
     * If required fields are missing or the email format is invalid, the student
     * is not added.
     */
    private static void addStudent() {
        System.out.println();
        String firstName = InputUtil.readLine("Student firstname:");
        String lastName = InputUtil.readLine("Student lastname:");
        String email = InputUtil.readLine("Student email:");
        if (email != null && !InputValidation.isValidEmail(email)) {
            InputUtil.printLine("Invalid email format. Student not added.");
            return;
        }
        String batch = InputUtil.readLine("Student batch:");
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        if (firstName.isEmpty() || lastName.isEmpty() || batch.isEmpty()) {
            InputUtil.printLine("All fields are required. Student not added.");
            return;
        }
        studentService.addNewStudent(firstName, lastName, email, batch);
        InputUtil.printLine("Student added successfully.");
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Displays all registered students on the console.
     */
    private static void viewAllStudents() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        studentService.viewAllStudents();
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for a student ID and displays the matching student if found.
     */
    private static void searchStudentById() {
        String id = InputUtil.readLine("Enter student ID to search:");
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                InputUtil.printLine(student.toString());
            }
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for a student ID and deactivates the corresponding student.
     */
    private static void deactivateStudent() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String id = InputUtil.readLine("Enter student ID to deactivate:");
        try {
            studentService.deactivateStudent(id);
            InputUtil.printLine("Student deactivated successfully.");
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Reads course details from the console and creates a new course.
     */
    private static void addNewCourse() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String name = InputUtil.readLine("Course Name:");
        String description = InputUtil.readLine("Course description:");
        int duration = 0;
        try {
            duration = InputUtil.readInt("Duration in Weeks:");
        } catch (InvalidInputException e) {
            InputUtil.printLine(e.getMessage());
        }
        courseService.addCourse(name, description, duration);
        InputUtil.printLine("Course added successfully.");
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Displays all courses currently registered in the system.
     */
    public static void viewAllCourses() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        courseService.viewAllCourses();
    }

    /**
     * Prompts for a course ID and deactivates the matching course.
     */
    public static void deactivateCourse() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String id = InputUtil.readLine("Enter course ID to deactivate:");
        try {
            courseService.deactivateCourse(id);
            InputUtil.printLine("Course deactivated successfully.");
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Reads student and course IDs and enrolls the student in the course.
     */
    private static void enrollStudent() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String studentId = InputUtil.readLine("Student ID to enroll:");
        String courseId = InputUtil.readLine("Course ID to enroll in:");
        try {
            enrollmentService.enroll(studentId, courseId);
            InputUtil.printLine("Student " + studentId + " enrolled for course " + courseId);
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for a student ID and displays that student's enrollments.
     *
     * @throws EntityNotFoundException when the specified student does not exist
     */
    public static void viewAllEnrollmentsForStudent() throws EntityNotFoundException {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        try {
            String studentId = InputUtil.readLine("Enter student ID to view enrollments:");
            enrollmentService.viewEnrollmentsForStudent(studentId);
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for an enrollment ID and marks the enrollment as completed.
     *
     * @throws EntityNotFoundException when the enrollment cannot be found
     */
    public static void markEnrollmentAsCompleted() throws EntityNotFoundException {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String enrollmentId = InputUtil.readLine("Enter enrollment ID to mark as completed:");
        try {
            enrollmentService.markEnrollmentAsCompleted(enrollmentId);
            InputUtil.printLine("Enrollment " + enrollmentId + " marked as completed.");
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }
}
