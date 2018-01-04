package pl.dahdev.managementapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dahdev.managementapp.model.Role;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RoleRepositoryTest {

    private static final String ADMIN = "ROLE_ADMIN";

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void whenGivenRoleNameShouldReturnRole() {
        Role role = new Role(ADMIN);
        roleRepository.save(role);

        Optional<Role> actualRole = roleRepository.findByRole(ADMIN);
        assertThat(actualRole).hasValue(role);
    }

    @Test
    public void whenGivenIdShouldReturnRole() {
        Role role = new Role(ADMIN);
        long id = roleRepository.save(role).getId();

        Optional<Role> actualRole = roleRepository.findById(id);
        assertThat(actualRole).hasValue(role);
    }
}
