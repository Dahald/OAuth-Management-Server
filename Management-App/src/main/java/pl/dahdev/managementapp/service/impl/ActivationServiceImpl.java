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
import pl.dahdev.managementapp.service.UserService;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService {

    private static final int TOKEN_LENGTH = 1028;

    private ActivationRepository activationRepository;
    private UserService userService;

    public ActivationServiceImpl(@Autowired ActivationRepository activationRepository, @Autowired UserService userService) {
        this.activationRepository = activationRepository;
        Assert.notNull(activationRepository, "ActivationRepository is a null!");
        this.userService = userService;
        Assert.notNull(userService, "UserService is a null!");
    }

    @Override
    public void activate(String activationCode) throws ActivationException {
        Optional<Activation> activationOptional = activationRepository.findByActivationCode(activationCode);
        if (activationOptional.isPresent()) {
            Activation activation = activationOptional.get();
            userService.activateUser(activation.getUser());
            activationRepository.delete(activation.getId());
        } else {
            throw new ActivationException(ActivationException.ACTIVATION_NOT_FOUND);
        }
    }

    @Override
    public String generateActivationCode(User user) {
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
}
