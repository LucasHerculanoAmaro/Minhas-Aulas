package com.euripedes.Conectando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Conta;
import com.euripedes.Conectando.repository.ContaRepository;
import com.euripedesConectando.ResourceNotFoundException.ResourceNotFoundException;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;  // Repositório para buscar contas

    public Conta findByNumero(String codigo) {
        return contaRepository.findByCodigo(codigo)
               .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com número: " + codigo));
    }
}
