package com.sparta.springtodolist.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserRoleEnum roleEnum = UserRoleEnum.USER;

    @Test
    void findByUsername() {
        String username = "username";
        String password = "123456789";
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, roleEnum);

        User User2 = userRepository.saveAndFlush(user);
        // when
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // then
        assertThat(optionalUser.isPresent()).isEqualTo(true);
        assertThat(optionalUser.get()).isEqualTo(User2);
    }

    @Test
    void findByUsername_fail() {
        String username = "username";
        String password = "123456789";
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, roleEnum);

        User User2 = userRepository.saveAndFlush(user);
        // when
        Optional<User> optionalUser = userRepository.findByUsername("테스트");

        // then
        assertThat(optionalUser.isEmpty()).isEqualTo(true);
    }
}