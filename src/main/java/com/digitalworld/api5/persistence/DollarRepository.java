package com.digitalworld.api5.persistence;

import com.digitalworld.api5.model.DollarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DollarRepository extends JpaRepository<DollarModel, Integer> {
}
