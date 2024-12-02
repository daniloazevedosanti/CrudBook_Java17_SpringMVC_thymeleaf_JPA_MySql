package com.crudbook.crudbook.controller.repository;

import com.crudbook.crudbook.model.entity.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long> {
}
