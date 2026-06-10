package com.airtribe.learntrack.exception;

/**
 * Exception thrown when a requested operation violates business rules or
 * invalid data prevents completion.
 * <p>
 * This runtime exception is used in LearnTrack when operations such as
 * activating/deactivating courses or enrolling students fail due to invalid
 * state or duplicate data.
 */
public class NotValidDataException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message describing why the data is invalid
     */
    public NotValidDataException(String message) {
        super(message);
    }

}
