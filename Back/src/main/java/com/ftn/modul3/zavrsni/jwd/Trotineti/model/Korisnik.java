package com.ftn.modul3.zavrsni.jwd.Trotineti.model;



import javax.persistence.*;

import com.ftn.modul3.zavrsni.jwd.Trotineti.enumeration.KorisnickaUloga;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
  	@Setter(value = AccessLevel.NONE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String korisnickoIme;

    @Column( unique = true, nullable = false)
    private String email;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(nullable = false)
    private String lozinka;

    @Enumerated(EnumType.STRING)
    private KorisnickaUloga uloga;




}
