package com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Data
public class RezervacijaDTO {
	
	private Long id;
	
	
	
	private LocalDateTime datumIznajmiljvanja;
	
	private LocalDateTime datumVracanja;
	
	
	private String eMail;
	
	private Long trotinetId;
	
	private String trotSfira;

}
