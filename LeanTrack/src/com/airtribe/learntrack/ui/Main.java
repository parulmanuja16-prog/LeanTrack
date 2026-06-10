package com.airtribe.learntrack.ui;

import java.util.List;

import com.airtribe.learntrack.constants.LeanTrackConstants;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.exception.NotValidDataException;
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
                    activateDeactivateCourse();
                    break;
                case 8:
                    enrollStudent();
                    break;
                case 9:
                    viewAllEnrollmentsForStudent();
                    break;
                case 10:
                    changeEnrollmentStatus();
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
        
        String batch = InputUtil.readLine("Student batch:");
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        if (firstName.isEmpty() || lastName.isEmpty() || batch.isEmpty()) {
            InputUtil.printLine("First Name, Last Name and Batch fields are required. Student not added.");
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
        List<Student> students = studentService.viewAllStudents();
        if (students.isEmpty()) {
            InputUtil.printLine("No students available.");
            return;
        }
        students.forEach(System.out::println);        
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
        
        try {
            int duration = InputUtil.readInt("Duration in Weeks:");
            if(duration < 0) {
                InputUtil.printLine("Please enter a non-negative number for duration of weeks.");
                return;
            }
            courseService.addCourse(name, description, duration);
        } catch (InvalidInputException e) {
            InputUtil.printLine(e.getMessage());
        }
        
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
        List<Course> courses = courseService.viewAllCourses();
         if(courses.isEmpty()){
            System.out.println("No courses available.");
            return;
        }
        courses.forEach(System.out::println);
        InputUtil.printLine("##################################################################");        
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for a course ID and deactivates the matching course.
     */
    public static void activateDeactivateCourse() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String id = InputUtil.readLine("Enter course ID to activate/deactivate:");
        int choice= InputUtil.readInt("Enter 1 to activate, 2 to deactivate");
        try {
           String result = courseService.changeCourseStatus(id, choice);
           InputUtil.printLine("Course "+id+": is "+result+" successfully.");
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        } catch(NotValidDataException e){
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
        catch(NotValidDataException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for a student ID and displays that student's enrollments.
     * <p>
     * Validates the student exists and then prints each enrollment record for
     * that student.
     */
    public static void viewAllEnrollmentsForStudent() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        try {
            String studentId = InputUtil.readLine("Enter student ID to view enrollments:");
            List<Enrollment> enrollments = enrollmentService.viewEnrollmentsForStudent(studentId);
            if(enrollments.size()==0){
                InputUtil.printLine("No enrollments found for student "+ studentId +" .");
            }
            enrollments.forEach(e -> {
                Course course = courseService.getCourseById(e.getCourseId());
                System.out.println("Enrollment ID: " + e.getId() + ", Student: " + studentId + ", Course: " + course.getCourseName() + ", Status: " + e.getStatus());
               
            });
    
        } catch (EntityNotFoundException e) {
            InputUtil.printLine(e.getMessage());
        }
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }

    /**
     * Prompts for an enrollment ID and updates the enrollment status.
     * <p>
     * The user may choose to cancel or complete an existing enrollment.
     */
    public static void changeEnrollmentStatus() {
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
        String enrollmentId = InputUtil.readLine("Enter enrollment ID to mark as completed/cancelled:");
        int choice = InputUtil.readInt("Enter 1 for Cancellation and 2 for Completion:");
        if(choice==1 || choice==2){
             try {
                String result = enrollmentService.changeEnrollmentStatus(enrollmentId,choice);
                InputUtil.printLine("Enrollment " + enrollmentId + " marked as "+result+ " .");
            } catch (EntityNotFoundException e) { 
                InputUtil.printLine(e.getMessage());
            }
        }else{
            InputUtil.printLine("Enetered Value is not valid.");
        }

       
        InputUtil.printLine("##################################################################");
        InputUtil.printLine("##################################################################");
    }
}
