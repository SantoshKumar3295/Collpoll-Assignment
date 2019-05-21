package com.collpoll.assignment.todoList.repository;

import com.collpoll.assignment.todoList.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Integer> {
}
