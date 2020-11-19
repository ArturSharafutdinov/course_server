package ru.kinopoisk.server.services.mappers;

import org.springframework.stereotype.Component;
import ru.kinopoisk.server.persistence.domain.User;
import ru.kinopoisk.server.persistence.dto.UserDto;

@Component
public class UserMapper {

    public User mapToEntity(UserDto userDto) {
        User user = new User();

        user.setUsername(
                userDto.getUsername()
        );
        user.setPassword(
                userDto.getPassword()
        );
        user.setEmail(
                userDto.getEmail()
        );
        user.setFirstName(
                userDto.getFirstName()
        );
        user.setMiddleName(
                userDto.getMiddleName()
        );

        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(
                user.getId()
        );
        userDto.setUsername(
                user.getUsername()
        );
        userDto.setPassword(
                user.getPassword()
        );
        userDto.setEmail(
                user.getEmail()
        );
        userDto.setFirstName(
                user.getFirstName()
        );
        userDto.setMiddleName(
                user.getMiddleName()
        );


        return userDto;
    }
}
