package mentoring.study.tdd.controller;

import mentoring.study.tdd.exception.TaskValidationException;
import mentoring.study.tdd.model.Task;
import mentoring.study.tdd.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    private TaskService service = mock(TaskService.class);
    private TaskController controller = new TaskController(service);

    @Test
    void deveCriarTaskComSucesso(){
        Task task = new Task();
        task.setTitle("Estudar TDD");

        Task savedTask = new Task();
        savedTask.setTitle("Estudar TDD");

        when(service.create(task)).thenReturn(savedTask);
        ResponseEntity<Task> response = controller.createTask(task);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudar TDD", response.getBody().getTitle());

        verify(service, times(1)).create(task);
    }

    @Test
    void deveLancarExcecaoQuandoTaskForNula() {
        Task task = null;

        when(service.create(null))
                .thenThrow(new TaskValidationException("Tarefa não pode ser nula"));

        assertThrows(TaskValidationException.class, () -> {
            controller.createTask(task);
        });

        verify(service).create(null);
    }

    @Test
    void deveListarTodasAsTasks(){
        Task task1 = new Task();
        task1.setTitle("Estudar TDD");

        Task task2 = new Task();
        task2.setTitle("Fazer exercícios");

        when(service.findAll()).thenReturn(List.of(task1, task2));
        ResponseEntity<List<Task>> response = controller.getAllTasks();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Estudar TDD", response.getBody().get(0).getTitle());
        assertEquals("Fazer exercícios", response.getBody().get(1).getTitle());

        verify(service, times(1)).findAll();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremTasks() {
        when(service.findAll()).thenReturn(List.of());

        ResponseEntity<List<Task>> response = controller.getAllTasks();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(service, times(1)).findAll();
    }
}