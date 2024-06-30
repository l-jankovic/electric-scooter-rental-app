package com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class TrotinetDTO {
	
	private Long id;
	
	@Size(max = 20)
	private String sifra;
	@Min(0)
	@Max(100)
	private int nivoBat;
	
	private int maxBrzina ;
	
	private boolean iznajmljen;
	
	private Long adresaId;
	
	private String ulica;
	
	private int brojAdresa;

}
