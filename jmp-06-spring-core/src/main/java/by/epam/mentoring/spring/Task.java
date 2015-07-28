package by.epam.mentoring.spring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Task {

    @Id
    @Indexed(unique = true)
    private String id;

    private String taskName;

    private String taskDescription;

    private String taskPriority;

    private String taskStatus;

    private int taskArchived = 0;

    public String getTaskId() {
        return id;
    }

    public void setTaskId(String taskId) {
        this.id = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int isTaskArchived() {
        return taskArchived;
    }

    public void setTaskArchived(int taskArchived) {
        this.taskArchived = taskArchived;
    }
}

