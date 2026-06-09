package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

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
     * @param duration    the duration of the course in weeks or hours
     */
    public void addCourse(String name, String description, int duration) {
        courses.add(new Course(IdGenerator.getNextCourseId(), name, description, duration,true));
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
     * Prints all available courses to standard output.
     * <p>
     * If no courses are registered, a message is displayed instead.
     */
    public void viewAllCourses() {
        if(courses.isEmpty()){
            System.out.println("No courses available.");
            return;
        }
        courses.stream().forEach(System.out::println);
    }

    /**
     * Deactivates a course by marking it inactive.
     *
     * @param courseId the course identifier
     * @throws EntityNotFoundException if the course cannot be found
     */
    public void deactivateCourse(String courseId){
        Course course = null;
        try {
            course = this.getCourseById(courseId);
            course.setActive(false);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cannot deactivate as Course not found with ID: " + courseId);
        }
    }
}
