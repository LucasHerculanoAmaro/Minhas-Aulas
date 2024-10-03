package com.euripedes.Conectando.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.Diario;
import com.euripedes.Conectando.model.Historico;
import com.euripedes.Conectando.repository.HistoricoRepository;

@Service
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	public void registrarHistorico (Diario diario, String usuario, String descricaoAlteracao ) {
		
		Historico historico = new Historico();
		
		historico.setDiario(diario);
		historico.setUsuario(usuario);
		historico.setCampos(descricaoAlteracao);
		historico.setData(LocalDateTime.now());
		
		historicoRepository.save(historico);
		
	}
	
	public List<Historico> listarHistoricoPorDiario(Long diarioId) {
        return historicoRepository.findByDiarioId(diarioId);
    }
	
}
