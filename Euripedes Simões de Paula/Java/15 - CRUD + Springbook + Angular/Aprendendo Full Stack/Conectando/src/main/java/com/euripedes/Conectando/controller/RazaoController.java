package com.euripedes.Conectando.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.euripedes.Conectando.model.Razao;
import com.euripedes.Conectando.service.RazaoService;

@RestController
@RequestMapping("/razao")
public class RazaoController {

    @Autowired
    private RazaoService razaoService;
    
    @GetMapping("/diario/{diarioId}")
    public List<Razao> buscarPorDiarioId(@PathVariable Long diarioId) {
        return razaoService.buscarPorDiarioId(diarioId);
    }
    
    @GetMapping("/data")
    public List<Razao> buscarPorData(@RequestParam LocalDate data) {
        return razaoService.buscarData(data);
    }

    @GetMapping("/data-range")
    public List<Razao> buscarPorIntervaloDeDatas(
        @RequestParam LocalDate startDate, 
        @RequestParam LocalDate endDate) {
        return razaoService.buscarPorIntervaloDeDatas(startDate, endDate);
    }
    
    @GetMapping("/conta/{contaId}")
    public List<Razao> buscarPorContaId(@PathVariable Long contaId) {
        return razaoService.buscarPorContaId(contaId);
    }
    
    @GetMapping("/historico")
    public List<Razao> buscarPorHistorico(@RequestParam String palavra) {
        return razaoService.buscarPorHistorico(palavra);
    }
    
    @GetMapping("/debito")
    public List<Razao> buscarPorDebito(@RequestParam BigDecimal valorMinimo) {
        return razaoService.buscarPorDebitoMaiorQue(valorMinimo);
    }

    @GetMapping("/credito")
    public List<Razao> buscarPorCredito(@RequestParam BigDecimal valorMinimo) {
        return razaoService.buscarPorCreditoMaiorQue(valorMinimo);
    }
    
    @GetMapping("/valor-range")
    public List<Razao> buscarPorValorIntervalo(
        @RequestParam BigDecimal valorMinimo, 
        @RequestParam BigDecimal valorMaximo) {
        return razaoService.buscarPorValorIntervalo(valorMinimo, valorMaximo);
    }

    
}
