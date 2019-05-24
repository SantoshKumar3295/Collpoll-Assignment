package com.collpoll.assignment.todoList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*@Author : Santosh Kumar
***@Subtak table has a FOREIGN KEY (task_id) which refrence to (id) of Task table;
*  Subtask has (Many) to (1) relation with Task.
*  If any Task gets deleted then it's child subtask containing task_id (id of task) will also gets
    deleted because while creating SubTask table we will add on delete cascade on foreign key
    "..FOREIGN KEY(task_id) REFERENCES Task(id) On Delete Cascade".
    for which JPA has "orphanRemoval=true, cascade= {CascadeType.ALL, CascadeType.REMOVE}"
    to perform same task.
 */

@Entity
@Table(name = "subtask", catalog = "m_db")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Here FOREIGN KEY IS "task_id" which is referencing to Task(id)
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
