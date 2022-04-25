package ru.mirea.task23.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.task23.models.Role;
import ru.mirea.task23.models.User;
import ru.mirea.task23.repositories.RoleRepository;
import ru.mirea.task23.repositories.UserRepository;

import java.util.Arrays;
import java.util.Optional;

@Component
public class SetupAuthLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean setupFlag = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupFlag)
            return;

        String[] rolesNames = { "USER", "ADMIN" };
        for (String name : rolesNames) {
            createRoleIfNotFound(name);
        }

        Role adminRole = createRoleIfNotFound("ADMIN");
        User adminUser = new User(
                "admin",
                "admin@admin.admin",
                passwordEncoder.encode("admin")
        );
        adminUser.setRoles(Arrays.asList(adminRole));
        userRepository.save(adminUser);

        setupFlag = true;
    }

    @Transactional
    public Role createRoleIfNotFound(String name) {
        Optional<Role> optRole = roleRepository.findByName(name);
        Role role;

        if (optRole.isEmpty()) {
            role = new Role(name);
            roleRepository.save(role);
        } else {
            role = optRole.get();
        }

        return role;
    }
}
