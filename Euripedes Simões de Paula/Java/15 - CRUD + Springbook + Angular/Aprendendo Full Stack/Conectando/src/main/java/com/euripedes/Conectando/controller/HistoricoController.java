package com.euripedes.Conectando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Historico;
import com.euripedes.Conectando.repository.HistoricoRepository;

@RestController
@RequestMapping("historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    // Endpoint para listar o histórico de um lançamento
    @GetMapping("/{id}")
    public ResponseEntity<List<Historico>> getHistoricoLancamento(@PathVariable Long id) {
        List<Historico> historico = historicoRepository.findByDiarioId(id);
        return ResponseEntity.ok(historico);
    }
}
