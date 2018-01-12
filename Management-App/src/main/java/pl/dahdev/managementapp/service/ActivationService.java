package pl.dahdev.managementapp.service;

import pl.dahdev.managementapp.exception.ActivationException;
import pl.dahdev.managementapp.model.Activation;
import pl.dahdev.managementapp.model.User;

public interface ActivationService {

    void addActivation(String activationCode, User user);

    String generateActivationCode();

    Activation findById(long id) throws ActivationException;

    Activation findByActivationCode(String activationCode) throws ActivationException;

    void deleteActivation(Activation activation);

}
