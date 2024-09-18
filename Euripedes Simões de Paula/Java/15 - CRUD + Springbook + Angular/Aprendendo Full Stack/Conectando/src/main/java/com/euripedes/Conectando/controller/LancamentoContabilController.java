package com.euripedes.Conectando.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.euripedes.Conectando.repository.BalanceteRepository;
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
    
    @Autowired
    private BalanceteRepository balanceteRepository;;
    


    // Método GET
    @GetMapping("/all")
    public ResponseEntity<List<LancamentoContabil>> getLancamentos() {
        List<LancamentoContabil> lancamentos = lancamentoContabilService.getAll();//.listarLancamentos();
        return ResponseEntity.ok(lancamentos);
    }

    // Método GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoContabil> getLancamentoById(@PathVariable Long id) {
        LancamentoContabil lancamento = lancamentoContabilService.getById(id);//.buscarLancamentoPorId(id);
        return ResponseEntity.ok(lancamento);
    }
    
    // Método POST
    @PostMapping("/registrar")
    public ResponseEntity<?> createTransacao(@RequestBody LancamentoContabilDto lancamentoDto) {
        // Cria o novo lançamento a partir dos dados do DTO
        LancamentoContabil lancamento = new LancamentoContabil();
        lancamento.setValor(lancamentoDto.getValor());
        lancamento.setData(lancamentoDto.getData());
        lancamento.setHistorico(lancamentoDto.getHistorico());

        // Define as contas crédito e débito
        Conta contaDebito = contaRepository.findById(lancamentoDto.getContaDebitoId()).orElseThrow();
        Conta contaCredito = contaRepository.findById(lancamentoDto.getContaCreditoId()).orElseThrow();
        lancamento.setDebitoId(contaDebito);
        lancamento.setCreditoId(contaCredito);

        // Salva o lançamento
        lancamentoContabilService.createLancamento(lancamento);

        // Atualiza o balancete
        //balanceteService.atualizarBalancete(lancamentoSalvo);

        return ResponseEntity.ok("Lançamento cadastrado com sucesso.");
    }

    // Método UPDATE
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LancamentoContabil> updateLancamento(
    	    @PathVariable Long id, 
    	    @RequestBody LancamentoContabil lancamentoAtualizado) {

    	    try {
    	        // Buscar o lançamento existente
    	        LancamentoContabil lancamentoExistente = lancamentoContabilRepository.findById(id)
    	            .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado"));

    	        // Atualiza os dados relevantes do lançamento
    	        lancamentoExistente.setCreditoId(lancamentoAtualizado.getCreditoId());
    	        lancamentoExistente.setDebitoId(lancamentoAtualizado.getDebitoId());
    	        lancamentoExistente.setValor(lancamentoAtualizado.getValor());
    	        lancamentoExistente.setData(lancamentoAtualizado.getData());
    	        lancamentoExistente.setHistorico(lancamentoAtualizado.getHistorico());

    	        // Salvar a atualização no banco
    	        LancamentoContabil lancamentoAtualizadoFinal = lancamentoContabilRepository.save(lancamentoExistente);

    	        return ResponseEntity.ok(lancamentoAtualizadoFinal);
    	    } catch (EntityNotFoundException e) {
    	        return ResponseEntity.notFound().build();
    	    } catch (Exception e) {
    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	    }
        }


//
//    // Método DELETE
//    @DeleteMapping("/deletar/{id}")
//    public ResponseEntity<Void> deleteLancamento(@PathVariable Long id) {
//        lancamentoContabilService.deletarLancamento(id);
//        balanceteService.excluirBalancetesPorLancamentoId(id);
//        return ResponseEntity.noContent().build();
//    }



}
