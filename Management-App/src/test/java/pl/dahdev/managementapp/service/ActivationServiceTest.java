package pl.dahdev.managementapp.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.dahdev.managementapp.repository.ActivationRepository;
import pl.dahdev.managementapp.service.impl.ActivationServiceImpl;

import static org.junit.Assert.assertNotNull;

public class ActivationServiceTest {

    private ActivationService activationService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ActivationRepository activationRepository;


    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        this.activationService = new ActivationServiceImpl(activationRepository);
    }

    @Test
    public void whenGenerateActivationCodeOutputShouldNotBeNull() {
        assertNotNull(activationService.generateActivationCode());
    }
}
