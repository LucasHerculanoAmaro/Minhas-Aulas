package com.euripedes.Conectando.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.model.LancamentoContabilDto;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;
import com.euripedes.Conectando.service.BalanceteService;
import com.euripedes.Conectando.service.LancamentoContabilService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/lancamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LancamentoContabilController {

	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
    @Autowired
    private LancamentoContabilService lancamentoContabilService;
    
    @Autowired
    private BalanceteService balanceteService;

    
    @GetMapping("/test")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }
    
    // Método para criar um novo lançamento
    @PostMapping("/registrar")
    public ResponseEntity<?> cadastrarTransacao(@RequestBody LancamentoContabilDto lancamentoDto) {
        LancamentoContabil lancamento = new LancamentoContabil();
        lancamento.setValor(lancamentoDto.getValor());
        lancamento.setData(lancamentoDto.getData());
        lancamento.setHistorico(lancamentoDto.getHistorico());

        // Definir contas crédito e débito
        Conta contaDebito = contaRepository.findById(lancamentoDto.getContaDebitoId()).orElseThrow();
        Conta contaCredito = contaRepository.findById(lancamentoDto.getContaCreditoId()).orElseThrow();
        lancamento.setCodigoDebito(contaDebito);
        lancamento.setCodigoCredito(contaCredito);

        lancamentoContabilRepository.save(lancamento);  
        // Atualiza o balancete
        balanceteService.atualizarBalancete(lancamento);
        lancamentoContabilService.criarLancamento(lancamento); // código descomentado
        

        return ResponseEntity.ok("Lançamento cadastrado com sucesso.");
    }

    // Método para listar todos os lançamentos
    @GetMapping("/todos")
    public ResponseEntity<List<LancamentoContabil>> listarLancamentos() {
        List<LancamentoContabil> lancamentos = lancamentoContabilService.listarLancamentos();
        return ResponseEntity.ok(lancamentos);
    }

    // Método para buscar um lançamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoContabil> buscarLancamentoPorId(@PathVariable Long id) {
        LancamentoContabil lancamento = lancamentoContabilService.buscarLancamentoPorId(id);
        return ResponseEntity.ok(lancamento);
    }

    // Método para atualizar um lançamento
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LancamentoContabil> atualizarLancamento(@PathVariable Long id, @RequestBody LancamentoContabil lancamentoAtualizado) {
    	try {
    		LancamentoContabil lancamento = lancamentoContabilService.atualizarLancamento(id, lancamentoAtualizado);
    		return ResponseEntity.ok(lancamento);
    	} catch (EntityNotFoundException e) {
    		return ResponseEntity.notFound().build();
    	}
    }

    // Método para deletar um lançamento
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarLancamento(@PathVariable Long id) {
        lancamentoContabilService.deletarLancamento(id);
        balanceteService.excluirBalancetesPorLancamentoId(id);
        return ResponseEntity.noContent().build();
    }

}
