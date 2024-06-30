package com.ftn.modul3.zavrsni.jwd.Trotineti.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.TrotinetService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.support.TrotinetDtoToTrotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.support.TrotinetToTrotinetDto;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.TrotinetDTO;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.VracanjeTrotinetaDTO;



@RestController
@RequestMapping(value = "/api/trotineti", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrotinetController {
	
	
	@Autowired
	private TrotinetService service;
	
	
	@Autowired
	private TrotinetToTrotinetDto toDto;
	
	@Autowired 
	private TrotinetDtoToTrotinet toTrotinet;
	
	
	
		@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TrotinetDTO> create(@Valid @RequestBody TrotinetDTO tDto) {
			Trotinet t = toTrotinet.convert(tDto);

			if (t.getAdresa() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		

			Trotinet savedT=  service.save(t);

			return new ResponseEntity<>(toDto.convert(savedT), HttpStatus.CREATED);
		}
	 
	 
	 
	 
		@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TrotinetDTO> update(@PathVariable Long id, @Valid @RequestBody TrotinetDTO tDto) {

			if (!id.equals(tDto.getId())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Trotinet t = toTrotinet.convert(tDto);

			if (t.getAdresa() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		

			Trotinet savedT=  service.update(t);

			return new ResponseEntity<>(toDto.convert(savedT), HttpStatus.CREATED);
		}
		
		
		
		
		@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
		@PutMapping(value = "/charge/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TrotinetDTO> updatBat(@PathVariable Long id){
			
		
			Trotinet t= service.findOne(id);
			if(t==null) {
				  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			}else {
			
			

			  
			Trotinet tS = service.rechargeBat(id);
			
			  return new ResponseEntity<TrotinetDTO>(toDto.convert(tS),HttpStatus.OK);
		}
			}
		
		
		@PermitAll
		@PutMapping(value = "/return/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<VracanjeTrotinetaDTO> returnScooter(@PathVariable Long id,
				  @RequestBody VracanjeTrotinetaDTO requestBody){
			
			System.out.println(requestBody.getEmail() + "OVO JE EMAIL");
			 try {
					System.out.println(requestBody.getEmail() + "OVO JE EMAIL");

		            service.scooterReturn(id, requestBody.getStanjeBaterije(),requestBody.getEmail(),requestBody.getAdresaId());
		            return ResponseEntity.ok(requestBody);
		        } catch (Exception e) {
					System.out.println(requestBody.getEmail() + "OVO JE EMAIL");

					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		        }
			 
		}
		
		

		@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			Trotinet obrisant = service.delete(id);

			if (obrisant!= null) {

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		
		  @PermitAll
		  @GetMapping()
		  public ResponseEntity<List<TrotinetDTO>> getAll(
				  @RequestParam(required = false) Long adresaId,
				  @RequestParam(required = false) Integer batOd,
				  @RequestParam(required = false) Integer batDo,
				  @RequestParam(value = "pageNo",defaultValue = "0")int pageNo){
			  
			  Page<Trotinet>page;
			 
			  page=service.search(adresaId, batOd, batDo, pageNo);
		
			  HttpHeaders headers = new HttpHeaders();
		      headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		      
		      return new ResponseEntity<>(toDto.convert(page.getContent()), headers, HttpStatus.OK);
		  }
		  
		  
		  
		  
		   @ExceptionHandler(value = DataIntegrityViolationException.class)
		    public ResponseEntity<Void> handle() {
		        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		    }

	
	
	

}
