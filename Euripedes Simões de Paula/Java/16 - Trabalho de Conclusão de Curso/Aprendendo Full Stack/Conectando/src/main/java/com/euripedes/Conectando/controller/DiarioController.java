package com.euripedes.Conectando.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.model.Usuario;
import com.euripedes.Conectando.repository.DiarioRepository;
import com.euripedes.Conectando.service.DiarioService;
import com.euripedesConectando.ResourceNotFoundException.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/diario")
@CrossOrigin(origins = "http://localhost:4200")
public class DiarioController {
    
    @Autowired
    private DiarioRepository diarioRepository;
    @Autowired
    private DiarioService diarioService;
    
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
        
        // Salva o lançamento no Diário
        Diario novoDiario = diarioRepository.save(diarioRequest);
        
        // Atualiza o Diário
        diarioService.createDiario(diarioRequest, usuario);

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
    public ResponseEntity<Map<String, Boolean>> deletarTransacao(@PathVariable Long id) {
        Diario diario = diarioRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Diário não encontrado"));
        
        diarioRepository.delete(diario);
//        diarioService.deletarTransacao(id);
        
        Map<String, Boolean> response = new HashMap<>();
        
        response.put("Deletado", Boolean.TRUE);
        
        return ResponseEntity.ok(response);
    }
    
/*	Início da implementação para filtros	*/
    
//  Método GET por ID
    @GetMapping("/transacoes/{id}")
	public ResponseEntity<Diario> getDiarioById(@PathVariable Long id) {
		Diario diario = diarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Diário não encontrado"));
		return ResponseEntity.ok(diario);
	}
    
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
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {    	
        List<Diario> resultado = diarioService.buscarPorIntervaloDeDatas(startDate, endDate);
        
        System.out.println("Resultados encontrados: " + resultado.size());
        
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
