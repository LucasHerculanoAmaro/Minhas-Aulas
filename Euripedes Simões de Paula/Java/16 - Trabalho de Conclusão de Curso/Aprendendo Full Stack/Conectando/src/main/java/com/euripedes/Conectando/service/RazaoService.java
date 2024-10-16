package com.euripedes.Conectando.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.model.Razao;
import com.euripedes.Conectando.repository.DiarioRepository;
import com.euripedes.Conectando.repository.RazaoRepository;

import jakarta.transaction.Transactional;

@Service
public class RazaoService {

	@Autowired
	private RazaoRepository razaoRepository;
	
	@Autowired DiarioRepository diarioRepository;
	
	@Transactional
	public void criarRazao(Diario diario) {

		// Obtendo o valor do Diário e seu ID
	    BigDecimal valor = BigDecimal.valueOf(diario.getValor());
	    Long diarioId = diario.getId();

	    // Buscar o objeto Diário pelo ID para garantir que ele existe
	    Diario diarioObj = diarioRepository.findById(diarioId)
	        .orElseThrow(() -> new RuntimeException("Diário não encontrado"));

	    // Verificar se já existe um registro de Razão para a conta de débito
	    Optional<Razao> razaoDebitoOptional = razaoRepository.findByContaIdAndDiarioId(diario.getDebito().getId(), diarioId);
	    Razao razaoDebito;

	    // Se existir, atualiza o registro, senão, cria um novo
	    if (razaoDebitoOptional.isPresent()) {
	        razaoDebito = razaoDebitoOptional.get();
	        // Atualiza o débito somando o valor atual
	        razaoDebito.setDebito(razaoDebito.getDebito().add(valor));
	    } else {
	        razaoDebito = new Razao();
	        // Se for novo, inicializa com as informações de débito
	        razaoDebito.setConta(diario.getDebito());
	        razaoDebito.setDebito(valor); // Inicia com o valor do débito
	        razaoDebito.setCredito(BigDecimal.ZERO); // Crédito é zero porque é débito
	    }
	    // Configurações gerais para ambos os casos (novo ou existente)
	    razaoDebito.setData(diario.getData());
	    razaoDebito.setHistorico(diario.getHistorico());
	    razaoDebito.setDiario(diarioObj);

	    // Verificar se já existe um registro de Razão para a conta de crédito
	    Optional<Razao> razaoCreditoOptional = razaoRepository.findByContaIdAndDiarioId(diario.getCredito().getId(), diarioId);
	    Razao razaoCredito;

	    // Se existir, atualiza o registro, senão, cria um novo
	    if (razaoCreditoOptional.isPresent()) {
	        razaoCredito = razaoCreditoOptional.get();
	        // Atualiza o crédito somando o valor atual
	        razaoCredito.setCredito(razaoCredito.getCredito().add(valor));
	    } else {
	        razaoCredito = new Razao();
	        // Se for novo, inicializa com as informações de crédito
	        razaoCredito.setConta(diario.getCredito());
	        razaoCredito.setCredito(valor); // Inicia com o valor do crédito
	        razaoCredito.setDebito(BigDecimal.ZERO); // Débito é zero porque é crédito
	    }
	    // Configurações gerais para ambos os casos (novo ou existente)
	    razaoCredito.setData(diario.getData());
	    razaoCredito.setHistorico(diario.getHistorico());
	    razaoCredito.setDiario(diarioObj);

	    // Salvar ou atualizar ambos os registros no Razão
	    razaoRepository.save(razaoDebito);
	    razaoRepository.save(razaoCredito);
		
	}
	
	@Transactional
	public void atualizarRazao(Diario diarioAtualizado, Diario diarioAnterior) {
		
		BigDecimal valorAtualizado = BigDecimal.valueOf(diarioAtualizado.getValor());
		Long diarioId = diarioAtualizado.getId();

		// Atualizar Razão para a conta de débito
		Razao razaoDebito = razaoRepository.findByContaIdAndDiarioId(diarioAtualizado.getDebito().getId(), diarioId)
		        .orElseThrow(() -> new RuntimeException("Registro de Razão não encontrado para a conta de débito"));

		// Atualizar Razão para a conta de crédito
		Razao razaoCredito = razaoRepository.findByContaIdAndDiarioId(diarioAtualizado.getCredito().getId(), diarioId)
		        .orElseThrow(() -> new RuntimeException("Registro de Razão não encontrado para a conta de crédito"));

		// Verificar e atualizar os valores de débito e crédito corretamente
		if (razaoDebito != null && razaoCredito != null) {
		    
		    // Atualiza o valor do débito
		    if (diarioAtualizado.getDebito() != null) {
		        razaoDebito.setDebito(valorAtualizado);  // Aplica o novo valor de débito
		        razaoDebito.setCredito(BigDecimal.ZERO); // Garante que o crédito esteja zerado
		    }
		    
		    // Atualiza o valor do crédito
		    if (diarioAtualizado.getCredito() != null) {
		        razaoCredito.setCredito(valorAtualizado);  // Aplica o novo valor de crédito
		        razaoCredito.setDebito(BigDecimal.ZERO);   // Garante que o débito esteja zerado
		    }
		    
		    // Atualiza data e histórico para ambos os registros
		    razaoDebito.setData(diarioAtualizado.getData());
		    razaoDebito.setHistorico(diarioAtualizado.getHistorico());

		    razaoCredito.setData(diarioAtualizado.getData());
		    razaoCredito.setHistorico(diarioAtualizado.getHistorico());

		    // Salvar as atualizações
		    razaoRepository.save(razaoDebito);
		    razaoRepository.save(razaoCredito);
		}
		
	}
	
//	Serviço para busca por ID
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
    	
    	return razaoRepository.findByCreditoOrDebitoBetween(valorMinimo, valorMaximo);
    	
    }
    
}
