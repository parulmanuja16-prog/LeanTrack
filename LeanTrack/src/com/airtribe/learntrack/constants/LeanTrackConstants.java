package com.airtribe.learntrack.constants;

/**
 * Application constants used by the LearnTrack project.
 * <p>
 * This class provides shared values and enumerations used by the UI and
 * service classes.
 */
public final class LeanTrackConstants {
    
    /**
     * Constant used to express cancellation actions.
     */
    public static final String CANCEL = "Cancel";

    /**
     * Constant used to express completion actions.
     */
    public static final String COMPLETE = "Complete";

    /**
     * Status values for an enrollment record.
     */
    public enum EnrollmentStatus {
        ACTIVE, COMPLETED, CANCELLED
    }

    private LeanTrackConstants() {
        // Prevent instantiation
    }

}
