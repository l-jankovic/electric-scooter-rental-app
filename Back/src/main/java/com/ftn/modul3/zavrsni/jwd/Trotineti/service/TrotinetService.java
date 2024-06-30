package com.ftn.modul3.zavrsni.jwd.Trotineti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;


public interface TrotinetService {

	

    Trotinet findOne(Long id);

    List< Trotinet> findAll();
    
    Page<Trotinet> findAll(int pageNo);

    Page<Trotinet> search(Long adresaId,Integer nivoBatOd,Integer nivoBatDo, int page);

    Trotinet save(Trotinet tro);

    Trotinet update(Trotinet tro);
    
    Trotinet scooterReturn(Long trotinetId, int stanjeBaterije, String emailKorisnika, Long novaAdresaId);
    
    Trotinet delete(Long id);
    
    Trotinet rechargeBat(Long id);
}
