package pl.dahdev.managementapp.service;


import pl.dahdev.managementapp.exception.RoleException;
import pl.dahdev.managementapp.model.Role;

public interface RoleService {

    void addRole(String role);

    Role findById(long id) throws RoleException;

    Role findByRole(String role) throws RoleException;

}
