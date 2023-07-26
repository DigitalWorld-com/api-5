package com.digitalworld.api5.persistence;

import com.digitalworld.api5.entity.ConversionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<ConversionEntity, Integer> {
}
