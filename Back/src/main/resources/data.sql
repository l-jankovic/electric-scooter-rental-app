INSERT INTO korisnik (id, email, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, email, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, email, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
              
              
              
              INSERT INTO `trotineti`.`adresa` (`broj`, `ulica`) VALUES ('112', 'Smrtna');
INSERT INTO `trotineti`.`adresa` (`broj`, `ulica`) VALUES ('10', 'Grobljanska');
INSERT INTO `trotineti`.`adresa` (`broj`, `ulica`) VALUES ('20', 'Vracarska 10');
INSERT INTO `trotineti`.`adresa` (`broj`, `ulica`) VALUES ('30', 'Vorlokova');
INSERT INTO `trotineti`.`trotinet` (`iznajmljen`, `max_brzina`, `nivo_bat`, `sifra`, `adresa_id`) VALUES (false, 25, 20, '3213123', 1);
INSERT INTO `trotineti`.`trotinet` (`iznajmljen`, `max_brzina`, `nivo_bat`, `sifra`, `adresa_id`) VALUES (true, 30, 80, '231321', 2);
INSERT INTO `trotineti`.`trotinet` (`iznajmljen`, `max_brzina`, `nivo_bat`, `sifra`, `adresa_id`) VALUES (false,15, 40, '312321', 2);
INSERT INTO `trotineti`.`trotinet` (`iznajmljen`, `max_brzina`, `nivo_bat`, `sifra`, `adresa_id`) VALUES (false,15, 7, '1010', 4);
INSERT INTO `trotineti`.`trotinet` (`iznajmljen`, `max_brzina`, `nivo_bat`, `sifra`, `adresa_id`) VALUES (false,20, 50, '312321442142', 3);

INSERT INTO `trotineti`.`rezervacija` (`e_mail`, `vreme_iznajmljivanja`, `trotinet_id`) VALUES ('petar@maildrop.cc', '2024-03-25 20:30', '2');
INSERT INTO `trotineti`.`rezervacija` (`e_mail`, `vreme_iznajmljivanja`, `vreme_vracanja`, `trotinet_id`) VALUES ('tamara@maildrop.cc', '2024-03-25 20:30', '2024-03-26 18:30', '1');

