package com.ftn.modul3.zavrsni.jwd.Trotineti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;



@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Adresa {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @EqualsAndHashCode.Include
     @Setter(value = AccessLevel.NONE)
	  private Long id;
	 
	 
	 @Column(nullable = false)
	 private String ulica;
	 
	 @Column(nullable = false)
	 private int broj;
	 
	 
	 @OneToMany(mappedBy = "adresa",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 private List<Trotinet> trotineti = new ArrayList<Trotinet>();
}
