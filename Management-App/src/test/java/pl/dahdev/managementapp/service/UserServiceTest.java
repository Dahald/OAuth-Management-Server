package pl.dahdev.managementapp.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.dahdev.managementapp.exception.UserException;
import pl.dahdev.managementapp.model.User;
import pl.dahdev.managementapp.repository.UserRepository;
import pl.dahdev.managementapp.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private static final String USERNAME = "username";
    private static final String WRONG_USERNAME = "wrongusername";
    private static final String PASSWORD = "admin";
    private static final String WRONG_PASSWORD = "wong_pass";
    private static final String NEW_PASSWORD = "new_pass";
    private static final long ID = 500;
    private static final long WRONG_ID = 1000;
    private static final String ENCRYPTED_PASSWORD = "$2a$10$5EoNWcI.mW3k5EoyfVOieO/EoFZ0nMVNWFkaB4SnR2PDR.fefj66K";

    private UserService userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserServiceImpl(userRepository, new BCryptPasswordEncoder());
    }

    @Test
    public void testMockCreation() {
        assertNotNull(userRepository);
    }

    @Test
    public void whenGivenUsernameShouldReturnUser() throws UserException {
        User user = new User(USERNAME, PASSWORD);
        user.setId(ID);
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));
        User actualUser = userService.findByUsername(USERNAME);

        verify(userRepository).findByUsername(USERNAME);
        assertEquals(user, actualUser);
    }

    @Test
    public void whenGivenWrongUsernameShouldThrowException() throws UserException {
        expectedException.expect(UserException.class);
        expectedException.expectMessage(UserException.USER_NOT_FOUND);
        when(userRepository.findByUsername(WRONG_USERNAME)).thenReturn(Optional.empty());
        userService.findByUsername(WRONG_USERNAME);
    }

    @Test
    public void whenGivenUserIdShouldDisableUser() throws UserException {
        User user = new User(USERNAME, PASSWORD);
        user.setId(ID);

        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        userService.disableUser(ID);

        verify(userRepository).findById(ID);
        verify(userRepository).save(user);
    }

    @Test
    public void whenGivenWrongUserIdShouldThrownExceptionInsteadOfDisableUser() throws UserException {
        expectedException.expect(UserException.class);
        expectedException.expectMessage(UserException.USER_NOT_FOUND);
        when(userRepository.findById(WRONG_ID)).thenReturn(Optional.empty());
        userService.disableUser(WRONG_ID);
    }

    @Test
    public void whenGivenUsernameOldPasswordNewPasswordShouldChangePassword() throws UserException {
        User user = new User(USERNAME, ENCRYPTED_PASSWORD);
        user.setId(ID);
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));
        userService.changePassword(USERNAME, PASSWORD, NEW_PASSWORD);
        verify(userRepository).findByUsername(USERNAME);
        verify(userRepository).save(user);
    }

    @Test
    public void whenGivenWrongPasswordShouldThrownExceptionInsteadOfChangePassword() throws UserException {
        expectedException.expect(UserException.class);
        expectedException.expectMessage(UserException.WRONG_PASSWORD);
        User user = new User(USERNAME, ENCRYPTED_PASSWORD);
        user.setId(ID);
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));
        userService.changePassword(USERNAME, WRONG_PASSWORD, NEW_PASSWORD);
    }

    @Test
    public void whenGivenWrongUsernameShouldThrownExceptionInsteadOfChangePassword() throws UserException {
        expectedException.expect(UserException.class);
        expectedException.expectMessage(UserException.USER_NOT_FOUND);

        when(userRepository.findByUsername(WRONG_USERNAME)).thenReturn(Optional.empty());
        userService.changePassword(WRONG_USERNAME, PASSWORD, NEW_PASSWORD);
    }
}
