package com.praticeShop.service;

import com.praticeShop.entity.User;
import com.praticeShop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void deleteUser() {

    }

    @Test
    void getUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("roshan");
        user.setLastName("bhure");
        user.setPinCode(441904);
        user.setDateOfBirth(new Date());
        user.setDateOfJoining(new Date());
        user.setDeleted(false);

        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        assertEquals(userService.getUser(1l), Optional.of(user));

    }

    @Test
    void getAllUser() {
        List<User> userList = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userService.getAllUser(), userList);
    }

    @Test
    void getAllUserActiveUser() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setFirstName("Roshan");
        user.setLastName("bhure");
        user.setPinCode(441904);
        user.setId(1);
        user.setDateOfBirth(new Date());
        user.setDateOfJoining(new Date());
        user.setDeleted(false);
        expected.add(user);

        Mockito.when(userRepository.findAll()).thenReturn(expected);
        assertEquals(userService.getAllUser(), expected);

    }

    @Test
    void getAllUserInActiveUser() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setFirstName("Roshan");
        user.setLastName("bhure");
        user.setPinCode(441904);
        user.setId(1);
        user.setDateOfBirth(new Date());
        user.setDateOfJoining(new Date());
        user.setDeleted(true);

        userList.add(user);
        User user1 = new User();
        user1.setFirstName("Akhil");
        user1.setLastName("zade");
        user1.setPinCode(44104);
        user1.setId(2);
        user1.setDateOfBirth(new Date());
        user1.setDateOfJoining(new Date());
        user1.setDeleted(false);

        userList.add(user1);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> actual = userService.getAllUser();

        assertEquals(1, actual.size());

    }

    @Test
    void softDelete() {
        User user1 = new User();
        user1.setFirstName("Akhil");
        user1.setLastName("zade");
        user1.setPinCode(441044);
        user1.setId(2);
        user1.setDateOfBirth(new Date());
        user1.setDateOfJoining(new Date());
        user1.setDeleted(false);
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user1));
        String expected = userService.softDelete(1l);
        assertEquals(true,user1.getDeleted());
        assertEquals(" record deleted", expected);
    }

    @Test
    void updateUser() {
    }

    @Test
    void findByNameOrLastName() {

        List<User>userList=new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Akhil");
        user1.setLastName("zade");
        user1.setPinCode(441044);
        user1.setId(2);
        user1.setDateOfBirth(new Date());
        user1.setDateOfJoining(new Date());
        user1.setDeleted(false);
        userList.add(user1);
        Mockito.when(userRepository.findByFirstNameOrLastName(Optional.of("Akhil"), Optional.of("zade"))).thenReturn(userList);
        assertEquals(userService.findByNameOrLastName(Optional.of("Akhil")),user1);


    }

    @Test
    void findAllByDateOfBirthAndJoiningDateAsc() {
    }
}