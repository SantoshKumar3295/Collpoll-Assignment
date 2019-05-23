package com.collpoll.assignment.todoList.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/*@Author : Santosh Kumar
**User is root entity which holds user information like name, password etc;
 */

@Entity
@Table(name = "users", catalog = "santo_db")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToMany(orphanRemoval=true, cascade= {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<Task> taskList;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
