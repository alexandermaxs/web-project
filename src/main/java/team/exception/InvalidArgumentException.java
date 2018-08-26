package team.exception;

public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message, Exception e) {
        super(message, e);
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}
