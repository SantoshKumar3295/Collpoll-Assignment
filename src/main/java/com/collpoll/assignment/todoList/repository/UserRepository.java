package com.collpoll.assignment.todoList.repository;

import com.collpoll.assignment.todoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
 ***@Author : Santosh Kumar
 * Custom queries based on our requirement
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users u WHERE name = :name and password = :password", nativeQuery = true)
    public User getUser(@Param("name") String name, @Param("password") String password);
}
