package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.NotValidDataException;
import com.airtribe.learntrack.constants.LeanTrackConstants;
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
     * @throws EntityNotFoundException if the student or course cannot be found or is inactive
     * @throws NotValidDataException   if the student is already enrolled in the course
     */
    public void enroll(String studentId, String courseId) throws EntityNotFoundException, NotValidDataException {
        Student student = null;
        Course course = null;
        
        student = studentService.getStudentById(studentId); // Validate student exists
         if(student == null) {
             throw new EntityNotFoundException("Cannot enroll as Student not found with ID: " + studentId);
         }     
       
        if(!student.isActive()) {
            throw new EntityNotFoundException("Cannot enroll as Student with ID: " + studentId + " is not active.");
        }
        
        course = courseService.getCourseById(courseId); // Validate course exists
        if(course == null) {
            throw new EntityNotFoundException("Cannot enroll as Course not found with ID: " + courseId);
        }
        if(!course.isActive()) {
                throw new EntityNotFoundException("Cannot enroll as Course with ID: " + courseId + " is not active.");
        }
        
         // Check for existing enrollment
        for(Enrollment e : enrollments) {
            if (e.getStudentId().equals(studentId) && e.getCourseId().equals(courseId)) {
                throw new NotValidDataException ("Student is already enrolled in this course.");
            }
        }
        Enrollment enrollment = new Enrollment(studentId, courseId);
        enrollments.add(enrollment);
    }

    /**
     * Returns all enrollments for a specific student.
     * <p>
     * This method validates that the referenced student exists and then returns
     * all enrollment records for that student.
     *
     * @param studentId the ID of the student whose enrollments should be returned
     * @return the list of enrollments for the student
     * @throws EntityNotFoundException if the student cannot be found
     */
    public List<Enrollment> viewEnrollmentsForStudent(String studentId) throws EntityNotFoundException {
        final Student student;
        List<Enrollment> stuEnrollments = new ArrayList<>();
        try{
            student = studentService.getStudentById(studentId); // Validate student exists
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Cannot view enrollments as Student not found with ID:"+ studentId);
        }
        for(Enrollment enrollment:enrollments){
            if(enrollment.getStudentId().equalsIgnoreCase(studentId)){
                stuEnrollments.add(enrollment);
            }
        }
      return stuEnrollments;
    }

    /**
     * Marks an enrollment as completed or cancelled.
     *
     * @param enrollmentId the ID of the enrollment to update
     * @param choice       {@code 1} to cancel the enrollment, {@code 2} to complete it
     * @return the new enrollment status as a string
     * @throws EntityNotFoundException if no enrollment exists with the specified ID
     */
    public String changeEnrollmentStatus(String enrollmentId, int choice) throws EntityNotFoundException {
        Enrollment enrollment = enrollments.stream()
            .filter(e -> e.getId().equals(enrollmentId))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId));
            EnrollmentStatus status = choice==1?EnrollmentStatus.CANCELLED:EnrollmentStatus.COMPLETED;
        enrollment.setStatus(status);
        return status.toString();
    }
}
