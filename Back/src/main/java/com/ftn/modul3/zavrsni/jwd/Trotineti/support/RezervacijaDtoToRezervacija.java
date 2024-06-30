package com.ftn.modul3.zavrsni.jwd.Trotineti.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.RezervacijaService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.TrotinetService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija>{
	
	@Autowired
	private RezervacijaService rezervacijaService;
	@Autowired
	private  TrotinetService trotinetService;
	@Override
	public Rezervacija convert(RezervacijaDTO source) {
		Rezervacija r;
		
		if(source.getId()==null) {
			r=new Rezervacija();
		}else {
			r= rezervacijaService.findOne(source.getId());
		}
		
		if(r!=null) {
			r.setEMail(source.getEMail());
			System.out.println(r.getEMail() + " OVO JE MAIL" );
			r.setVremeIznajmljivanja(source.getDatumIznajmiljvanja());
			r.setVremeVracanja(source.getDatumVracanja());
			r.setTrotinet(trotinetService.findOne(source.getTrotinetId()));
			
		}
		
		return r;
	}

}
