package pl.dahdev.managementapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dahdev.managementapp.model.Activation;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ActivationRepositoryTest {

    private static final String ACTIVATION_CODE = "activation_code";

    @Autowired
    ActivationRepository activationRepository;

    @Test
    public void whenGivenActivationCodeShouldReturnActivation() {
        Activation activation = new Activation();
        activation.setActivationCode(ACTIVATION_CODE);
        activationRepository.save(activation);

        Optional<Activation> actualActivation = activationRepository.findByActivationCode(ACTIVATION_CODE);
        assertThat(actualActivation).hasValue(activation);
    }

    @Test
    public void whenGivenIdShouldReturnRole() {
        Activation activation = new Activation();
        activation.setActivationCode(ACTIVATION_CODE);
        long id = activationRepository.save(activation).getId();

        Optional<Activation> actualActivation = activationRepository.findById(id);
        assertThat(actualActivation).hasValue(activation);
    }

}