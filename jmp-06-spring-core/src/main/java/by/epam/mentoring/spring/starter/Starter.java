package by.epam.mentoring.spring.starter;

import by.epam.mentoring.spring.entity.Task;
import by.epam.mentoring.spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Starter implements CommandLineRunner {
    @Autowired
    private TaskRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Task("test1", "test2", "test3", "test4", 0));

        repository.findAll().forEach(System.out::println);
    }
}