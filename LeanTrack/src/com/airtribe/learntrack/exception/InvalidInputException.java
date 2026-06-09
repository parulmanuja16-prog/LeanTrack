package com.airtribe.learntrack.exception;

/**
 * Exception thrown when console input cannot be parsed or validated.
 * <p>
 * This runtime exception is used by LearnTrack input validation utilities to
 * signal invalid user input such as a non-numeric value where an integer is expected.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message describing the invalid input
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
