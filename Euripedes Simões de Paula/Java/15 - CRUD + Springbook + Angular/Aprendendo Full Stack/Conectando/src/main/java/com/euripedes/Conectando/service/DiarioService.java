package com.euripedes.Conectando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.LancamentoContabil;
import com.euripedes.Conectando.repository.LancamentoContabilRepository;

@Service
public class DiarioService {

	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;
	
	public List<LancamentoContabil> listarLancamento() {
		return lancamentoContabilRepository.findAll();
	}
	
}
