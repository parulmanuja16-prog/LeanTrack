package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

/**
 * Utility class for validating console input values in the LearnTrack application.
 * <p>
 * This class provides static validation helpers for email addresses, non-empty
 * strings, and integer parsing. It cannot be instantiated.
 */
public class InputValidation {
    private InputValidation() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns {@code true} when the provided email address matches a basic
     * email pattern.
     *
     * @param email the email address to validate
     * @return {@code true} if the email is syntactically valid, otherwise {@code false}
     */
    public static boolean isValidEmail(String email) {
        // Simple regex for email validation
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
        
    }

    /**
     * Returns {@code true} when the provided string is not blank.
     *
     * @param input the string to validate
     * @return {@code true} when the string is non-null and contains non-whitespace characters
     */
    public static boolean isNonEmptyString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Parses the provided input as an integer.
     *
     * @param input the string to parse
     * @return the parsed integer value
     * @throws InvalidInputException if the input is not a valid integer
     */
    public static int validateInt(String input) {
        try { 
            int num = Integer.parseInt(input);            
            return num;
        } catch (NumberFormatException e) {
           throw new InvalidInputException("Please enter a valid number.");
        }
    }

}
