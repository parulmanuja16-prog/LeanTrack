# LeanTrack Class Diagram

This document provides a visual representation of the class structure and relationships in the LeanTrack application.

## Class Hierarchy and Relationships

```mermaid
classDiagram
    class Person {
        -String id
        -String firstname
        -String lastname
        -String email
        +getId() String
        +getFirstname() String
        +getLastname() String
        +getEmail() String
        +setFirstname(String)
        +setLastname(String)
        +setEmail(String)
        +getFullName() String
        +toString() String
    }

    class Student {
        -String batch
        -boolean active
        +getBatch() String
        +setBatch(String)
        +isActive() boolean
        +setActive(boolean)
    }

    class Trainer {
        -String expertise
        +getExpertise() String
        +setExpertise(String)
    }

    class Course {
        -String id
        -String courseName
        -String description
        -int durationInWeeks
        -boolean active
        +getId() String
        +getCourseName() String
        +setCourseName(String)
        +getDescription() String
        +setDescription(String)
        +getDurationInWeeks() int
        +setDurationInWeeks(int)
        +isActive() boolean
        +setActive(boolean)
        +toString() String
    }

    class Enrollment {
        -String id
        -String studentId
        -String courseId
        -LocalDate enrollmentDate
        -EnrollmentStatus status
        +getId() String
        +setId(String)
        +getStudentId() String
        +setStudentId(String)
        +getCourseId() String
        +setCourseId(String)
        +getEnrollmentDate() LocalDate
        +setEnrollmentDate(LocalDate)
        +getStatus() EnrollmentStatus
        +setStatus(EnrollmentStatus)
    }

    class StudentService {
        -List~Student~ students
        +addNewStudent(String, String, String, String)
        +getStudentById(String) Student
        +getAllStudents() List~Student~
        +updateStudent(Student)
        +deactivateStudent(String)
        +removeStudent(String)
    }

    class CourseService {
        -List~Course~ courses
        +addCourse(String, String, int)
        +getCourseById(String) Course
        +getAllCourses() List~Course~
        +updateCourse(Course)
        +deactivateCourse(String)
    }

    class EnrollmentService {
        -List~Enrollment~ enrollments
        -StudentService studentService
        -CourseService courseService
        +enrollStudentToCourse(String, String) Enrollment
        +getEnrollmentsByStudentId(String) List~Enrollment~
        +updateEnrollmentStatus(String, EnrollmentStatus)
        +getAllEnrollments() List~Enrollment~
    }

    class IdGenerator {
        +getNextStudentId() String
        +getNextCourseId() String
        +getNextEnrollmentId() String
    }

    class InputValidation {
        +validateEmail(String) boolean
        +validateName(String) boolean
        +validateInputs() void
    }

    class LeanTrackConstants {
        +EnrollmentStatus ACTIVE
        +EnrollmentStatus INACTIVE
        +EnrollmentStatus COMPLETED
    }

    %% Inheritance Relationships
    Student --|> Person : extends
    Trainer --|> Person : extends

    %% Association Relationships
    StudentService --> Student : manages
    CourseService --> Course : manages
    EnrollmentService --> Enrollment : manages
    EnrollmentService --> StudentService : uses
    EnrollmentService --> CourseService : uses
    Enrollment --> Student : references (via studentId)
    Enrollment --> Course : references (via courseId)

    %% Utility and Constants
    StudentService --> IdGenerator : uses
    CourseService --> IdGenerator : uses
    EnrollmentService --> IdGenerator : uses
    Enrollment --> LeanTrackConstants : uses
```

## Relationship Summary

### Inheritance
- **Student** and **Trainer** both extend **Person**
  - Inherit common properties: id, firstname, lastname, email
  - Student adds: batch, active
  - Trainer adds: expertise

### Aggregation/Composition
- **StudentService** manages a list of **Student** objects
- **CourseService** manages a list of **Course** objects
- **EnrollmentService** manages a list of **Enrollment** objects

### Dependencies
- **EnrollmentService** depends on both **StudentService** and **CourseService**
  - Used to validate student and course existence during enrollment
- **Enrollment** maintains foreign key references to **Student** and **Course**
  - studentId references Student
  - courseId references Course

### Utility Dependencies
- All services use **IdGenerator** to create unique identifiers
- **Enrollment** references **LeanTrackConstants** for EnrollmentStatus enum values
- Input validation utilities are used by services for data validation

## Key Design Patterns

1. **Service Layer Pattern**: Separation of business logic in service classes
2. **Entity-Service Architecture**: Clear separation between data models and business logic
3. **Dependency Injection**: EnrollmentService receives StudentService and CourseService as dependencies
4. **ID Generation**: Centralized ID generation through IdGenerator utility class
