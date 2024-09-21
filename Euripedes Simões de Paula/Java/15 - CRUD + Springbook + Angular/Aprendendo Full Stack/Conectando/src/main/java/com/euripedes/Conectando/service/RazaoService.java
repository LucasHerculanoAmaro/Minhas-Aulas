package com.euripedes.Conectando.service;

import java.math.BigDecimal;
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
	
	public Razao atualizarRazao(Diario diario) {
		
		Optional<Razao> razaoOptional = razaoRepository.findByContaId(diario.getDebito().getId());
		
		if (razaoOptional.isPresent()) {
			
			Razao razao = razaoOptional.get();
			BigDecimal valor = BigDecimal.valueOf(diario.getValor());
			
			razao.setSaldoAtual(razao.getSaldoAtual().add(valor));
			razao.setData(diario.getData());
			razao.setHistorico(diario.getHistorico());
			
			return razaoRepository.save(razao);
			
		} else {
		
			throw new NullPointerException("Razão sem registros!");
		}
	}
	
}
