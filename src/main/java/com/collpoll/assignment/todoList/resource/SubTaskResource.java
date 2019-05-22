package com.collpoll.assignment.todoList.resource;

import com.collpoll.assignment.todoList.model.SubTask;
import com.collpoll.assignment.todoList.model.Task;
import com.collpoll.assignment.todoList.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/subTask")
public class SubTaskResource {

    //Subtask APIs
    @Autowired
    SubTaskRepository subTaskRepository;

    @PostMapping(value = "/addSubTask/{id}")
    public void addSubTask(@RequestBody SubTask subTask, @PathVariable("id") Integer id) {
        Task task = new Task();
        task.setId(id);

        subTask.setTask(task);
        subTaskRepository.save(subTask);
    }

    @DeleteMapping(value = "/deleteSubTask/{id}")
    public void deleteSubTask(@PathVariable("id") Integer id) {
        /*SubTask subTask = new SubTask();
        subTask.setId(id);
        subTask.setTask(null);*/
        subTaskRepository.delete(id);
    }
}
