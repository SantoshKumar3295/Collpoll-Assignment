package com.collpoll.assignment.todoList.resource;

import com.collpoll.assignment.todoList.model.SubTask;
import com.collpoll.assignment.todoList.model.Task;
import com.collpoll.assignment.todoList.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
***Subtask REST APIs
 */

@RestController
@RequestMapping(value = "/subTask")
public class SubTaskResource {

    @Autowired
    SubTaskRepository subTaskRepository;

    @PostMapping(value = "/addSubTask/{task_id}")
    public SubTask addSubTask(@RequestBody SubTask subTask, @PathVariable("task_id") Integer task_id) {

        Task task = new Task();
        //To create subtask with task_id
        task.setId(task_id);

        subTask.setTask(task);
        return subTaskRepository.save(subTask);
    }

    @DeleteMapping(value = "/deleteSubTask/{id}")
    public void deleteSubTask(@PathVariable("id") Integer id) {
        subTaskRepository.delete(id);
    }
}
