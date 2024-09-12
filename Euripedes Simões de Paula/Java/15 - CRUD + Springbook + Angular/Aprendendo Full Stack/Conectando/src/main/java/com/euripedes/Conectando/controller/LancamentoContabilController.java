package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.service.LancamentoContabilService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoContabilController {

    @Autowired
    private LancamentoContabilService lancamentoContabilService;

    // Método para criar um novo lançamento
    @PostMapping
    public ResponseEntity<LancamentoContabil> criarLancamento(@RequestBody LancamentoContabil novoLancamento) {
        LancamentoContabil lancamento = lancamentoContabilService.criarLancamento(novoLancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
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
        LancamentoContabil lancamento = lancamentoContabilService.atualizarLancamento(id, lancamentoAtualizado);
        return ResponseEntity.ok(lancamento);
    }

    // Método para deletar um lançamento
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarLancamento(@PathVariable Long id) {
        lancamentoContabilService.deletarLancamento(id);
        return ResponseEntity.noContent().build();
    }
}
