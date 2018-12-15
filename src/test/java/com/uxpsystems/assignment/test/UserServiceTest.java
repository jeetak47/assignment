package com.uxpsystems.assignment.test;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserRepository;
import com.uxpsystems.assignment.service.UserService;
import com.uxpsystems.assignment.service.UserServiceImpl;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
public class UserServiceTest {
    @TestConfiguration
    static class UserServiceTestConfig {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
    @Autowired
    UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Before
    public void setUp() {
        User user = new User();
        user.setUsername("Josh");
        user.setId(5L);
        user.setStatus("Activated");
        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
    }
    @Test
    public void whenIdGiven_thenUserShouldBeFound() {
        Long id =5L;
        User user = userService.getUser(5L);

        assertThat(user.getId(),is(id));
    }

    public static Matcher<Long> is(Long value) {
        return org.hamcrest.core.Is.is(value.longValue());
    }
}
