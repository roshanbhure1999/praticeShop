package com.praticeShop.controller;

import com.praticeShop.entity.User;
import com.praticeShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
    }
    @GetMapping(path = "/user/{id}")
    public Optional<User> getUser(@PathVariable("id") long id){
        return userService. getUser(id);
    }
    @GetMapping(path = "/getAll")
    public List<User> getAll(){
        return userService.getAllUser();
    }

    @PutMapping(path="/softDelete/{id}")
    public String softDelete(@PathVariable("id") long id){
        return userService.softDelete(id);
    }


    @PutMapping(path="/update")
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/search")
    public List<User> findByFirstNameOrLastName(@RequestParam(name = "firstName") Optional<String> firstName, @RequestParam(name = "lastName") Optional<String> lastName) {
        return userService.findByNameOrLastName(firstName, lastName);
    }

    @GetMapping("/searchByDate")
    public List<User> findAllByDateOfBirthAndJoiningDateAsc(@RequestParam(name = "dateOfBirth")Optional<Date> dateOfBirth, @RequestParam(name = "dateOfJoining") Optional<Date> dateOfJoining) {
        return userService.findAllByDateOfBirthAndJoiningDateAsc(dateOfBirth,dateOfJoining);
    }

}
