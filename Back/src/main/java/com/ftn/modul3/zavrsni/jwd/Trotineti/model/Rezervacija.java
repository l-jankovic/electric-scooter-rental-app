package com.ftn.modul3.zavrsni.jwd.Trotineti.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Rezervacija {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @EqualsAndHashCode.Include
     @Setter(value = AccessLevel.NONE)
	  private Long id;
	 
	 @Column(nullable  = false)
	 private LocalDateTime vremeIznajmljivanja;
	 
	 private LocalDateTime vremeVracanja;
	 
	 @Column(nullable = false)
	 private String eMail;
	 
	 @ManyToOne
	 private Trotinet trotinet;

}
