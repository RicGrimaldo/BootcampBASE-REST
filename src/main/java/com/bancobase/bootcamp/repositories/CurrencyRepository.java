package com.bancobase.bootcamp.repositories;

import com.bancobase.bootcamp.schemas.CurrencySchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencySchema, String> {
}
