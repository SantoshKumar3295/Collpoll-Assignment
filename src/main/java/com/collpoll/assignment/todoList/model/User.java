package com.collpoll.assignment.todoList.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/*@Author : Santosh Kuamr
 **User table holds user information like name, password etc;
 * User has One to many reation with Task, as Every user can hold multiple task.
 */

@Entity
@Table(name = "users", catalog = "m_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //This realtion is saying "If Task has FK reference to user's id, then if user gets
    //deleted then delete Task whose FK pointing to id of User"
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
