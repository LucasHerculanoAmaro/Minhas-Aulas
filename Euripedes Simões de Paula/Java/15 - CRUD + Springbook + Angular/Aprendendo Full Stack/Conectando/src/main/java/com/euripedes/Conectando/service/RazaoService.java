package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
		razaoDebito.setDiarioId(diario.getId());

		// Operação de Crédito
		BigDecimal creditoAtual = razaoCredito.getCredito() != null ? razaoCredito.getCredito() : BigDecimal.ZERO;
		razaoCredito.setCredito(creditoAtual.add(valor));  // Adiciona o valor ao saldo de crédito
		razaoCredito.setDebito(BigDecimal.ZERO);  // Zera o débito, já que é uma operação de crédito
		razaoCredito.setData(diario.getData());
		razaoCredito.setHistorico(diario.getHistorico());
		razaoCredito.setDiarioId(diario.getId());

		// Salvar alterações no Razão
		razaoRepository.save(razaoDebito);
		razaoRepository.save(razaoCredito);
	
	}
	
	// Serviço para busca por ID
    public List<Razao> buscarPorDiarioId(Long diarioId) {
        return razaoRepository.findByDiarioId(diarioId);
    }
    
//  Serviço para busca por Data
    public List<Razao> buscarData(LocalDate data) {
    	return razaoRepository.findByData(data);
    }
    
//	Serviço para busca entre datas
    public List<Razao> buscarPorIntervaloDeDatas(LocalDate startDate, LocalDate endDate) {
    	return razaoRepository.findByDataBetween(startDate, endDate);
    }
    
//	Serviço para busca por Conta
    public List<Razao> buscarPorContaId(Long contaId) {
    	return razaoRepository.findByConta_Id(contaId);
    }
    
//  Serviço para busca por Histórico
    public List<Razao> buscarPorHistorico(String palavra) {
    	return razaoRepository.findByHistoricoContaining(palavra);
    }
    
//  Serviço para busca por Débito
    public List<Razao> buscarPorDebitoMaiorQue(BigDecimal valorMinimo) {
    	return razaoRepository.findByDebitoGreaterThan(valorMinimo);
    }
    
//  Serviço para busca por Crédito
    public List<Razao> buscarPorCreditoMaiorQue(BigDecimal valorMinimo) {
    	return razaoRepository.findByCreditoGreaterThan(valorMinimo);
    }
	
//  Serviço para busca por Intervalo de Valores
    public List<Razao> buscarPorValorIntervalo(BigDecimal valorMinimo, BigDecimal valorMaximo) {
    	
//    	List<Razao> creditos = razaoRepository.findByCreditoBetween(valorMinimo, valorMaximo);
//    	List<Razao> debitos = razaoRepository.findByDebitoBetween(valorMinimo, valorMaximo);
//    	
//        // Combina as duas listas
//        List<Razao> resultado = new ArrayList<>();
//        resultado.addAll(creditos);
//        resultado.addAll(debitos);
//
//        return resultado;
    	
    	return razaoRepository.findByCreditoOrDebitoBetween(valorMinimo, valorMaximo);
    	
    }
    
}
