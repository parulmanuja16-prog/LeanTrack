package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.constants.LeanTrackConstants.EnrollmentStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that manages enrollment operations for students and courses.
 * <p>
 * This class holds an in-memory list of {@link Enrollment} objects and provides
 * methods to enroll students, query student-specific enrollments, and update
 * enrollment status.
 */
public class EnrollmentService {
    private List<Enrollment> enrollments = new ArrayList<>();
    private StudentService studentService;
    private CourseService courseService;

    /**
     * Constructs an EnrollmentService with the required student and course services.
     *
     * @param studentService the service used to validate student existence
     * @param courseService  the service used to validate course existence
     */
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Enrolls a student in a course.
     * <p>
     * The method validates that both the student and course exist before creating
     * a new {@link Enrollment} record. It also prevents duplicate enrollments for
     * the same student/course pair.
     *
     * @param studentId the ID of the student to enroll
     * @param courseId  the ID of the course to enroll in
     * @throws EntityNotFoundException if the student or course cannot be found,
     *                                 or when the student is already enrolled in the course
     */
    public void enroll(String studentId, String courseId) throws EntityNotFoundException {
         try{
            studentService.getStudentById(studentId); // Validate student exists
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Cannot enroll as Student not found with ID:"+ studentId);
        }
        try{
            courseService.getCourseById(courseId); // Validate course exists
        }catch(EntityNotFoundException e){
             throw new EntityNotFoundException("Cannot enroll as Course not found with ID:"+ courseId);
        }
        for(Enrollment e : enrollments) {
            if (e.getStudentId().equals(studentId) && e.getCourseId().equals(courseId)) {
                throw new EntityNotFoundException("Student is already enrolled in this course.");
            }
        }
        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId, java.time.LocalDate.now(), EnrollmentStatus.ACTIVE);
        enrollments.add(enrollment);
    }

    /**
     * Prints all enrollments for a specific student.
     * <p>
     * This method validates that the referenced student exists and then prints
     * each enrollment record for that student, resolving course details when available.
     *
     * @param studentId the ID of the student whose enrollments should be displayed
     * @throws EntityNotFoundException if the student cannot be found
     */
    public void viewEnrollmentsForStudent(String studentId) throws EntityNotFoundException {
        final Student student;
        try{
            student = studentService.getStudentById(studentId); // Validate student exists
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Cannot view enrollments as Student not found with ID:"+ studentId);
        }
        enrollments.stream()
            .filter(e -> e.getStudentId().equals(studentId))
            .forEach(e -> {
                try {
                    Course course = courseService.getCourseById(e.getCourseId());
                    System.out.println("Enrollment ID: " + e.getId() + ", Student: " + student.getDisplayName() + ", Course: " + course.getCourseName() + ", Status: " + e.getStatus());
                } catch (EntityNotFoundException ex) {
                    System.out.println("Enrollment ID: " + e.getId() + ", Course ID: " + e.getCourseId() + " (Course not found), Status: " + e.getStatus());
                }
            });
    }

    /**
     * Marks an enrollment as completed.
     *
     * @param enrollmentId the ID of the enrollment to update
     * @throws EntityNotFoundException if no enrollment exists with the specified ID
     */
    public void markEnrollmentAsCompleted(String enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = enrollments.stream()
            .filter(e -> e.getId().equals(enrollmentId))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId));
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }
}
