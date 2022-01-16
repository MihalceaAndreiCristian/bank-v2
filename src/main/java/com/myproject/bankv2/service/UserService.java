package com.myproject.bankv2.service;

import com.myproject.bankv2.dto.UserDTO;
import com.myproject.bankv2.exceptions.InvalidUserDataException;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> getUsers(){
        List<UserDTO> result = new ArrayList<>();
        List<User> users = repository.getAllUsers();
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
        return repository.getUserByUsername(username);
    }

    public UserDTO getUserByUsername(String username){
        return new UserDTO(repository.getUserByUsername(username));
    }

    public UserDTO getUserByEmail(String email){
        return new UserDTO(repository.getUserByEmail(email));
    }

    public void saveUser(User user){
        User foundUser= repository.getUserByUsername(user.getUsername());
        if (foundUser != null){
            throw new InvalidUserDataException();
        }
        user.setUserCreatedAt(LocalDateTime.now());
        repository.saveUser(user);
    }

    public void delete(String username){
       User foundUser= repository.getUserByUsername(username);
       repository.deleteUser(foundUser.getId());
    }

    public void updateUser(User user){
        User foundUser = repository.getUserByUsername(user.getUsername());
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setBirthDate(user.getBirthDate());
        foundUser.setFullName( user.getFirstName() + " " + user.getLastName());
        foundUser.setEmail(user.getEmail());

        if (foundUser.getUserCreatedAt() == null){
            foundUser.setUserCreatedAt(LocalDateTime.now());
        }
        repository.saveUser(foundUser);

    }
}
