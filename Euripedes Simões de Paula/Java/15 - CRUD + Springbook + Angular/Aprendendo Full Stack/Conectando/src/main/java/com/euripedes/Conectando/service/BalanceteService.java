package com.euripedes.Conectando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.BalanceteRepository;

@Service
public class BalanceteService {

//	@Autowired
//	private LancamentoContabilRepository lancamentoContabilRepository;
	
	@Autowired
	private BalanceteRepository balanceteRepository;
	
	public void atualizarBalancete(LancamentoContabil lancamento) {
		
		Balancete balanceteDebito = balanceteRepository
				.findByCodigoConta(lancamento.getCodigoDebito().getCodigo())
				.orElse(new Balancete());
		balanceteDebito.setCodigoConta(lancamento.getCodigoDebito().getCodigo());
		balanceteDebito.setSaldoDevedor(balanceteDebito.getSaldoDevedor().add(lancamento.getValor()));
		balanceteRepository.save(balanceteDebito);
		
		Balancete balanceteCredito = balanceteRepository
				.findByCodigoConta(lancamento.getCodigoCredito().getCodigo())
				.orElse(new Balancete());
		balanceteCredito.setCodigoConta(lancamento.getCodigoCredito().getCodigo());
		balanceteCredito.setSaldoCredor(balanceteCredito.getSaldoCredor().add(lancamento.getValor()));
		balanceteRepository.save(balanceteCredito);
		
	}
	
	public List<Balancete> listarBalancete() {
		return balanceteRepository.findAll();
	}
	
	
}
