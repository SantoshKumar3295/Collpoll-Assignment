package com.collpoll.assignment.todoList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/*@Authore : SantoshKuamr
**@Task table has a FOREIGN KEY (user_id) which refrence to (id) of User table;
* Task has (Many) to (1) relation with User.
* If any User gets deleted then it's child Task containing user_id (id of user) will also gets
    deleted because while creating Task table we will add on delete cascade on foreign key
    "..FOREIGN KEY(user_id) REFERENCES User(id) On Delete Cascade".
    for which JPA has "orphanRemoval=true, cascade= {CascadeType.ALL, CascadeType.REMOVE}"
    to perform same task. Also due to our same relation between task & subtask, subtask will also
    gets delted.
 */

@Entity
@Table(name = "task", catalog = "m_db")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Here FOREIGN KEY IS "user_id" which is referencing to Users(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    //This realtion is saying "If subtask has FK reference to task's id, then if task gets
    //deleted then delete subtask whose FK pointing to id of task"
    @OneToMany(orphanRemoval=true, cascade= {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "task")
    @JsonManagedReference
    private List<SubTask> subtasks;


    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTask> subtasks) {
        this.subtasks = subtasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
