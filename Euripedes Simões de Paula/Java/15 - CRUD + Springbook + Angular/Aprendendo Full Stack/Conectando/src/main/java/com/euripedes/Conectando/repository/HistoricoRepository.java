package com.euripedes.Conectando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.euripedes.Conectando.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByDiarioId(Long diarioId);
}
