package ar.com.facundobazan.hotel_alura.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarHuespedController implements Initializable {

    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnContinuar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtApellido.setDisable(true);
        txtNombre.setDisable(true);
    }

    @FXML
    private void buscarHuesped() {

        if (!txtDocumento.getText().matches("^\\d{6,12}$")) {
            JOptionPane.showMessageDialog(null, "Ingresa un documento válido.\n" +
                    "Solo números (6 a 12 dígitos).");
            txtDocumento.clear();
            txtDocumento.requestFocus();
        }
    }

    @FXML
    private void cancelarReserva() {
    }

    @FXML
    private void ContinuarReserva() {
    }
}
