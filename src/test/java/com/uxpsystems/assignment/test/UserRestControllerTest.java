package com.uxpsystems.assignment.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.uxpsystems.assignment.config.NotFoundException;
import com.uxpsystems.assignment.controller.UserController;
import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @WithMockUser(username="admin")
    @Test
    public void getAllUsers()
            throws Exception {
        User user = new User();
        user.setUsername("Rony");
        user.setPassword("ABCD123");
        user.setStatus("Activated");
        List<User> allusers = Arrays.asList(user);
        given(userService.getUsers()).willReturn(allusers);
        mvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is(user.getUsername())));
    }


    @WithMockUser(username="admin")
    @Test
    public void getUserNotFound()
            throws Exception {
        User user = new User();
        user.setUsername("Rony");
        user.setPassword("ABCD123");
        user.setStatus("Activated");
        List<User> allusers = Arrays.asList(user);
        given(userService.getUser(7)).willThrow(new NotFoundException());
        mvc.perform(get("/user/7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    /*@WithMockUser(username="admin")
    @Test
    public void getCreateUser()
            throws Exception {
        User user = new User();
        user.setUsername("Rony");
        user.setPassword("ABCD123");
        user.setStatus("Activated");

        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("Rony");
        user1.setPassword("ABCD123");
        user1.setStatus("Activated");
        given(userService.createUser(user)).willReturn(user1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(user);

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN",csrfToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user.getUsername())));

    }*/

}
