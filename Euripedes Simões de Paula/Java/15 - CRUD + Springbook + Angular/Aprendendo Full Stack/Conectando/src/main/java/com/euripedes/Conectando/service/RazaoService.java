package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.model.Razao;
import com.euripedes.Conectando.repository.RazaoRepository;

@Service
public class RazaoService {

	@Autowired
	private RazaoRepository razaoRepository;
	
	public void atualizarRazao(Diario diario) {
		
//		Optional<Razao> razaoOptional = razaoRepository.findByContaId(diario.getDebito().getId());
//		
//		if (razaoOptional.isPresent()) {
//			
//			Razao razao = razaoOptional.get();
//			BigDecimal valor = BigDecimal.valueOf(diario.getValor());
//			
//			razao.setSaldoAtual(razao.getSaldoAtual().add(valor));
//			razao.setData(diario.getData());
//			razao.setHistorico(diario.getHistorico());
//			
//			return razaoRepository.save(razao);
//			
//		} else {
//		
//			throw new NullPointerException("Razão sem registros!");
//		}
		
		BigDecimal valor = BigDecimal.valueOf(diario.getValor());

		// Atualizar conta de débito
		Optional<Razao> razaoDebitoOptional = razaoRepository.findByContaId(diario.getDebito().getId());
		Razao razaoDebito = razaoDebitoOptional.orElseGet(() -> {
		    Razao novaRazao = new Razao();
		    novaRazao.setDebito(BigDecimal.ZERO); // Inicializa o débito como zero
		    return novaRazao;
		});

		// Atualizar conta de crédito
		Optional<Razao> razaoCreditoOptional = razaoRepository.findByContaId(diario.getCredito().getId());
		Razao razaoCredito = razaoCreditoOptional.orElseGet(() -> {
		    Razao novaRazao = new Razao();
		    novaRazao.setCredito(BigDecimal.ZERO); // Inicializa o crédito como zero
		    return novaRazao;
		});

		// Operação de Débito
		BigDecimal debitoAtual = razaoDebito.getDebito() != null ? razaoDebito.getDebito() : BigDecimal.ZERO;
		razaoDebito.setDebito(debitoAtual.add(valor));  // Adiciona o valor ao saldo de débito
		razaoDebito.setCredito(BigDecimal.ZERO);  // Zera o crédito, já que é uma operação de débito
		razaoDebito.setData(diario.getData());
		razaoDebito.setHistorico(diario.getHistorico());

		// Operação de Crédito
		BigDecimal creditoAtual = razaoCredito.getCredito() != null ? razaoCredito.getCredito() : BigDecimal.ZERO;
		razaoCredito.setCredito(creditoAtual.add(valor));  // Adiciona o valor ao saldo de crédito
		razaoCredito.setDebito(BigDecimal.ZERO);  // Zera o débito, já que é uma operação de crédito
		razaoCredito.setData(diario.getData());
		razaoCredito.setHistorico(diario.getHistorico());

		// Salvar alterações no Razão
		razaoRepository.save(razaoDebito);
		razaoRepository.save(razaoCredito);



	    ///return null;
	
	}
	
}
