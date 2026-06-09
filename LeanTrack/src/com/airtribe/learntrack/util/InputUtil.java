package com.airtribe.learntrack.util;

import java.util.Scanner;

import com.airtribe.learntrack.exception.InvalidInputException;

/**
 * Utility class for reading console input and printing output.
 * <p>
 * This class centralizes common input/output operations for the LearnTrack
 * console UI and is not instantiable.
 */
public final class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    /**
     * Reads a line of text from standard input after displaying a prompt.
     *
     * @param prompt the text displayed before reading input
     * @return the trimmed user input line
     */
    public static String readLine(String prompt) {
        System.out.print(prompt + " ");
        return SCANNER.nextLine().trim();
    }

    /**
     * Reads an integer from standard input after displaying a prompt.
     * <p>
     * Invalid input is rejected and the user is prompted again until a valid
     * integer value is entered.
     *
     * @param prompt the text displayed before reading input
     * @return the parsed integer value
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                String input = readLine(prompt);
                return InputValidation.validateInt(input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints a message to standard output.
     *
     * @param message the message to print
     */
    public static void printLine(String message) {
        System.out.println(message);
    }
}
