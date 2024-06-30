package com.ftn.modul3.zavrsni.jwd.Trotineti.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Korisnik;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.KorisnikRepository;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.RezervacijaRepo;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.RezervacijaService;

@Service
public class JPARezervacijaService implements RezervacijaService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired
	private RezervacijaRepo repo;
	@Override
	public Rezervacija findOne(Long id) {
		return repo.findById(id).orElseGet(null);
	}

	@Override
	public List<Rezervacija> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	@Override
	public Rezervacija save(Rezervacija rez) {
		
		rez.setVremeIznajmljivanja(LocalDateTime.now());
		Trotinet t = rez.getTrotinet();
		t.setIznajmljen(true);
		return repo.save(rez);
	}



}
