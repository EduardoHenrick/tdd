package mentoring.study.tdd.service;

import mentoring.study.tdd.exception.TaskValidationException;
import mentoring.study.tdd.model.Task;
import mentoring.study.tdd.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    void deveCriarTaskComSucesso() {
        Task task = new Task();
        task.setTitle("Limpar banheiro");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Limpar banheiro");

        when(repository.save(task)).thenReturn(savedTask);
        Task result = service.create(task);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Limpar banheiro", result.getTitle());

        verify(repository, times(1)).save(task);
    }

    @Test
    void deveLancarExcecaoQuandoTarefaForNula() {
        Task task = null;

        assertThrows(TaskValidationException.class, () -> {
            service.create(task);
        });

        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoTituloForVazio() {
        Task task = new Task();
        task.setTitle("");

        assertThrows(TaskValidationException.class, () -> {
            service.create(task);
        });

        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoTituloForNulo() {
        Task task = new Task();
        task.setTitle(null);

        assertThrows(TaskValidationException.class, () -> {
            service.create(task);
        });

        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoTituloConterApenasEspacos() {
        Task task = new Task();
        task.setTitle("   ");

        assertThrows(TaskValidationException.class, () -> {
            service.create(task);
        });

        verify(repository, never()).save(any());
    }

    @Test
    void deveRetornarTodasAsTarefasQuandoExistiremTarefas() {
        Task task1 = new Task();
        task1.setTitle("Tarefa 1");

        Task task2 = new Task();
        task2.setTitle("Tarefa 2");

        List<Task> tasks = List.of(task1, task2);

        when(repository.findAll()).thenReturn(tasks);

        List<Task> result = service.findAll();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Tarefa 1", result.get(0).getTitle());
        assertEquals("Tarefa 2", result.get(1).getTitle());

        verify(repository, times(1)).findAll();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremTarefas() {
        when(repository.findAll()).thenReturn(List.of());

        List<Task> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository).findAll();
    }


}