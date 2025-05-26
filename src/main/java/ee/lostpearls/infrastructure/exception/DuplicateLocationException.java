package ee.lostpearls.infrastructure.exception;

public class DuplicateLocationException extends RuntimeException {
    public DuplicateLocationException() {
        super("A location with the same name and coordinates already exists in this county.");
    }
}