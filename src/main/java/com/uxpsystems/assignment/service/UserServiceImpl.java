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
    static final List<String> STATUS = Arrays.asList("ACTIVATED","DEACTIVATED");
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public User getUser(long id) {
        Optional<User> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotFoundException(String.format("User with id %d not found",id));
        }
       return byId.get();
    }

    @Override
    public User deleteUser(long id) {
        User user = getUser(id);
        userRepository.delete(user);
      return user;
    }

    @Override
    public User createUser(User user) {
        validateRequest(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    private void validateRequest(User user){

        if(user.getUsername()==null || user.getUsername().isEmpty()){
            throw  new RequestNotValidException("User username should not be empty");
        }

        if(user.getPassword()==null || user.getPassword().isEmpty()){
            throw  new RequestNotValidException("User password should not be empty");
        }


        if(user.getStatus()==null || user.getStatus().isEmpty() || !STATUS.contains(user.getStatus().toUpperCase())){
            throw  new RequestNotValidException("User status only permit Activated/Deactivated values");
        }
    }
}
