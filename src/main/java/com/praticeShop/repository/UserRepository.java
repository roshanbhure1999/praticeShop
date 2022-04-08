package com.praticeShop.repository;

import com.praticeShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameOrLastName(Optional<String> firstName, Optional<String> lastName);
    List<User> findByDateOfBirthAndDateOfJoining(Optional<Date>dateOfBirth, Optional<Date>dateOfJoining);
}
