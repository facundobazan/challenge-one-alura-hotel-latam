package ar.com.facundobazan.hotel_alura.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuscarHuesped extends Application {

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = FXMLLoader.load(BuscarHuesped.class.getResource("./views/buscar-huesped.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        stage.setTitle("Buscar Huesped");
        stage.setResizable(false);
        stage.isAlwaysOnTop();
        stage.setScene(scene);
        stage.show();
    }
}
