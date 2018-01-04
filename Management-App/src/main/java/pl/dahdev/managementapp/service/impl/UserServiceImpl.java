package pl.dahdev.managementapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.dahdev.managementapp.component.UserPasswordEncoder;
import pl.dahdev.managementapp.exception.UserException;
import pl.dahdev.managementapp.model.User;
import pl.dahdev.managementapp.repository.UserRepository;
import pl.dahdev.managementapp.service.UserService;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserPasswordEncoder userPasswordEncoder;

    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired UserPasswordEncoder userPasswordEncoder) {
        this.userRepository = userRepository;
        Assert.notNull(userRepository, "UserRepository is a null!");
        this.userPasswordEncoder = userPasswordEncoder;
        Assert.notNull(userPasswordEncoder, "UserPasswordEncoder is a null!");
    }

    @Override
    public void addUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(userPasswordEncoder.encode(password));
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
            if (userPasswordEncoder.matches(oldPassword, actualUser.getPassword())) {
                actualUser.setPassword(userPasswordEncoder.encode(newPassword));
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
