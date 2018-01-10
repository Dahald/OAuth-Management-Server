package pl.dahdev.managementapp.service;

import pl.dahdev.managementapp.exception.UserException;
import pl.dahdev.managementapp.model.User;

import java.util.List;

public interface UserService {

    void addUser(String username, String password, String email);

    User findByUsername(String username) throws UserException;

    void changePassword(String username, String oldPassword, String newPassword) throws UserException;

    void disableUser(long id) throws UserException;

    void activateUser(User user);

    List<User> getAllUsers();

}
