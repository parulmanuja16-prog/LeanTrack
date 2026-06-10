package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.NotValidDataException;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing course data in the LearnTrack application.
 * <p>
 * This class stores courses in memory and provides operations to add,
 * retrieve, list, and deactivate courses.
 */
public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    /**
     * Creates a new course and saves it in the internal course list.
     *
     * @param name        the course name
     * @param description a description of the course
     * @param duration    the duration of the course in weeks
     */
    public void addCourse(String name, String description, int duration) {
        courses.add(new Course(name, description, duration));
    }

    /**
     * Retrieves a course by its identifier.
     *
     * @param id the course identifier
     * @return the matching {@link Course}
     * @throws EntityNotFoundException if no course is found with the provided ID
     */
    public Course getCourseById(String id) throws EntityNotFoundException {
       for(Course c : courses) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course not found with ID: " + id);
    }

    /**
     * Returns all available courses in the system.
     *
     * @return the list of available courses
     */
    public List<Course> viewAllCourses() {
        return courses;
    }

    /**
     * Updates a course's active status.
     *
     * @param courseId the course identifier
     * @param status   {@code 1} to activate the course, {@code 2} to deactivate it
     * @return a message indicating the new status
     * @throws EntityNotFoundException if the course cannot be found
     * @throws NotValidDataException   if the status is invalid or the requested state is already set
     */
    public String changeCourseStatus(String courseId, int status) throws EntityNotFoundException, NotValidDataException{
        Course course = this.getCourseById(courseId);
        if(status==1){
            if(course.isActive())
                throw new NotValidDataException("Course is already active");
            else
                course.setActive(true);
            return "Activated";
        }
        else if(status==2){
            if(!course.isActive()){
                throw new NotValidDataException("Course is already deactivated.");
            }else
                course.setActive(false);
                return "Deactivated";
        }else{
            throw new NotValidDataException("Invalid choice for activatin/deactivation");
        }
       
    }

}