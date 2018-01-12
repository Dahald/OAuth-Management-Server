package pl.dahdev.managementapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.dahdev.managementapp.exception.ActivationException;
import pl.dahdev.managementapp.model.Activation;
import pl.dahdev.managementapp.model.User;
import pl.dahdev.managementapp.repository.ActivationRepository;
import pl.dahdev.managementapp.service.ActivationService;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService {

    private static final int TOKEN_LENGTH = 1028;

    private ActivationRepository activationRepository;

    public ActivationServiceImpl(@Autowired ActivationRepository activationRepository) {
        this.activationRepository = activationRepository;
        Assert.notNull(activationRepository, "ActivationRepository is a null!");
    }

    @Override
    public void addActivation(String activationCode, User user) {
        Activation activation = new Activation(activationCode, user);
        activationRepository.save(activation);
    }

    @Override
    public String generateActivationCode() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16);
    }

    @Override
    public Activation findById(long id) throws ActivationException {
        return activationRepository.findById(id).orElseThrow(
                () -> new ActivationException(ActivationException.ACTIVATION_NOT_FOUND)
        );
    }

    @Override
    public Activation findByActivationCode(String activationCode) throws ActivationException {
        return activationRepository.findByActivationCode(activationCode).orElseThrow(
                () -> new ActivationException(ActivationException.ACTIVATION_NOT_FOUND)
        );
    }

    @Override
    public void deleteActivation(Activation activation) {
        activationRepository.delete(activation);
    }
}
