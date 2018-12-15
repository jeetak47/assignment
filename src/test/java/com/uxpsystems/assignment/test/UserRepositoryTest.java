package com.uxpsystems.assignment.test;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSave_thenReturnUser() {
        // given
        User user = new User();
        user.setUsername("jhon");
        user.setUsername("Password");
        user.setUsername("Activated");
        entityManager.persist(user);
        entityManager.flush();

        // when
        List<User> users = userRepository.findAll();

        // then
        assertThat(users.get(0).getUsername())
                .isEqualTo(user.getUsername());
    }

}