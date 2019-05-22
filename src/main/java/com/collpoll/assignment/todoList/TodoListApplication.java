package com.collpoll.assignment.todoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*
*Run this class inorder to start spring service
 */

@EnableJpaRepositories(basePackages = "com.collpoll.assignment.todoList.repository")
@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}
