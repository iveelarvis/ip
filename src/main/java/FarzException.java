/**
 * Represents an error caused by an invalid command entered in the Farz chatbot.
 */
public class FarzException extends Exception {

    /**
     * Creates an exception with a user-friendly explanation of the invalid command.
     *
     * @param message Explanation shown to the user.
     */
    public FarzException(String message) {
        super(message);
    }
}
