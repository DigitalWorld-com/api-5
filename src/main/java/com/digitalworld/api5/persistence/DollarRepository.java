package com.digitalworld.api5.persistence;

import com.digitalworld.api5.entity.DollarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DollarRepository extends JpaRepository<DollarEntity, Integer> {
}
