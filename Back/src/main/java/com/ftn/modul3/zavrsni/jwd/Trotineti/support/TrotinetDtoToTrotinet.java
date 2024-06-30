package com.ftn.modul3.zavrsni.jwd.Trotineti.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Trotinet;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.AdresaService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.TrotinetService;
import com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto.TrotinetDTO;
@Component
public class TrotinetDtoToTrotinet implements Converter<TrotinetDTO, Trotinet>{

	
	@Autowired
	private AdresaService adresaService;
	
	@Autowired
	private  TrotinetService trotinetService;
	@Override
	public Trotinet convert(TrotinetDTO source) {
		
		Trotinet t;
		
		if(source.getId()==null) {
			t= new Trotinet();
		}else {
			t=trotinetService.findOne(source.getId());
			
		}
		
		if(t!=null) {
			t.setSifra(source.getSifra());
			t.setIznajmljen(source.isIznajmljen());
			t.setMaxBrzina(source.getMaxBrzina());
			t.setNivoBat(source.getNivoBat());
			t.setAdresa(adresaService.findOne(source.getAdresaId()));
			
			
		
			
			
		}
		return t;
	
	}

}
