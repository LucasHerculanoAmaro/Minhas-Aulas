package com.euripedes.Conectando.controller;

import java.util.List;

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
import com.euripedes.Conectando.repository.RazaoRepository;
import com.euripedes.Conectando.service.BalanceteService;
import com.euripedes.Conectando.service.DiarioService;
import com.euripedes.Conectando.service.RazaoService;
import com.euripedesConectando.ResourceNotFoundException.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/diario")
public class DiarioController {
    
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private DiarioRepository diarioRepository;
    @Autowired
    private BalanceteService balanceteService;
    @Autowired
    private DiarioService diarioService;
    @Autowired
    private RazaoService razaoService;
    
    
    
    @GetMapping("/transacoes")
    public List<Diario> listarTransacoes() {
        return diarioRepository.findAll();
    }
    
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
        
        // Atualiza o Razao
        razaoService.atualizarRazao(novoDiario);

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

}
