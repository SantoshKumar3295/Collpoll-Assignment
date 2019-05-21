package com.collpoll.assignment.todoList.repository;


import com.collpoll.assignment.todoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM Task u WHERE user_id = :userid", nativeQuery = true)
    List<Task> getAllTask(@Param("userid") Integer id);
}

