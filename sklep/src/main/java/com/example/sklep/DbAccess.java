package com.example.sklep;

import com.example.sklep.database.Klient;
import com.example.sklep.database.Produkt;
import com.example.sklep.database.Zamowienie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class DbAccess {

    private static SessionFactory sessionFactory;
    private final static DbAccess instance = new DbAccess();


    private ObservableList<Klient> klientTable = FXCollections.observableArrayList();

    private ObservableList<Produkt> produktTable = FXCollections.observableArrayList();

    private ObservableList<Zamowienie> zamowienieTable = FXCollections.observableArrayList();

    private DbAccess() {
    }

    public static synchronized SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try{
                Class.forName("org.sqlite.JDBC");
            }catch (ClassNotFoundException e){

            }
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static DbAccess getInstance(){
        return instance;
    }

    public ObservableList<Klient> getKlientTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Klient> allKlient = session.createQuery("FROM Klient ").list();
        session.getTransaction().commit();
        session.close();
        klientTable.setAll(allKlient);
        return klientTable;
    }
    public ObservableList<Produkt> getProduktTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Produkt> allProdukt = session.createQuery("FROM Produkt ").list();
        session.getTransaction().commit();
        session.close();
        produktTable.setAll(allProdukt);
        return produktTable;
    }
    public ObservableList<Zamowienie> getZamowienieTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Zamowienie> allZamowienie = session.createQuery("FROM Zamowienie ").list();
        session.getTransaction().commit();
        session.close();
        zamowienieTable.setAll(allZamowienie);
        return zamowienieTable;
    }




    public void saveOrUpdateEntity(Object entityObject) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(entityObject);
        session.getTransaction().commit();
        session.close();

    }

    public void deleteZamowienieById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Zamowienie entity = session.find(Zamowienie.class, id);
        session.remove(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProduktbyIndexFromZamowienieById(Integer ZamId,Integer ProdIndex) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Zamowienie entity = session.find(Zamowienie.class, ZamId);
        entity.getProdukty().remove(ProdIndex);
        saveOrUpdateEntity(entity);
        session.getTransaction().commit();
        session.close();
    }


    public Zamowienie getZamowienieById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Zamowienie entity = session.find(Zamowienie.class, id);
        session.getTransaction().commit();
        session.close();
        return entity;
    }


}