package com.collpoll.assignment.todoList.repository;

import com.collpoll.assignment.todoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
