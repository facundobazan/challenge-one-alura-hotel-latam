package ar.com.facundobazan.hotel_alura.controllers;

import ar.com.facundobazan.hotel_alura.entities.records.RegistroHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroReserva;
import ar.com.facundobazan.hotel_alura.services.HuespedServicio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private RegistroReserva reservaSeleccionada;
    private RegistroHuesped huespedSeleccionado;

    public BuscarHuespedController(RegistroReserva reserva) {

        this.reservaSeleccionada = reserva;
    }

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
        } else {

            HuespedServicio huespedServicio = new HuespedServicio();
            huespedSeleccionado = huespedServicio.buscarPorDocumento(txtDocumento.getText());
            if (huespedServicio == null) {

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "No se encontraron resultados.\n¿Desea continuar con el registro?",
                        "Resultado de la busqueda",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                switch (opcion) {
                    case JOptionPane.YES_OPTION -> continuarReserva();
                    case JOptionPane.NO_OPTION -> txtDocumento.requestFocus();
                    case JOptionPane.CANCEL_OPTION -> cancelarReserva();
                }
            }
        }
    }

    @FXML
    private void cancelarReserva() {

        JOptionPane.showConfirmDialog(null, "Reserva cancelada");
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void continuarReserva() {
    }
}
