package com.euripedes.Conectando.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.model.DiarioGeral;
import com.euripedes.Conectando.repository.DiarioGeralRepository;

@Service
public class DiarioService {

	@Autowired
	private DiarioGeralRepository diarioRepository;
	
	public List<DiarioGeral> obterTransacoesPorDatas(LocalDate inicio, LocalDate fim) {
		return diarioRepository.findByDataBetween(inicio, fim);
	}
	
}
