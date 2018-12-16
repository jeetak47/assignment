package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.config.NotFoundException;
import com.uxpsystems.assignment.config.RequestNotValidException;
import com.uxpsystems.assignment.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    static final List<String> STATUS_VALUES = Arrays.asList("ACTIVATED","DEACTIVATED");
    @Override
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public User getUser(long id) {
        Optional<User> byId = userRepository.findById(id);
        // throw not found exception if user not present
        if(!byId.isPresent()){
            throw new NotFoundException(String.format("User with id %d not found",id));
        }
       return byId.get();
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public User deleteUser(long id) {
        User user = getUser(id);
        userRepository.delete(user);
      return user;
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public User createUser(User user) {
        validateRequest(user);
        return userRepository.save(user);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public User updateUser(User user) {
        validateRequest(user);
        // Check if user is present
        getUser(user.getId());
        return userRepository.save(user);
    }

    private void validateRequest(User user){

        if(user.getUsername()==null || user.getUsername().isEmpty()){
            throw  new RequestNotValidException("User username should not be empty");
        }

        if(user.getPassword()==null || user.getPassword().isEmpty()){
            throw  new RequestNotValidException("User password should not be empty");
        }


        if(user.getStatus()==null || user.getStatus().isEmpty() || !STATUS_VALUES.contains(user.getStatus().toUpperCase())){
            throw  new RequestNotValidException("User status only permit Activated/Deactivated values");
        }
    }
}
