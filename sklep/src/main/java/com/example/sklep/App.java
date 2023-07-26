package com.example.sklep;


import com.example.sklep.controlers.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        //create db

        SessionFactory sessionF = DbAccess.getSessionFactory();
        Session session = sessionF.openSession();
        session.close();

        //load main scene
        scene = new Scene(loadFXML("main-view"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Sklep");



    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(  fxml + ".fxml"));
        fxmlLoader.setController(Main.getInstance());
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }
}