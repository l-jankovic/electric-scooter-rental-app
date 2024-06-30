package com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto;

import javax.validation.constraints.NotBlank;

public class KorisnikPromenaLozinkeDto {

    //@NotBlank(message = "Korisnicko ime nije zadato.")
    private String korisnickoIme;

    //@NotBlank(message = "Stara lozinka nije zadata.")
    private String staraLozinka;

    //@NotBlank(message = "Lozinka nije zadata.")
    private String lozinka;

    //@NotBlank(message = "Ponovljena lozinka nije zadata.")
    private String ponovljenaLozinka;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPonovljenaLozinka() {
        return ponovljenaLozinka;
    }

    public void setPonovljenaLozinka(String ponovljenaLozinka) {
        this.ponovljenaLozinka = ponovljenaLozinka;
    }
}
