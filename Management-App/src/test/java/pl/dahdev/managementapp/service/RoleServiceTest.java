package pl.dahdev.managementapp.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.dahdev.managementapp.exception.RoleException;
import pl.dahdev.managementapp.model.Role;
import pl.dahdev.managementapp.repository.RoleRepository;
import pl.dahdev.managementapp.service.impl.RoleServiceImpl;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoleServiceTest {

    private static final String ADMIN = "ROLE_ADMIN";
    private static final String USER = "ROLE_USER";
    private static final long ID = 500;

    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        this.roleService = new RoleServiceImpl(roleRepository);
    }

    @Test
    public void testMockCreation() {
        assertNotNull(roleRepository);
    }

    @Test
    public void whenGivenRoleNameShouldReturnRole() throws RoleException {
        Role role = new Role(ADMIN);
        role.setId(ID);
        when(roleRepository.findByRole(ADMIN)).thenReturn(Optional.of(role));
        Role actualRole = roleService.findByRole(ADMIN);

        verify(roleRepository).findByRole(ADMIN);
        assertEquals(role, actualRole);
    }

    @Test(expected = RoleException.class)
    public void whenGivenWrongRoleNameShouldThrownException() throws RoleException {
        when(roleRepository.findByRole(USER)).thenReturn(Optional.empty());
        roleService.findByRole(USER);
    }

    @Test
    public void whenGivenRoleIdShouldReturnRole() throws RoleException {
        Role role = new Role(ADMIN);
        role.setId(ID);
        when(roleRepository.findById(ID)).thenReturn(Optional.of(role));
        Role actualRole = roleService.findById(ID);

        verify(roleRepository).findById(ID);
        assertEquals(role, actualRole);
    }

    @Test(expected = RoleException.class)
    public void whenGivenWrongRoleIdShouldThrownException() throws RoleException {
        when(roleRepository.findById(ID)).thenReturn(Optional.empty());
        roleService.findById(ID);
    }
}
