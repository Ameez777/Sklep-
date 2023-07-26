package com.example.sklep.controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class Main implements Initializable {

    @FXML
    private Tab produktyTabFx;
    @FXML
    private Tab zamowieniaTabFx;
    @FXML
    private Tab klienciTabFx;

    @FXML
    private final static Main instance = new Main();

    @FXML
    private TabPane MainTabPane;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        klienciTabFx.setClosable(false);
        zamowieniaTabFx.setClosable(false);
        produktyTabFx.setClosable(false);
    }
    public void addTab(Tab tab) {
        MainTabPane.getTabs().add(tab);
        MainTabPane.getSelectionModel().select(tab);
    }
}
