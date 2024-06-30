package com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Data
public class VracanjeTrotinetaDTO {
	
	
	private Long adresaId;
	
	private int stanjeBaterije;
	
	@NotNull
	@Email
	private String email;

}
