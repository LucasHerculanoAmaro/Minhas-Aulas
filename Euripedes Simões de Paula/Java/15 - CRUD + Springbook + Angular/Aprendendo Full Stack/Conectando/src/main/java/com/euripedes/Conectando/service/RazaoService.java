package com.euripedes.Conectando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euripedes.Conectando.repository.RazaoAnaliticoRepository;

@Service
public class RazaoService {

	@Autowired
	private RazaoAnaliticoRepository razaoRepository;
	
	public List<RazaoAnaliticoRepository> obterMovimentacoesporConta(String conta) {
		return razaoRepository.findByConta(conta);
	}
	
}
