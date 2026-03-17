package mentoring.study.tdd.service;

import mentoring.study.tdd.exception.TaskValidationException;
import mentoring.study.tdd.model.Task;
import mentoring.study.tdd.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Regra: seguir boas práticas do TDD

    public Task create(Task task) {
        return null;
    }

    public List<Task> findAll() {
        return null;
    }

    public void validate(Task task) {
        if (task == null) {
            throw new TaskValidationException("Task cannot be null");
        }
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new TaskValidationException("Task title cannot be null or empty");
        }
    }

}
