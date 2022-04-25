package ru.mirea.task23.services;

import ru.mirea.task23.models.User;
import ru.mirea.task23.models.dto.UserLoginDTO;
import ru.mirea.task23.models.dto.UserRegistrationDTO;

public interface IUserService {
    User login(UserLoginDTO loginDTO);
    User register(UserRegistrationDTO registrationDTO);
}
