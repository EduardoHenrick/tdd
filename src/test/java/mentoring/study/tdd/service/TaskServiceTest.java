package mentoring.study.tdd.service;

import mentoring.study.tdd.exception.TaskValidationException;
import mentoring.study.tdd.model.Task;
import mentoring.study.tdd.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}