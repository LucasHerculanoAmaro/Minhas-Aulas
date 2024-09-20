package com.euripedes.Conectando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.repository.DiarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DiarioService {

    @Autowired
    private DiarioRepository diarioRepository;

    @Autowired
    private BalanceteService balanceteService;

    public Diario createDiario(Diario diario) {

        // Salvar o lançamento contábil
        Diario novodiario = diarioRepository.save(diario);

        // Atualizar o balancete
        balanceteService.atualizarBalancete(novodiario);

        return novodiario;
    }

    public Diario updateDiario(Long id, Diario diarioAtualizado) {
        Diario diarioExistente = diarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lançamento não encontrado"));

        diarioExistente.setCredito(diarioAtualizado.getCredito());
        diarioExistente.setDebito(diarioAtualizado.getDebito());
        diarioExistente.setValor(diarioAtualizado.getValor());
        diarioExistente.setData(diarioAtualizado.getData());
        diarioExistente.setHistorico(diarioAtualizado.getHistorico());

        Diario diarioAtualizadoFinal = diarioRepository.save(diarioExistente);

        // Atualizar o balancete
        balanceteService.atualizarBalancete(diarioAtualizadoFinal);

        return diarioAtualizadoFinal;
    }
    
    public boolean deletarTransacao(Long id) {
        Optional<Diario> optionalDiario = diarioRepository.findById(id);
        if (optionalDiario.isPresent()) {
            Diario diario = optionalDiario.get();
            
            // Atualizar o balancete com base no lançamento antes de deletá-lo
            balanceteService.atualizarBalanceteAoDeletarDiario(diario);
            
            // Excluir o Diário
            diarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

