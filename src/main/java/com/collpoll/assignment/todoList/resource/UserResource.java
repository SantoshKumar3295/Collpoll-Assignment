package com.collpoll.assignment.todoList.resource;

import com.collpoll.assignment.todoList.model.User;
import com.collpoll.assignment.todoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/*@Author : Santosh Kumar
 ***User REST APIs*** Basic level of auth
* TODO : CONVERT THIS INTO BETTER AUTH
 */
@RestController
@RequestMapping(value = "")
public class UserResource  {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/login")
    public User loginUser(@RequestBody final User user) {
        return userRepository.getUser(user.getName(), user.getPassword());
    }

    @PostMapping(value = "/createUser")
    public User createuser(@RequestBody final User user) {
        User user_check = userRepository.getUser(user.getName(), user.getPassword());
        //check if user is already present
        if(user_check == null)
            return userRepository.save(user);
        else
            return user_check;
    }
}
