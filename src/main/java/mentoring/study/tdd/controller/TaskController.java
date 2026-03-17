package mentoring.study.tdd.controller;

import mentoring.study.tdd.model.Task;
import mentoring.study.tdd.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return null;
    }

}
