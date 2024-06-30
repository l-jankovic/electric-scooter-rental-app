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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Trotinet {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @EqualsAndHashCode.Include
     @Setter(value = AccessLevel.NONE)
	  private Long id;
	 
	 @Column(nullable = false,unique = true)
	 private String sifra;
	 @Column
	 private int maxBrzina;
	 @Column(nullable = false)
	 private boolean iznajmljen;
	 
	 
	 @Column
	 private int nivoBat;
	 
	 @ManyToOne
	 private Adresa adresa;
	 
	 @OneToMany(mappedBy="trotinet",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 List<Rezervacija> rezervacije= new ArrayList<>();

}
