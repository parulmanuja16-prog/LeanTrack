package com.airtribe.learntrack.exception;

/**
 * Exception thrown when a requested entity cannot be found.
 * <p>
 * This runtime exception is used across the LearnTrack application when a
 * requested student, course, or enrollment cannot be located by its identifier.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message explaining why the entity was not found
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
