package com.example.sklep.controlers;

import com.example.sklep.DbAccess;
import com.example.sklep.database.Klient;
import com.example.sklep.database.Produkt;
import com.example.sklep.database.Zamowienie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabProdukty implements Initializable {


    @FXML
    private TableView<Produkt> pTableFx;

    @FXML
    private Spinner<Double> pCenaFx;

    @FXML
    private TableColumn pNazwaProduktuKolFx;

    @FXML
    private Text pError;

    @FXML
    private TextField pNazwaProduktuFx;


    @FXML
    private TableColumn pCenaKolFx;
    @FXML
    private Main Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pCenaFx.setEditable(true);

        this.Main = Main.getInstance();
        pNazwaProduktuKolFx.setCellValueFactory(new PropertyValueFactory<Produkt, String>("nazwaProduktu"));
        pCenaKolFx.setCellValueFactory(new PropertyValueFactory<Produkt, Double>("cena"));
        pTableFx.setItems(DbAccess.getInstance().getProduktTable());
    }
    @FXML
    public void submitProdukt() throws IOException {
        if (pNazwaProduktuFx.getText().isEmpty()
                || pCenaFx.getValue().doubleValue() <= 0.

        ) {
            pError.setText("Brakuje danych");
            pError.setVisible(true);
        } else {
            Produkt newProdukt = new Produkt();
            newProdukt.setNazwaProduktu(pNazwaProduktuFx.getText());
            newProdukt.setCena(pCenaFx.getValue());


            try {
                DbAccess.getInstance().saveOrUpdateEntity(newProdukt);
                pError.setText("Dodano produkt");
                pError.setVisible(true);
                pTableFx.setItems(DbAccess.getInstance().getProduktTable());
            } catch (Exception e) {
                pError.setText("Blad w dodawaniu");
                pError.setVisible(true);
                e.printStackTrace();
            }

        }
    }
}
