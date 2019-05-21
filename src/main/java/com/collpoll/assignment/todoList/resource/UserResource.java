package com.collpoll.assignment.todoList.resource;

import com.collpoll.assignment.todoList.model.User;
import com.collpoll.assignment.todoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "")
public class UserResource  {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/login")
    public User persist(@RequestBody final User user) {
        for(User obj : userRepository.findAll()) {
            if(obj.getName().equalsIgnoreCase(user.getName())) return obj;
        }
        return null;
    }
}
