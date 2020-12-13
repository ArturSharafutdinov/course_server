package ru.course.server.services.authServices;

import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.course.server.persistence.dao.UserRepository;
import ru.course.server.persistence.domain.User;
import ru.course.server.persistence.dto.JwtUserDto;
import ru.course.server.persistence.dto.UserDto;
import ru.course.server.persistence.enums.Role;
import ru.course.server.services.mappers.UserMapper;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    //TODO сделать когда - нибудь simple dao через дженерики save/update/delete/ и тд через HibernateDaoSupport
    @Autowired
    private UserRepository userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void submit(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userDao.getOne(id);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDetails createUserDetails(User user) {
        return new JwtUserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getRoles()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public Long register(UserDto userDto) {
        boolean isUserExist = checkUserForExistsByEmail(userDto.getEmail());
        if (isUserExist)
            throw new IllegalArgumentException("There is an user with that email address:" + userDto.getEmail());

        User user = userMapper.mapToEntity(userDto);
        user.setRoles(SetUtils.hashSet(Role.ADMIN));
        user.setEnabled(true);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userDao.save(user);

        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUserForExistsByEmail(String email) {
        return userDao.countUserByEmail(email) != 0;
    }

}
