package team.exception;

public class PersistentException extends Exception {

    public PersistentException(String message) {
        super(message);
    }

    public PersistentException(Exception e) {
        super(e);
    }
}
