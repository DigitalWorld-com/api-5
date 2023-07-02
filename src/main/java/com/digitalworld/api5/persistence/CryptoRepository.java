package com.digitalworld.api5.persistence;

import com.digitalworld.api5.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<CryptoEntity, Integer> {
}
