package com.goutam.ai.Smart_To_Do.repository;

import com.goutam.ai.Smart_To_Do.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
