package com.example.sklep.controlers;

import com.example.sklep.App;
import com.example.sklep.DbAccess;
import com.example.sklep.database.Klient;
import com.example.sklep.database.Zamowienie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabKlienci implements Initializable {

    @FXML
    private TableView<Klient> kTableFx;

    @FXML
    private TextField kImieFx;
    @FXML
    private TextField kNazwiskoFx;
    @FXML
    private TextField kKodFx;
    @FXML
    private TextField kTelefonFx;
    @FXML
    private TextField kEmailFx;
    @FXML
    private TextField kUlicaFx;

    @FXML
    private Text kError;
    @FXML
    private TableColumn kImieKolFx;
    @FXML
    private TableColumn kNazwiskoKolFx;
    @FXML
    private TableColumn kKodKolFx;
    @FXML
    private TableColumn kUlicaKolFx;
    @FXML
    private TableColumn kTelefonKolFx;
    @FXML
    private TableColumn kEmailKolFx;
    @FXML
    private Main Main;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Main = Main.getInstance();
        kImieKolFx.setCellValueFactory(new PropertyValueFactory<Klient, String>("imie"));
        kNazwiskoKolFx.setCellValueFactory(new PropertyValueFactory<Klient, String>("nazwisko"));
        kKodKolFx.setCellValueFactory(new PropertyValueFactory<Klient, String>("kod"));
        kUlicaKolFx.setCellValueFactory(new PropertyValueFactory<Klient, String>("ulica"));
        kTelefonKolFx.setCellValueFactory(new PropertyValueFactory<Klient, String>("telefon"));
        kEmailKolFx.setCellValueFactory(new PropertyValueFactory<Klient, String>("email"));

        kTableFx.setItems(DbAccess.getInstance().getKlientTable());
    }
    @FXML
    public void submitKlient() throws IOException {
        if (kImieFx.getText().isEmpty()
                || kNazwiskoFx.getText().isEmpty()
                || kKodFx.getText().isEmpty()
                || kUlicaFx.getText().isEmpty() ||
                kTelefonFx.getText().isEmpty() ||
                kEmailFx.getText().isEmpty()
                ) {
            kError.setText("Brakuje danych");
            kError.setVisible(true);
        } else {
            Klient newKlient = new Klient();
            newKlient.setEmail(kEmailFx.getText());
            newKlient.setImie(kImieFx.getText());
            newKlient.setNazwisko(kNazwiskoFx.getText());
            newKlient.setKod(kKodFx.getText());
            newKlient.setUlica(kUlicaFx.getText());
            newKlient.setTelefon(kTelefonFx.getText());

            try {
                DbAccess.getInstance().saveOrUpdateEntity(newKlient);
                kError.setText("Dodano klienta");
                kError.setVisible(true);
                kTableFx.setItems(DbAccess.getInstance().getKlientTable());
            } catch (Exception e) {
                kError.setText("Blad w dodawaniu");
                kError.setVisible(true);
                e.printStackTrace();
            }

        }

    }






}
