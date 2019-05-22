package com.collpoll.assignment.todoList.resource;

import com.collpoll.assignment.todoList.model.Task;
import com.collpoll.assignment.todoList.model.User;
import com.collpoll.assignment.todoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskResource {

    @Autowired
    TaskRepository taskRepository;

    /*private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "JPADemo";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }
    */
    @PostMapping(value = "/all")
    public List<Task> getAll(@RequestBody final User user) {
          return taskRepository.getAllTask(user.getId());
    }

    @PostMapping(value = "/all/{date}")
    public List<Task> getFilterTask(@RequestBody final User user, @PathVariable("date") String date) {
        return taskRepository.getFilterTask(user.getId(), date);
    }

    @PostMapping(value = "/addTask/{user_id}")
    public void addTask(@RequestBody Task task, @PathVariable("user_id") Integer id) {
        User user = new User();
        user.setId(id);

        task.setUser(user);

        System.out.println(task.getUser());
        taskRepository.save(task);
    }

    @PutMapping(value = "/editTask")
    public void editTask(@RequestBody @Valid Task task) {
        User user = new User();
        user.setId(taskRepository.getUserId(task.getId()));
        task.setUser(user);
        taskRepository.saveAndFlush(task);
    }

    @DeleteMapping(value = "/deleteTask/{id}")
    public void deleteTask(@PathVariable("id") Integer id) {
      /*  Task task = new Task();
        task.setId(id);
        task.setUser(null);*/
        taskRepository.delete(id);
    }

}
