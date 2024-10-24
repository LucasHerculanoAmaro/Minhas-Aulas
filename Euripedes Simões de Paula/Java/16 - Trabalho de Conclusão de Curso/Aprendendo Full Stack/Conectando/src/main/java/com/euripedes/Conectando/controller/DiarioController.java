package com.euripedes.Conectando.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedes.Conectando.repository.DiarioRepository;
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
    
//  Método GET
    @GetMapping("/transacoes")
    public List<Diario> listarTransacoes() {
        return diarioRepository.findAll();
    }
    
//  Método POST
    @PostMapping("/registrar")
    public Diario createTransacao(@RequestBody Diario diarioRequest, @RequestHeader(value = "Usuario", required = false) String usuario) {

//		Para caso de teste não identificado
    	if (usuario == null) {	usuario = "admin";	}
    	
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
        
        // Atualiza o Diário
        diarioService.createDiario(diarioRequest, usuario);

        // Atualiza o Balancete para as contas de crédito e débito
        balanceteService.atualizarBalancete(novoDiario);
        
        // Atualiza o Razao
        razaoService.criarRazao(novoDiario);

        // Salva o lançamento
        return novoDiario;
    }
    
//  Método PUT
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Diario> atualizarTransacao(@PathVariable Long id, @RequestBody Diario transacaoAtualizada) {
        try {
            Diario diarioAtualizado = diarioService.updateDiario(id, transacaoAtualizada, "Atualizando Transação.");
            return ResponseEntity.ok(diarioAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
//  Método DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        boolean isDeleted = diarioService.deletarTransacao(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
/*	Início da implementação para filtros	*/
    
//	Filtro por ID do Diário
    @GetMapping("/{diarioId}")
    public ResponseEntity<Diario> buscarPorId(@PathVariable Long diarioId) {
        return diarioService.buscarPorId(diarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//	Filtro por Intervalo de Datas
    @GetMapping("/datas")
    public ResponseEntity<List<Diario>> buscarPorIntervaloDeDatas(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Diario> resultado = diarioService.buscarPorIntervaloDeDatas(startDate, endDate);
        return ResponseEntity.ok(resultado);
    }

//	Filtro por Conta de Crédito
    @GetMapping("/credito/{creditoId}")
    public ResponseEntity<List<Diario>> buscarPorContaCredito(@PathVariable Long creditoId) {
        List<Diario> resultado = diarioService.buscarPorContaCredito(creditoId);
        return ResponseEntity.ok(resultado);
    }

//	Filtro por Conta de Débito
    @GetMapping("/debito/{debitoId}")
    public ResponseEntity<List<Diario>> buscarPorContaDebito(@PathVariable Long debitoId) {
        List<Diario> resultado = diarioService.buscarPorContaDebito(debitoId);
        return ResponseEntity.ok(resultado);
    }

//	Filtro por Intervalo de Valores
    @GetMapping("/valores")
    public ResponseEntity<List<Diario>> buscarPorValorIntervalo(
        @RequestParam BigDecimal valorMinimo,
        @RequestParam BigDecimal valorMaximo) {
        List<Diario> resultado = diarioService.buscarPorValorIntervalo(valorMinimo, valorMaximo);
        return ResponseEntity.ok(resultado);
    }

//	Filtro por Histórico
    @GetMapping("/historico")
    public ResponseEntity<List<Diario>> buscarPorHistorico(@RequestParam String palavra) {
        List<Diario> resultado = diarioService.buscarPorHistorico(palavra);
        return ResponseEntity.ok(resultado);
    }

}
