import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mirea.task23.models.Role;
import ru.mirea.task23.models.User;
import ru.mirea.task23.models.dto.UserLoginDTO;
import ru.mirea.task23.models.dto.UserRegistrationDTO;
import ru.mirea.task23.repositories.RoleRepository;
import ru.mirea.task23.repositories.UserRepository;
import ru.mirea.task23.security.services.UserDetailsImpl;
import ru.mirea.task23.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private User user;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = new User("username", "email", "password");
        user.setRoles(Arrays.asList(new Role("USER")));
    }

    @Test
    public void loginTest() {
        Mockito.when(authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(user));

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        UserLoginDTO userLoginDTO = new UserLoginDTO(user.getUsername(), user.getPassword());

        User newUser = userService.login(userLoginDTO);

        Assertions.assertEquals(newUser.getUsername(), user.getUsername());
        Assertions.assertEquals(newUser.getEmail(), user.getEmail());
    }

    @Test
    public void registerTest() {
        String otherUsername = "otherUsername";
        String otherEmail = "otherEmail";

        Mockito.when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        Mockito.when(userRepository.existsByUsername(otherUsername)).thenReturn(true);
        Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(otherEmail)).thenReturn(true);

        Role role = user.getRoles().get(0);
        Mockito.when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(role));

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        List<String> rolesNames = user.getRoles().stream()
                .map(roleObj -> roleObj.getName())
                .collect(Collectors.toList());

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                rolesNames
        );
        UserRegistrationDTO userRegistrationDTOWithOtherUsername = new UserRegistrationDTO(
                otherUsername,
                user.getEmail(),
                user.getPassword(),
                rolesNames
        );
        UserRegistrationDTO userRegistrationDTOWithOtherEmail = new UserRegistrationDTO(
                user.getUsername(),
                otherEmail,
                user.getPassword(),
                rolesNames
        );

        User newUser = userService.register(userRegistrationDTO);

        Assertions.assertEquals(newUser.getUsername(), user.getUsername());
        Assertions.assertEquals(newUser.getEmail(), user.getEmail());

        IllegalStateException exceptionUsername = Assertions.assertThrows(IllegalStateException.class, () -> {
            userService.register(userRegistrationDTOWithOtherUsername);
        });
        IllegalStateException exceptionEmail = Assertions.assertThrows(IllegalStateException.class, () -> {
            userService.register(userRegistrationDTOWithOtherEmail);
        });

        Assertions.assertEquals("Username is already in use!", exceptionUsername.getMessage());
        Assertions.assertEquals("Email is already in use!", exceptionEmail.getMessage());
    }
}
