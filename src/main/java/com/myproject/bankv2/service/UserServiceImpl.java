package com.myproject.bankv2.service;

import com.myproject.bankv2.dto.UserDTO;
import com.myproject.bankv2.exceptions.InvalidUserDataException;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> getUsers(){
        List<UserDTO> result = new ArrayList<>();
        List<User> users = repository.findAll();
        users.forEach(user ->
                {
                    if(user.getFullName() == null ) {
                        user.setFullName("No name saved");
                    }
                }
        );
        users.forEach(user -> result.add(new UserDTO(user)));
        return result;
    }

    public User getUserById(int id){
        return repository.findById(id);
    }

    public User getUser(String username){
        return repository.findByUsername(username);
    }

    public UserDTO getUserByUsername(String username){
        return new UserDTO(repository.findByUsername(username));
    }

    public UserDTO getUserByEmail(String email){
        return new UserDTO(repository.findByEmail(email));
    }

    public void saveUser(User user){
        User foundUser= repository.findByUsername(user.getUsername());
        if (foundUser != null){
            throw new InvalidUserDataException();
        }
        user.setUserCreatedAt(LocalDateTime.now());
        repository.save(user);

    }

    public void delete(String username){
       User foundUser= repository.findByUsername(username);
       repository.deleteById(foundUser.getId());
    }

    public void updateUser(User user){
        User foundUser = repository.findByUsername(user.getUsername());
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setBirthDate(user.getBirthDate());
        foundUser.setFullName( user.getFirstName() + " " + user.getLastName());
        foundUser.setEmail(user.getEmail());

        if (foundUser.getUserCreatedAt() == null){
            foundUser.setUserCreatedAt(LocalDateTime.now());
        }
        repository.save(foundUser);
    }

}
