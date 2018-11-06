package com.atits.Repository;


import com.atits.entity.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestScoreRepository extends JpaRepository<TestScore, Long> {
}
