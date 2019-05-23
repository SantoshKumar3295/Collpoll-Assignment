package com.collpoll.assignment.todoList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/*@Author : Santosh Kumar
*
***@Subtak has a foreign key (task_id) which is the pk id of task
*  Subtask (Many) to (1) Task relation
*  Task entity will delete this entity (row in table) when the Task is deleted.
 */

@Entity
@Table(name = "subtask", catalog = "santo_db")
public class SubTask {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @JsonBackReference
    private Task task;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
