package org.arbol.arbolbinario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class ArbolBinarioApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ArbolBinarioApplication.class.getResource("arbolbinario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenido");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}