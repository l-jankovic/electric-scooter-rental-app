package com.ftn.modul3.zavrsni.jwd.Trotineti.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.TrotinetDTO;

@Component
public class TrotinetToTrotinetDto implements Converter<Trotinet, TrotinetDTO> {


	

	@Override
	public TrotinetDTO convert(Trotinet source) {
		
		TrotinetDTO dto = new TrotinetDTO();
		
		dto.setId(source.getId());
		dto.setSifra(source.getSifra());
		dto.setNivoBat(source.getNivoBat());
		dto.setMaxBrzina(source.getMaxBrzina());
		dto.setIznajmljen(source.isIznajmljen());
		dto.setAdresaId(source.getId());
		dto.setUlica(source.getAdresa().getUlica());
		dto.setBrojAdresa(source.getAdresa().getBroj());
		
		return dto;
	}
	
	 public List<TrotinetDTO> convert(List<Trotinet> trotovi){
	        List<TrotinetDTO> dto = new ArrayList<>();

	        for(Trotinet t: trotovi) {
	            dto.add(convert(t));
	        }

	        return dto;
	    }




}
