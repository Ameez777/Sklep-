package com.example.sklep.database;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String nazwaProduktu;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double cena;

    @Override
    public String toString() {
        return nazwaProduktu ;
    }

    @ManyToMany(mappedBy = "produkty")
    private List<Zamowienie> zamowienia;


    public Integer getId() {
        return id;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public Double getCena() {
        return cena;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

}
