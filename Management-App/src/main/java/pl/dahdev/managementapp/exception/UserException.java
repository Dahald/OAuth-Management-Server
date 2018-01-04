package pl.dahdev.managementapp.exception;

public class UserException extends Exception {

    public static final String USER_NOT_FOUND = "User not found!";
    public static final String WRONG_PASSWORD = "Wrong password!";

    public UserException(String message) {
        super(message);
    }
}
