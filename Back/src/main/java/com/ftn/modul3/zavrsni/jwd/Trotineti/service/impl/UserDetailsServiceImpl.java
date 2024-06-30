package com.ftn.modul3.zavrsni.jwd.Trotineti.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Trotineti.model.Korisnik;
import com.ftn.modul3.zavrsni.jwd.Trotineti.service.KorisnikService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
	
  @Autowired
  private KorisnikService korisnikService;

  /* Zelimo da predstavimo korisnika preko UserDetails klase - nacina
  *  na koji Spring boot predstavlja korisnika. Ucitamo na osnovu korisnickog imena
  *  korisnika iz nase mysql baze i u okviru UserDetails namapiramo njegove podatke
  *  - kredencijale i rolu kroz GrantedAuthorities. */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Korisnik korisnik = korisnikService.findbyKorisnickoIme(username).orElse(null);

    if (korisnik == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // korisnik moze imati vise od jedne uloge te za svaku ulogu mogu biti definisana prava
        String role = "ROLE_" + korisnik.getUloga().toString();
        //String role = korisnik.getUloga().toString();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
                korisnik.getKorisnickoIme().trim(),
                korisnik.getLozinka().trim(),
                grantedAuthorities);
    }
  }
}
