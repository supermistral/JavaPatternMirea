package ru.mirea.task23.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.task23.models.Role;
import ru.mirea.task23.models.User;
import ru.mirea.task23.models.dto.UserLoginDTO;
import ru.mirea.task23.models.dto.UserRegistrationDTO;
import ru.mirea.task23.repositories.RoleRepository;
import ru.mirea.task23.repositories.UserRepository;
import ru.mirea.task23.security.services.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(UserLoginDTO loginDTO) {
        log.info("User authentication with username: " + loginDTO.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userRepository.findByUsername(userDetails.getUsername()).get();
    }

    @Override
    @Transactional
    public User register(UserRegistrationDTO registrationDTO) {
        String username = registrationDTO.getUsername();
        String email = registrationDTO.getEmail();

        log.info("User registration with username: " + username + ", email: " + email);

        if (userRepository.existsByUsername(username)) {
            log.info("User with username: " + username + " is already exists");
            throw new IllegalStateException("Username is already in use!");
        }

        if (userRepository.existsByEmail(email)) {
            log.info("User with email: " + email + " is already exists");
            throw new IllegalStateException("Email is already in use!");
        }

        User user = new User(
                username,
                email,
                passwordEncoder.encode(registrationDTO.getPassword())
        );

        List<String> rolesNames = registrationDTO.getRolesNames();
        List<Role> roles = new ArrayList<>();

        if (rolesNames == null) {
            Role role = roleRepository
                    .findByName("USER")
                    .orElseThrow(() -> new IllegalStateException("USER Role is not found"));
            roles.add(role);
        } else {
            rolesNames.forEach(roleName -> {
                roles.add(roleRepository
                        .findByName(roleName)
                        .orElseThrow(() -> new IllegalStateException(roleName + " Role is not found"))
                );
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}
