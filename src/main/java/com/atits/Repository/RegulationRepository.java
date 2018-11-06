package com.atits.Repository;


import com.atits.entity.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegulationRepository extends JpaRepository<Regulation, Long> {
}
