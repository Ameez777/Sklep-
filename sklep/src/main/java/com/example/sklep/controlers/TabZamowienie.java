package com.example.sklep.controlers;

import com.example.sklep.DbAccess;
import com.example.sklep.database.Klient;
import com.example.sklep.database.Produkt;
import com.example.sklep.database.Zamowienie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;


public class TabZamowienie implements Initializable {

    @FXML
    private Main Main;

    @FXML
    private ChoiceBox zeProduktFx;

    @FXML
    private Text zeError;
    @FXML
    private Text zeWartZam;

    @FXML
    private Spinner<Integer> zeIloscFx;

    @FXML
    private TableView<Produkt> zeTableFx;

    @FXML
    private TableColumn zeNazwaProduktuKolFx;
    @FXML
    private TableColumn zeCenaKolFx;



    private Integer zamowienieIndex;

    private ObservableList<Produkt> produktyZam =  FXCollections.observableArrayList();

    public TabZamowienie(Integer zamowienieIndex) {
        this.zamowienieIndex = zamowienieIndex;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Main = Main.getInstance();
        calculateSum();

        zeWartZam.setText("Wartosc Zamowienia: "+ DbAccess.getInstance().getZamowienieById(zamowienieIndex).getWartosc());


        zeNazwaProduktuKolFx.setCellValueFactory(new PropertyValueFactory<Produkt, String>("nazwaProduktu"));
        zeCenaKolFx.setCellValueFactory(new PropertyValueFactory<Produkt, Double>("cena"));

        produktyZam.setAll(DbAccess.getInstance().getZamowienieById(zamowienieIndex).getProdukty());

        zeProduktFx.setItems(DbAccess.getInstance().getProduktTable());
        zeProduktFx.getSelectionModel().selectFirst();
        zeTableFx.setItems(produktyZam);



    }


    @FXML
    public void submitEditZamowienie() throws IOException {

        if (zeProduktFx.getValue() == null || zeIloscFx.getValue().intValue() < 1 ) {
            zeError.setText("Brakuje danych");
            zeError.setVisible(true);
        } else {
            Zamowienie editZamowienie = DbAccess.getInstance().getZamowienieById(zamowienieIndex);



            for(int i = 0;i< zeIloscFx.getValue().intValue();i++){
               editZamowienie.addProdukt((Produkt) zeProduktFx.getValue());
            }

            try {
                DbAccess.getInstance().saveOrUpdateEntity(editZamowienie);

                calculateSum();

                produktyZam.setAll(DbAccess.getInstance().getZamowienieById(zamowienieIndex).getProdukty());
                zeWartZam.setText("Wartosc Zamowienia: "+ DbAccess.getInstance().getZamowienieById(zamowienieIndex).getWartosc());
                zeError.setText("Dodano produkt do zamowienia");
                zeError.setVisible(true);
            } catch (Exception e) {
                zeError.setText("Blad w dodawaniu");
                zeError.setVisible(true);
                e.printStackTrace();
            }
        }
    }

    private void calculateSum(){

        Zamowienie editZamowienie = DbAccess.getInstance().getZamowienieById(zamowienieIndex);
        List<Produkt> produkty = DbAccess.getInstance().getZamowienieById(zamowienieIndex).getProdukty();
        Double suma = 0.;
        for(Produkt pojedynczy  : produkty ){
            suma += pojedynczy.getCena();
        }
        editZamowienie.setWartosc(round(suma,2));

        DbAccess.getInstance().saveOrUpdateEntity(editZamowienie);
        DbAccess.getInstance().getZamowienieTable();
    }
    private  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
