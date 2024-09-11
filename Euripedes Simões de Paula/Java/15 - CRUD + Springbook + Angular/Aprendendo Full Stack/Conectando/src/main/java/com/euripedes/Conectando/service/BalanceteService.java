package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Balancete;
import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.RazaoAnalitico;
import com.euripedes.Conectando.repository.BalanceteRepository;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.RazaoAnaliticoRepository;

import jakarta.transaction.Transactional;

@Service
public class BalanceteService {

	@Autowired
	private BalanceteRepository balanceteRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
    private RazaoAnaliticoRepository razaoAnaliticoRepository;
	
	public void gerarBalancete() {
		
//		List<Conta> contas = contaRepository.findAll();
//		BigDecimal totalDevedor = BigDecimal.ZERO;
//		BigDecimal totalCredor = BigDecimal.ZERO;
//		
//		for (Conta conta : contas) {
//			Balancete balancete = new Balancete();
//			balancete.setCodigoConta(conta.getCodigo());
//			balancete.setNomeConta(conta.getNome());
//			
//			if (conta.getSaldo().compareTo(BigDecimal.ZERO) >= 0) {
//				balancete.setSaldoDevedor(conta.getSaldo());
//				totalDevedor = totalDevedor.add(conta.getSaldo());
//			} else {
//				balancete.setSaldoCredor(conta.getSaldo().negate());
//				totalCredor = totalCredor.add(conta.getSaldo().negate());
//			}
//			
//			balanceteRepository.save(balancete);
//		}
		
		List<RazaoAnalitico> razoes = razaoAnaliticoRepository.findAll();
		
		BigDecimal saldoTotal = razoes.stream()
									  .map(RazaoAnalitico::getSaldo)
									  .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Balancete balancete = new Balancete();
		balancete.setData(LocalDate.now());
		balancete.setSaldoTotal(saldoTotal);
		balancete.setRazaoAnaliticos(razoes);
		
		return balanceteRepository.save(balancete);
		
	}
	
	public BigDecimal calcularTotalGeral() { 
		BigDecimal totalDevedor = balanceteRepository.sumSaldoDevedor();
		BigDecimal totalCredor = balanceteRepository.sumSaldoCredor();
		return totalDevedor.subtract(totalCredor);
	}

	public List<Conta> listarContas() {
		return contaRepository.findAll();
	}
	
}
