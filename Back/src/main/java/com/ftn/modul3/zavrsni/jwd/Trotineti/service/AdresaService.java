package com.ftn.modul3.zavrsni.jwd.Trotineti.service;

import java.util.List;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Adresa;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;

public interface AdresaService {
	

    Adresa findOne(Long id);

    List<Adresa> findAll();

}
