package com.ftn.modul3.zavrsni.jwd.Trotineti.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Adresa;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.AdresaDTO;

;

@Component
public class AdresaToAdresaDto implements Converter<  Adresa,AdresaDTO>{



	@Override
	public AdresaDTO convert(Adresa source) {

		AdresaDTO dto= new AdresaDTO();
		
		dto.setId(source.getId());
		
		dto.setBroj(source.getBroj());
		
		dto.setUlica(source.getUlica());
	
		
		return dto;
	}
	
	
	
	 public List<AdresaDTO> convert(List<Adresa> adrsae){
	        List<AdresaDTO> dto = new ArrayList<>();

	        for(Adresa a:adrsae) {
	            dto.add(convert(a));
	        }

	        return dto;
	    }
	
	

	
	
	
}
