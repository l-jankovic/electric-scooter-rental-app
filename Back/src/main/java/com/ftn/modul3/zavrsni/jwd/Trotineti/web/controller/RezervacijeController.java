package com.ftn.modul3.zavrsni.jwd.Trotineti.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.RezervacijaService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.support.RezervacijaDtoToRezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.support.RezervacijeToRezervacijeDto;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.RezervacijaDTO;


@RestController
@RequestMapping(value = "/api/rezervacije",produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijeController {
	
	
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	
	@Autowired
	private RezervacijeToRezervacijeDto toDto;
	
	@Autowired
	private RezervacijaDtoToRezervacija toRezervacija;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<RezervacijaDTO>> getAll(){
		
		List<Rezervacija> rezervacijas= rezervacijaService.findAll();
		
	        
	        
		 return new ResponseEntity<>(toDto.convert(rezervacijas),HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO rDto) {
		Rezervacija r = toRezervacija.convert(rDto);

		if (r.getTrotinet() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	

		Rezervacija savedR=  rezervacijaService.save(r);

		return new ResponseEntity<>(toDto.convert(savedR), HttpStatus.CREATED);	}

	
	
	
	  @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	  @GetMapping("/{id}")
	    public ResponseEntity<RezervacijaDTO> getOne(@PathVariable Long id){
		 Rezervacija r = rezervacijaService.findOne(id);

	        if(r != null) {
	            return new ResponseEntity<>(toDto.convert(r), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
		
		
	
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Void> handle() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
