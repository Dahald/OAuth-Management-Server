package pl.dahdev.managementapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.dahdev.managementapp.exception.UserException;
import pl.dahdev.managementapp.model.User;
import pl.dahdev.managementapp.repository.UserRepository;
import pl.dahdev.managementapp.service.UserService;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        Assert.notNull(userRepository, "UserRepository is a null!");
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        Assert.notNull(bCryptPasswordEncoder, "BCryptPasswordEncoder is a null!");
    }

    @Override
    public void addUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) throws UserException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws UserException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User actualUser = user.get();
            if (bCryptPasswordEncoder.matches(oldPassword, actualUser.getPassword())) {
                actualUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
                userRepository.save(actualUser);
            } else {
                throw new UserException(UserException.WRONG_PASSWORD);
            }
        } else {
            throw new UserException(UserException.USER_NOT_FOUND);
        }
    }

    @Override
    public void disableUser(long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User actualUser = user.get();
            actualUser.setEnabled(0);
            userRepository.save(actualUser);
        } else {
            throw new UserException(UserException.USER_NOT_FOUND);
        }
    }
}
