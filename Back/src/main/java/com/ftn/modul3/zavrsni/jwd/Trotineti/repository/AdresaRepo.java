package com.ftn.modul3.zavrsni.jwd.Trotineti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Adresa;

@Repository
public interface AdresaRepo extends JpaRepository<Adresa, Long> {
	Adresa findOneById(Long id);
}
