package ar.com.facundobazan.hotel_alura.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuscarHuesped extends Application {

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("./views/BuscarHuesped.fxml"));
        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Buscar Huesped");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
