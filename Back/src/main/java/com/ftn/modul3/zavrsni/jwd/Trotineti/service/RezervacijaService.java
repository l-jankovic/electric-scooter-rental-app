package com.ftn.modul3.zavrsni.jwd.Trotineti.service;

import java.util.List;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;

public interface RezervacijaService {
	

    Rezervacija findOne(Long id);

    List< Rezervacija> findAll();
    
    Rezervacija save(Rezervacija rez);



}
