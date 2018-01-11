package pl.dahdev.managementapp.exception;

public class ActivationException extends Exception {

    public static final String ACTIVATION_NOT_FOUND = "Activation not found!";

    public ActivationException(String message) {
        super(message);
    }

}
