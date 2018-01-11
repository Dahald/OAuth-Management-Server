package pl.dahdev.managementapp.service;

import pl.dahdev.managementapp.exception.ActivationException;
import pl.dahdev.managementapp.model.Activation;
import pl.dahdev.managementapp.model.User;

public interface ActivationService {

    void activate(String activationCode) throws ActivationException;

    String generateActivationCode(User user);

    Activation findById(long id) throws ActivationException;

    Activation findByActivationCode(String activationCode) throws ActivationException;

}
