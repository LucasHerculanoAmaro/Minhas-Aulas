package com.euripedes.Conectando.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;

public interface BalanceteRepository extends JpaRepository<Balancete, Long>{

    Optional<Balancete> findByCodigo(String codigo);

    // Se você quiser buscar por uma Conta associada, certifique-se de que o Balancete tem uma referência à Conta
    Optional<Balancete> findByConta(Conta conta);

    List<Balancete> findByLancamentoId(Long lancamentoId);

    // Caso você tenha uma relação entre Balancete e Conta, este método pode ser útil
    Optional<Balancete> findByConta_Codigo(String codigoConta);
}
