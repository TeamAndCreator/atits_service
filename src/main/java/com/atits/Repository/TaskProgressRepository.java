package com.atits.Repository;


import com.atits.entity.TaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {
}
