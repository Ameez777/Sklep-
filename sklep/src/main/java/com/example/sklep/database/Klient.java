package com.example.sklep.database;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table

public class Klient {

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String imie;

    @Basic(optional = false)
    @Column(nullable = false)
    private String nazwisko;

    @Basic(optional = false)
    @Column(nullable = false, length = 6)
    private String kod;

    @Basic(optional = false)
    @Column(nullable = false)
    private String ulica;

    @Basic(optional = false)
    @Column(nullable = false)
    private String telefon;

    @Basic(optional = false)
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "klient")
    private Set<Zamowienie>  zamowienia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Set<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }


}
