package com.ftn.modul3.zavrsni.jwd.Trotineti.web.controller;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Adresa;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.AdresaService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.support.AdresaToAdresaDto;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.AdresaDTO;


@RestController
@RequestMapping(value = "/api/adrese",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdresaController {
	
	
	@Autowired
	private AdresaService adresaService;
	
	@Autowired
	private AdresaToAdresaDto toDto;
	
	
	@PermitAll
	@GetMapping
	public ResponseEntity<List<AdresaDTO>> getAll(){
		
		List<Adresa> adrese= adresaService.findAll();
		
	        
	        
		 return new ResponseEntity<>(toDto.convert(adrese),HttpStatus.OK);

	}
	
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Void> handle() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	

}
