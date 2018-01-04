package pl.dahdev.managementapp.service;

import pl.dahdev.managementapp.exception.UserException;
import pl.dahdev.managementapp.model.User;

public interface UserService {

    void addUser(String username, String password);

    User findByUsername(String username) throws UserException;

    void changePassword(String username, String oldPassword, String newPassword) throws UserException;

    void disableUser(long id) throws UserException;

}
