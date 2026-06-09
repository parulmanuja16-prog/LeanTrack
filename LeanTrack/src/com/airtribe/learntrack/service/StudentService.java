package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing students in the LearnTrack application.
 * <p>
 * This class maintains an in-memory list of {@link Student} objects and
 * provides CRUD operations for adding, retrieving, updating, deactivating,
 * and removing students.
 */
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    /**
     * Creates and stores a new student using the provided personal details.
     *
     * @param firstName the student's first name
     * @param lastName  the student's last name
     * @param email     the student's email address, or {@code null} if none
     * @param batch     the batch associated with the student
     */
    public void addNewStudent(String firstName, String lastName, String email, String batch) {
        Student student = null;
        if (email == null) {
            student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, batch, true);
        } else
            student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch, true);
        students.add(student);
    }

    /**
     * Prints all stored students to standard output.
     * <p>
     * If no students are available, a notification message is displayed.
     */
    public void viewAllStudents() {
        if (students.isEmpty()) {
            InputUtil.printLine("No students available.");
            return;
        }
        students.forEach(System.out::println);
    }

    /**
     * Finds a student by ID.
     *
     * @param id the student identifier
     * @return the matched {@link Student}
     * @throws EntityNotFoundException if no student exists for the given ID
     */
    public Student getStudentById(String id) throws EntityNotFoundException {
        Student student = null;
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                student = s;
                break;
            }
        }
        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        return student;
    }

    /**
     * Deactivates a student account by marking the student as inactive.
     *
     * @param id the student identifier
     * @throws EntityNotFoundException if the student cannot be found
     */
    public void deactivateStudent(String id) throws EntityNotFoundException {

        try {
            Student student = getStudentById(id);
            student.setActive(false);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cannot deactivate as Student not found with ID: " + id);
        }
    }

    /**
     * Updates the email address for an existing student.
     *
     * @param id       the student identifier
     * @param newEmail the new email address
     * @throws EntityNotFoundException if the student cannot be found
     */
    public void updateStudent(String id, String newEmail) throws EntityNotFoundException {
        try {
            Student student = getStudentById(id);
            student.setEmail(newEmail);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cannot update email as Student not found with ID: " + id);
        }
    }

    /**
     * Updates the batch and email address for an existing student.
     *
     * @param id       the student identifier
     * @param batch    the updated batch name
     * @param newEmail the updated email address
     * @throws EntityNotFoundException if the student cannot be found
     */
    public void updateStudent(String id, String batch, String newEmail) throws EntityNotFoundException {
        try {
            Student student = getStudentById(id);
            student.setEmail(newEmail);
            student.setBatch(batch);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cannot update email and batch as Student not found with ID: " + id);
        }
    }

    /**
     * Removes a student from the in-memory store.
     *
     * @param id the student identifier
     * @throws EntityNotFoundException if the student cannot be found
     */
    public void removeStudent(String id) throws EntityNotFoundException {
        Student student = null;
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                student = s;
                break;
            }
        }
        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        students.remove(student);
    }

}
