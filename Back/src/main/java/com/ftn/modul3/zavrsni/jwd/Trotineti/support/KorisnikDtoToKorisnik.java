package com.ftn.modul3.zavrsni.jwd.Trotineti.support;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Korisnik;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.KorisnikService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.KorisnikDTO;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik> {

    @Autowired
    private KorisnikService korisnikService;



    @Override
    public Korisnik convert(KorisnikDTO korisnikDTO) {
        Korisnik entity = null;

        if(korisnikDTO.getId() == null) {
            entity = new Korisnik();
        }else {
            Optional<Korisnik> korisnikOptional = korisnikService.findOne(korisnikDTO.getId());
            if(korisnikOptional.isPresent()){
                entity = korisnikOptional.get();
            }
        }

        if(entity != null) {
            entity.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
            entity.setEmail(korisnikDTO.getEmail());
            entity.setIme(korisnikDTO.getIme());
            entity.setPrezime(korisnikDTO.getPrezime());
        }

        return entity;
    }

}
