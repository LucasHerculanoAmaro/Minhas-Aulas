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
	private RazaoAnaliticoRepository razaoAnaliticoRepository;
	
	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	
	public List<RazaoAnalitico> obterMovimentacoesPorConta(String nomeConta) {
        return razaoAnaliticoRepository.findByContaNome(nomeConta);
    }
	
	public List<LancamentoContabil> listarTransacoesPorConta(Long id) {
		return lancamentoContabilRepository.findByContaId(id);
	}
	
	public BigDecimal calcularSaldoPorConta(Long id) {
        BigDecimal totalDebito = lancamentoContabilRepository.sumDebitoById(id);
        BigDecimal totalCredito = lancamentoContabilRepository.sumCreditoById(id);
        return totalDebito.subtract(totalCredito);
    }
	
	public RazaoAnalitico atualizarRazao(LancamentoContabil lancamento) {
        // Encontrar ou criar Razão para a conta de débito
        RazaoAnalitico razaoDebito = razaoAnaliticoRepository.findByConta(lancamento.getContaDebito())
                .orElse(new RazaoAnalitico(lancamento.getContaDebito()));

        // Atualizar saldo da conta de débito
        razaoDebito.setSaldo(razaoDebito.getSaldo().subtract(lancamento.getValor()));
        razaoAnaliticoRepository.save(razaoDebito);

        // Encontrar ou criar Razão para a conta de crédito
        RazaoAnalitico razaoCredito = razaoAnaliticoRepository.findByConta(lancamento.getContaCredito())
                .orElse(new RazaoAnalitico(lancamento.getContaCredito()));

        // Atualizar saldo da conta de crédito
        razaoCredito.setSaldo(razaoCredito.getSaldo().add(lancamento.getValor()));
        razaoAnaliticoRepository.save(razaoCredito);

        return razaoDebito; // ou retorno necessário
    }
	
}
