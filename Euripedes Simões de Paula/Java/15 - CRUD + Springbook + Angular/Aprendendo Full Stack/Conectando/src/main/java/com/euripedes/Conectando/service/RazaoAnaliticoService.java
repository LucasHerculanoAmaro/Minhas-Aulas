package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;
import com.euripedes.Conectando.repository.RazaoAnaliticoRepository;

@Service
public class RazaoAnaliticoService {

	@Autowired
	private RazaoAnaliticoRepository razaoRepository;
	
	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	public List<RazaoAnaliticoRepository> obterMovimentacoesporConta(String conta) {
		return razaoRepository.findByConta(conta);
	}
	
	public List<LancamentoContabil> listarTransacoesPorConta(Long id) {
		return lancamentoContabilRepository.findByContaId(id);
	}
	
	public BigDecimal calcularSaldoPorConta(Long contaId) {
        BigDecimal totalDebito = lancamentoContabilRepository.sumDebitoByContaId(contaId);
        BigDecimal totalCredito = lancamentoContabilRepository.sumCreditoByContaId(contaId);
        return totalDebito.subtract(totalCredito);
    }
	
}
