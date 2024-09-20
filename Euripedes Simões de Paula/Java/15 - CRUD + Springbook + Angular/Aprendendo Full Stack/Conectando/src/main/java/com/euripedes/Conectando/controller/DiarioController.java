package com.euripedes.Conectando.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.DiarioRepository;
import com.euripedes.Conectando.service.BalanceteService;
import com.euripedes.Conectando.service.DiarioService;
import com.euripedesConectando.ResourceNotFoundException.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/diario")
public class DiarioController {

    @Autowired
    private DiarioService diarioService;
    
    @Autowired
    private DiarioRepository diarioRepository;
    
    @Autowired
    private ContaRepository contaRepository;
    
    @Autowired
    private BalanceteService balanceteService;
    
    @GetMapping("/transacoes")
    public List<Diario> listarTransacoes() {
        return diarioRepository.findAll();
    }

//    @PostMapping
//    public ResponseEntity<Diario> createLancamento(@RequestBody Diario diario) {
//        Diario novoDiario = diarioService.createDiario(diario);
//        return ResponseEntity.ok(novoDiario);
//    }
    
    @PostMapping("/registrar")
    public Diario createTransacao(@RequestBody Diario diarioRequest) {

        // Busca a Conta de crédito usando o creditoId
        Conta contaCredito = contaRepository.findById(diarioRequest.getCredito().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Conta crédito não encontrada"));

        // Busca a Conta de débito usando o debitoId
        Conta contaDebito = contaRepository.findById(diarioRequest.getDebito().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Conta débito não encontrada"));

        // Associa as contas ao lançamento
        diarioRequest.setCredito(contaCredito);
        diarioRequest.setDebito(contaDebito);
        
        // Salva o lançamento no Diário
        Diario novoDiario = diarioRepository.save(diarioRequest);

        // Atualiza o Balancete para as contas de crédito e débito
        balanceteService.atualizarBalancete(novoDiario);

        // Salva o lançamento
        return novoDiario;
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Diario> atualizarTransacao(@PathVariable Long id, @RequestBody Diario transacaoAtualizada) {
        try {
            Diario diarioAtualizado = diarioService.updateDiario(id, transacaoAtualizada);
            return ResponseEntity.ok(diarioAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        boolean isDeleted = diarioService.deletarTransacao(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Diario> updateLancamento(@PathVariable Long id, @RequestBody Diario diarioAtualizado) {
//		Diario diarioAtualizadoFinal = diarioService.updateDiario(id, diarioAtualizado);
//        return ResponseEntity.ok(diarioAtualizadoFinal);
//    }
    

// ###################### UPDATE FUNCIONAL ############################################
//	@PutMapping("/atualizar/{id}")
//    public ResponseEntity<Diario> atualizarTransacao(@PathVariable Long id, @RequestBody Diario transacaoAtualizada) {
//        Optional<Diario> optionalDiario = diarioRepository.findById(id);
//        if (optionalDiario.isPresent()) {
//            Diario diario = optionalDiario.get();
//            diario.setCredito(transacaoAtualizada.getCredito());
//            diario.setDebito(transacaoAtualizada.getDebito());
//            diario.setData(transacaoAtualizada.getData());
//            diario.setHistorico(transacaoAtualizada.getHistorico());
//            diario.setValor(transacaoAtualizada.getValor());
//            diarioRepository.save(diario);
//            return ResponseEntity.ok(diario);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
// ###################### FIM DO UPDATE FUNCIONAL ############################################


}
