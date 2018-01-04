package pl.dahdev.managementapp.exception;

public class RoleException extends Exception {

    public static final String ROLE_NOT_FOUND = "Role not found!";

    public RoleException(String message) {
        super(message);
    }

}

