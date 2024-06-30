package com.ftn.modul3.zavrsni.jwd.Trotineti.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Adresa;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Korisnik;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.AdresaRepo;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.KorisnikRepository;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.RezervacijaRepo;
import com.ftn.modul3.zavrsni.jwd.Trotineti.repository.TrotinetRepo;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.TrotinetService;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class JPATrotinetService implements TrotinetService {

	
	@Autowired
	private TrotinetRepo trotinetRepo;
	
	@Autowired
	private RezervacijaRepo rezervacijaRepo;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private AdresaRepo adresaRepo;
	
	@Override
	public Trotinet findOne(Long id) {
		return trotinetRepo.findById(id).orElseGet(null);
		
	}

	@Override
	public List<Trotinet> findAll() {
		return trotinetRepo.findAll();
	}

	@Override
	public Page<Trotinet> findAll(int pageNo) {
		return trotinetRepo.findAll(PageRequest.of(pageNo, 4));
	}

	public Page<Trotinet> search(Long adresaId, Integer nivoBatOd, Integer nivoBatDo, int page) {
	    System.out.println(nivoBatOd);

		nivoBatOd = (nivoBatOd != null) ? nivoBatOd : 0;
	    nivoBatDo = (nivoBatDo != null) ? nivoBatDo : Integer.MAX_VALUE;
	    
	    System.out.println(nivoBatOd);
	    System.out.println(nivoBatDo);
	    PageRequest pageRequest = PageRequest.of(page, 4);

	    if (adresaId == null) {
	        return trotinetRepo.findByNivoBatBetween(nivoBatOd, nivoBatDo, pageRequest);
	    } else {
	        return trotinetRepo.findByAdresaIdAndNivoBatBetween(adresaId, nivoBatOd, nivoBatDo, pageRequest);
	    }
	}

	@Transactional
	@Override
	public Trotinet save(Trotinet tro) {
		
		tro.setIznajmljen(false);
		tro.setNivoBat(100);
		return trotinetRepo.save(tro);
	}

	@Transactional
	@Override
	public Trotinet update(Trotinet tro) {
		return trotinetRepo.save(tro);
	}

	@Transactional
	@Override
	public Trotinet delete(Long id) {
		Trotinet t = findOne(id);
		
		if(t!=null) {
			
			t.getAdresa().getTrotineti().remove(t);
			trotinetRepo.delete(t);
			
			return t;
		}
		
		return null;
	}

	@Override
	public Trotinet rechargeBat(Long id) {
		Trotinet t = findOne(id);
		
		t.setNivoBat(100);
		
		update(t);
		
		return t;
	}

	
	

	@Transactional
	@Override
	public Trotinet scooterReturn(Long trotinetId, int stanjeBaterije, String emailKorisnika, Long novaAdresaId) {
		System.out.println("OVO JE ID trota " + trotinetId);
		System.out.println("OVO JE ID adrese " + novaAdresaId);
		Optional<Korisnik> k= korisnikRepository.findByEmail(emailKorisnika);
		System.out.println("Ovo je user " + k.get().getEmail());
		System.out.println("EMAIL USAO SERVICE" + emailKorisnika);
		if(k.get().getEmail().equals(emailKorisnika)){
		Trotinet t= findOne(trotinetId);
		System.out.println(t.getSifra());

	Rezervacija rezervacija = rezervacijaRepo.findAktivnaRezervacijaZaTrotinet(trotinetId);
		System.out.println("Id rezervacie " + rezervacija.getId());
		
		t.setNivoBat(stanjeBaterije);
		t.setIznajmljen(false);
		
		System.out.println("DA LI JE TROTINET IZNAJMLJEN " + t.isIznajmljen());
		Adresa a = adresaRepo.findOneById(novaAdresaId);
		t.setAdresa(a);
		System.out.println("Adresa repo radi " + t.getAdresa().getUlica());
		rezervacija.setVremeVracanja(LocalDateTime.now());
		rezervacija.setEMail(emailKorisnika);
		System.out.println(t.getAdresa().getBroj() + "OVO JE ADRESA");
		update(t);
		rezervacijaRepo.save(rezervacija);
		return t;
		}else 
			System.out.println("USAO U THROW U SERVICE");
			throw new IllegalArgumentException("The user email doesn't match");
		}
	}


