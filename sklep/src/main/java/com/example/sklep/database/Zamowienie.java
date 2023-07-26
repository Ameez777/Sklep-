package com.example.sklep.database;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Zamowienie {

    @Override
    public String toString() {
        return  id + " " + wartosc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable= false)
    private Klient klient;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "zamowienie_id"),
            inverseJoinColumns = @JoinColumn(name = "produkt_id"))
    private List<Produkt> produkty;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double wartosc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public List<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(List<Produkt> produkty) {
        this.produkty = produkty;
    }
    public void addProdukt(Produkt produkt){
        this.produkty.add(produkt);
    }

    public Double getWartosc() {
        return wartosc;
    }

    public void setWartosc(Double wartosc) {
        this.wartosc = wartosc;
    }



}
