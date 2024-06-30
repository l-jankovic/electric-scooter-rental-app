package com.ftn.modul3.zavrsni.jwd.Trotineti.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Rezervacija;
import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.RezervacijaDTO;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.TrotinetDTO;

@Component
public class RezervacijeToRezervacijeDto  implements Converter<Rezervacija, RezervacijaDTO>{

	@Override
	public RezervacijaDTO convert(Rezervacija source) {
		RezervacijaDTO dto=  new RezervacijaDTO();
	
	
			dto.setId(source.getId());
			dto.setDatumIznajmiljvanja(source.getVremeIznajmljivanja());
			dto.setDatumVracanja(source.getVremeVracanja());
			dto.setEMail(source.getEMail());
			
			dto.setTrotinetId(source.getTrotinet().getId());
			dto.setTrotSfira(source.getTrotinet().getSifra());
	
			return dto;
	}
	
	
	 public List<RezervacijaDTO> convert(List<Rezervacija> rezvez){
	        List<RezervacijaDTO> dto = new ArrayList<>();

	        for(Rezervacija r: rezvez) {
	            dto.add(convert(r));
	        }

	        return dto;
	    }

}
