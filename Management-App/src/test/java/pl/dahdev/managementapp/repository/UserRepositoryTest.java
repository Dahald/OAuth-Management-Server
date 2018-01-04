package pl.dahdev.managementapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dahdev.managementapp.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    @Autowired
    UserRepository userRepository;

    @Test
    public void whenGivenUserNameShouldReturnUser() {
        User user = new User(USERNAME, PASSWORD);
        userRepository.save(user);

        Optional<User> actualRole = userRepository.findByUsername(USERNAME);
        assertThat(actualRole).hasValue(user);
    }

}
