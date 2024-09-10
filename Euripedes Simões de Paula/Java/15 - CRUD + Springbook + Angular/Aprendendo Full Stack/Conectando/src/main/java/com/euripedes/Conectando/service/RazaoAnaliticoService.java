package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.model.RazaoAnalitico;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;
import com.euripedes.Conectando.repository.RazaoAnaliticoRepository;

@Service
public class RazaoAnaliticoService {

	@Autowired
	private RazaoAnaliticoRepository razaoRepository;
	
	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	
	public List<RazaoAnalitico> obterMovimentacoesPorConta(String nomeConta) {
        return razaoRepository.findByContaNome(nomeConta);
    }
	
	public List<LancamentoContabil> listarTransacoesPorConta(Long id) {
		return lancamentoContabilRepository.findByContaId(id);
	}
	
	public BigDecimal calcularSaldoPorConta(Long id) {
        BigDecimal totalDebito = lancamentoContabilRepository.sumDebitoById(id);
        BigDecimal totalCredito = lancamentoContabilRepository.sumCreditoById(id);
        return totalDebito.subtract(totalCredito);
    }
	
}
