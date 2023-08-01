package com.digitalworld.api5.persistence;

import com.digitalworld.api5.entity.ConversionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversionRepository extends JpaRepository<ConversionEntity, Integer> {

    List<ConversionEntity> findByCoinNameContainingIgnoreCase(String findText);

}
