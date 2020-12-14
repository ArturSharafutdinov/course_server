package ru.course.server.controllers;


import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.course.server.persistence.dao.UserRepository;
import ru.course.server.persistence.domain.User;
import ru.course.server.persistence.dto.UserDto;
import ru.course.server.persistence.enums.Role;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SignUpController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/registration")
    public String registration(@Validated @RequestBody UserDto user){
        User newUser = userRepository.findByEmail(user.getEmail());
        if (newUser == null) {
            newUser = new User();
            newUser.setUsername(
                    user.getUsername()
            );
            newUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
            newUser.setEmail(
                    user.getEmail()
            );
            newUser.setFirstName(
                  user.getFirstName()
            );
            newUser.setMiddleName(
                   user.getMiddleName()
            );
            newUser.setRoles(
                    SetUtils.hashSet(Role.USER)
            );
            newUser.setEnabled(true);
             userRepository.save(newUser);
             return "Signed up successfully";

        }
        else{
            return "User with this email exists";
        }


    }

}
