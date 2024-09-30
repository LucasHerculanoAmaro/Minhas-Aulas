package com.euripedes.Conectando.service;

import java.util.Optional;

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
        // Verificar se existe um balancete para a conta de crédito, senão cria um novo
        Balancete balanceteCredito = balanceteRepository.findByContaId(diario.getCredito().getId());
        if (balanceteCredito == null) {
            balanceteCredito = new Balancete();
            balanceteCredito.setConta(diario.getCredito());
            balanceteCredito.setSaldo(0.0); // Saldo inicial, ou outro valor padrão
        }
        
        // Verificar se existe um balancete para a conta de débito, senão cria um novo
        Balancete balanceteDebito = balanceteRepository.findByContaId(diario.getDebito().getId());
        if (balanceteDebito == null) {
            balanceteDebito = new Balancete();
            balanceteDebito.setConta(diario.getDebito());
            balanceteDebito.setSaldo(0.0); // Saldo inicial, ou outro valor padrão
        }

        // Atualizar os saldos
        balanceteCredito.setSaldo(balanceteCredito.getSaldo() + diario.getValor());
        balanceteDebito.setSaldo(balanceteDebito.getSaldo() - diario.getValor());

        // Salvar as atualizações
        balanceteRepository.save(balanceteCredito);
        balanceteRepository.save(balanceteDebito);
    }

    public void atualizarBalanceteAoDeletarDiario(Diario diario) {
        // Lógica para ajustar os valores no balancete com base nas contas débito e crédito
        Long contaDebitoId = diario.getDebito().getId();
        Long contaCreditoId = diario.getCredito().getId();
        Double valor = diario.getValor(); 
        
	    // Ajustar valor no balancete para conta débito
        Balancete balanceteDbt = balanceteRepository.findByContaId(contaDebitoId);
        Optional<Balancete> balanceteDebito = Optional.ofNullable(balanceteDbt);
        balanceteDebito.ifPresent(b -> {
        	b.setSaldo(b.getSaldo() + valor); // Subtrai o valor do saldo
        	balanceteRepository.save(b);
        });
        
        // Ajustar valor no balancete para conta crédito
        Balancete balanceteCdt = balanceteRepository.findByContaId(contaCreditoId);
        Optional<Balancete> balanceteCredito = Optional.ofNullable(balanceteCdt);
        balanceteCredito.ifPresent(b -> {
            b.setSaldo(b.getSaldo() - valor); // Adiciona o valor ao saldo
            balanceteRepository.save(b);
        });
    }

}

