package pl.dahdev.managementapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dahdev.managementapp.exception.RoleException;
import pl.dahdev.managementapp.model.Role;
import pl.dahdev.managementapp.repository.RoleRepository;
import pl.dahdev.managementapp.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(@Autowired RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(String roleName) {
        Role role = new Role();
        role.setRole(roleName);
        roleRepository.save(role);
    }

    @Override
    public Role findById(long id) throws RoleException {
        return roleRepository.findById(id).orElseThrow(() -> new RoleException(RoleException.ROLE_NOT_FOUND));
    }

    @Override
    public Role findByRole(String role) throws RoleException {
        return roleRepository.findByRole(role).orElseThrow(() -> new RoleException(RoleException.ROLE_NOT_FOUND));
    }
}
