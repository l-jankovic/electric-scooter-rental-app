package com.ftn.modul3.zavrsni.jwd.Trotineti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Adresa;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.AdresaRepo;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.AdresaService;

@Service
public class JPAAdresaService implements AdresaService {

	
	@Autowired
	private AdresaRepo repo;
	@Override
	public Adresa findOne(Long id) {
		return repo.findById(id).orElseGet(null);
	}

	@Override
	public List<Adresa> findAll() {
		return repo.findAll();
	}

}
