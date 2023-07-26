package com.example.sklep.controlers;

import com.example.sklep.App;
import com.example.sklep.DbAccess;
import com.example.sklep.database.Klient;
import com.example.sklep.database.Produkt;
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

public class TabZamowienia implements Initializable {

    @FXML
    private TableView<Zamowienie> zTableFx;

    @FXML
    private TableColumn zKlientImieKolFx;

    @FXML
    private Text zError;

    @FXML
    private TableColumn zEditKolFx;

    @FXML
    private TableColumn zUsunKolFx;

    @FXML
    private TableColumn zIdKolFx;

    @FXML
    private TableColumn zWartoscKolFx;

    @FXML
    private ChoiceBox zKlientFx;

    @FXML
    private Main Main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.Main = Main.getInstance();
        zEditKolFx.setCellValueFactory(new PropertyValueFactory<Zamowienie,Integer>("id"));
        zKlientImieKolFx.setCellValueFactory(new PropertyValueFactory<Zamowienie,Klient>("klient"));
        zIdKolFx.setCellValueFactory(new PropertyValueFactory<Zamowienie,Integer >("id"));
        zWartoscKolFx.setCellValueFactory(new PropertyValueFactory<Zamowienie,Double> ("wartosc"));


        zKlientFx.setItems(DbAccess.getInstance().getKlientTable());
        zKlientFx.getSelectionModel().selectFirst();

        zTableFx.setItems(DbAccess.getInstance().getZamowienieTable());

        Callback<TableColumn<Klient, Integer>, TableCell<Klient, Integer>> cellEditFactory =
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Klient, Integer> column) {
                        final TableCell<Klient, Integer> cell = new TableCell<Klient, Integer>() {
                            final Button btn = new Button(column.getText());

                            @Override
                            public void updateItem(Integer Id, boolean empty) {
                                super.updateItem(Id, empty);
                                if (empty){
                                    setGraphic(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        try {
                                            addTab(Id);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                    setGraphic(btn);
                                }
                                setText(null);
                            }
                        };
                        return cell;
                    }
                };
        zIdKolFx.setCellFactory(cellEditFactory);


        //handle delete button
        zUsunKolFx.setCellValueFactory(new PropertyValueFactory<Zamowienie, Integer>("id"));
        Callback<TableColumn<Zamowienie, String>, TableCell<Zamowienie, Integer>> cellDeleteFactory =
                new Callback<TableColumn<Zamowienie, String>, TableCell<Zamowienie, Integer>>() {
                    @Override
                    public TableCell call(final TableColumn<Zamowienie, String> column) {
                        final TableCell<Zamowienie, Integer> cell = new TableCell<Zamowienie, Integer>() {
                            final Button btn = new Button(column.getText());
                            @Override
                            public void updateItem(Integer ftID, boolean empty) {
                                super.updateItem(ftID, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        DbAccess.getInstance().deleteZamowienieById(ftID);
                                        zTableFx.setItems(DbAccess.getInstance().getZamowienieTable());
                                    });
                                    setGraphic(btn);
                                }
                                setText(null);
                            }
                        };
                        return cell;
                    }
                };
        zUsunKolFx.setCellFactory(cellDeleteFactory);
}

    @FXML
    private void addTab(Integer id) throws IOException {
        Tab singleTab = new Tab("Zamowienie id: " + id);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("zamowienie-view.fxml"));
        fxmlLoader.setController(new TabZamowienie(id));
        AnchorPane anchorPane = fxmlLoader.load();
        singleTab.setContent(anchorPane);
        Main.getInstance().addTab(singleTab);
    }

    @FXML
    public void submitZamowienia() throws IOException {

        if (zKlientFx.getValue() == null) {
            zError.setText("Brakuje danych");
            zError.setVisible(true);
        } else {
            Zamowienie newZamowienie = new Zamowienie();
            newZamowienie.setKlient((Klient) zKlientFx.getValue());
            newZamowienie.setWartosc(0.);
            try {
                DbAccess.getInstance().saveOrUpdateEntity(newZamowienie);
                zError.setText("Dodano zamowienie");
                zError.setVisible(true);
                zTableFx.setItems(DbAccess.getInstance().getZamowienieTable());
            } catch (Exception e) {
                zError.setText("Blad w dodawaniu");
                zError.setVisible(true);
                e.printStackTrace();
            }
        }
    }
}
