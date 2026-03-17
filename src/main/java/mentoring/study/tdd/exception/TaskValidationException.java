package mentoring.study.tdd.exception;

public class TaskValidationException extends RuntimeException {
    public TaskValidationException(String message) {
        super(message);
    }
}
