package com.praticeShop.service;

import com.praticeShop.entity.User;
import com.praticeShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);

    }

    public Optional<User> getUser(long id) {
        Optional<User> byId = userRepository.findById(id);

        return userRepository.findById(id).filter(user -> user.getDeleted() == false);

    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        for (User l : userRepository.findAll()) {
            if (l.getDeleted() == false) {
                userList.add(l);
            }
        }
        return userList;
    }

    public String softDelete(Long id) {
        User byId = userRepository.findById(id).get();
        byId.setDeleted(Boolean.TRUE);
        userRepository.save(byId);

        return " record deleted";
    }

    public String updateUser(User user) {
        userRepository.save(user);
        return "update successfully";
    }

    public List<User> findByNameOrLastName(Optional<String> firstName, Optional<String> lastName) {
        return userRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<User> findAllByDateOfBirthAndJoiningDateAsc(Optional<Date> dateOfBirth, Optional<Date> dateOfJoining) {
        return userRepository.findByDateOfBirthAndDateOfJoining(dateOfBirth, dateOfJoining);
    }
}
