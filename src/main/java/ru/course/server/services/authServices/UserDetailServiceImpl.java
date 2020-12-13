package ru.course.server.services.authServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.course.server.persistence.domain.User;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        final User user = userService.findByEmail(email);

        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("No user found with email: " + email);
        }

        boolean isEnabled = user.getEnabled();
        if (!isEnabled) {
            throw new IllegalArgumentException("User is banned");
        }

        return userService.createUserDetails(user);
    }
}

