package com.euripedes.Conectando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.BalanceteRepository;

@Service
public class BalanceteService {

    @Autowired
    private BalanceteRepository balanceteRepository;

    public void atualizarBalancete(Diario diario) {
        // Encontrar os registros de balancete associados às contas de débito e crédito
        Balancete balanceteCredito = balanceteRepository.findByContaId(diario.getCredito());
        Balancete balanceteDebito = balanceteRepository.findByContaId(diario.getDebito());

        // Atualizar os saldos
        balanceteCredito.setSaldo(balanceteCredito.getSaldo() + diario.getValor());
        balanceteDebito.setSaldo(balanceteDebito.getSaldo() - diario.getValor());

        // Salvar as atualizações
        balanceteRepository.save(balanceteCredito);
        balanceteRepository.save(balanceteDebito);
    }
}

