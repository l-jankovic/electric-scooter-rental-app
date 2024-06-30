package com.ftn.modul3.zavrsni.jwd.Trotineti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;

@Repository
public interface RezervacijaRepo extends JpaRepository<Rezervacija, Long> {

	

    @Query("SELECT r FROM Rezervacija r " +
           "WHERE r.trotinet.id = :trotinetId " +
           "AND r.vremeVracanja IS NULL")
    Rezervacija findAktivnaRezervacijaZaTrotinet(@Param("trotinetId") Long trotinetId);
}
