package mentoring.study.tdd.repository;

import mentoring.study.tdd.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
