package by.epam.mentoring.spring.entity;

import org.springframework.data.annotation.Id;

public class Task {

    @Id
    private int id;

    private String taskName;

    private String taskDescription;

    private String taskPriority;

    private String taskStatus;

    private int taskArchived = 0;

    public Task(String taskName, String taskDescription, String taskPriority, String taskStatus, int taskArchived) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.taskArchived = taskArchived;
    }


    @Override
    public String toString() {
        return String.format("Task [id=%s, taskName=%s, taskDescription=%s, taskPriority=%s, taskStatus=%s]", id, taskName, taskDescription, taskPriority, taskStatus);
    }

}

