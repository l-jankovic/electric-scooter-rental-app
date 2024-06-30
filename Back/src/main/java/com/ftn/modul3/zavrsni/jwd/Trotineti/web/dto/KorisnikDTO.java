package com.ftn.modul3.zavrsni.jwd.Trotineti.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@Data
public class KorisnikDTO {

    @Positive(message = "Id mora biti pozitivan broj.")
    private Long id;

    //@NotBlank
    private String korisnickoIme;

    //@NotEmpty
    //@Email
    private String email;

    //@Size(min=3, max=50)
    private String ime;

    //@Size(min=3, max=50)
    private String prezime;





}
