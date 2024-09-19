package com.euripedes.Conectando.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.DiarioRepository;
import com.euripedes.Conectando.service.DiarioService;

@RestController
@RequestMapping("/diario")
public class DiarioController {

    @Autowired
    private DiarioService diarioService;
    
    @Autowired
    private DiarioRepository diarioRepository;
    
    @GetMapping("/transacoes")
    public List<Diario> listarTransacoes() {
        return diarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Diario> createLancamento(@RequestBody Diario diario) {
        Diario novoDiario = diarioService.createDiario(diario);
        return ResponseEntity.ok(novoDiario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diario> updateLancamento(@PathVariable Long id, @RequestBody Diario diarioAtualizado) {
		Diario diarioAtualizadoFinal = diarioService.updateDiario(id, diarioAtualizado);
        return ResponseEntity.ok(diarioAtualizadoFinal);
    }
    
    @PutMapping("/transacoes/{id}")
    public ResponseEntity<Diario> atualizarTransacao(@PathVariable Long id, @RequestBody Diario transacaoAtualizada) {
        Optional<Diario> optionalDiario = diarioRepository.findById(id);
        if (optionalDiario.isPresent()) {
            Diario diario = optionalDiario.get();
            diario.setCredito(transacaoAtualizada.getCredito());
            diario.setDebito(transacaoAtualizada.getDebito());
            diario.setData(transacaoAtualizada.getData());
            diario.setHistorico(transacaoAtualizada.getHistorico());
            diario.setValor(transacaoAtualizada.getValor());
            diarioRepository.save(diario);
            return ResponseEntity.ok(diario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
