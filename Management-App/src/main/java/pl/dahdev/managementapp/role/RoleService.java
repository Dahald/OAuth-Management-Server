package pl.dahdev.managementapp.role;


public interface RoleService {

    void addRole(String role);

    Role findById(long id) throws RoleException;

    Role findByRole(String role) throws RoleException;

}
