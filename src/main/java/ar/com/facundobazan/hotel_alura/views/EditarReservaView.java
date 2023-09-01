/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ar.com.facundobazan.hotel_alura.views;

import ar.com.facundobazan.hotel_alura.entities.FormaPago;
import ar.com.facundobazan.hotel_alura.entities.records.RecEditarReserva;
import ar.com.facundobazan.hotel_alura.services.ReservaServicio;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

/**
 * @author facundo
 */
public class EditarReservaView extends JFrame {

    /**
     * Creates new form EditarReservaView
     */
    public EditarReservaView(RecEditarReserva r) {
        this.reserva = r;
        initComponents();
        cargarCampos();
    }

    private void cargarCampos() {
        txtFechaInicio.setValue(reserva.fechaEntrada().toString());
        txtFechaFin.setValue(reserva.fechaSalida().toString());
        cboFormaPago.setSelectedItem(reserva.formaPago());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            formatter = new MaskFormatter("####-##-##");
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        lblFechaInicial = new JLabel();
        txtFechaInicio = new JFormattedTextField(formatter);
        lblFechaFinal = new JLabel();
        txtFechaFin = new JFormattedTextField(formatter);
        lblFormaPago = new JLabel();
        cboFormaPago = new JComboBox<>();
        btnConfirmar = new JButton();
        btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar reserva");
        setPreferredSize(new Dimension(250, 300));
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        lblFechaInicial.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lblFechaInicial.setText("Fecha de ingreso");

        txtFechaInicio.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N

        lblFechaFinal.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lblFechaFinal.setText("Fecha de egreso");

        txtFechaFin.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N

        lblFormaPago.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lblFormaPago.setText("Metodo de pago");

        cboFormaPago.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        cboFormaPago.setModel(new DefaultComboBoxModel<>(FormaPago.values()));

        btnConfirmar.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setPreferredSize(new java.awt.Dimension(105, 30));
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int opcion = JOptionPane.showConfirmDialog(
                        btnConfirmar.getParent(),
                        "Estas por modificar el registro seleccionado.\n¿Deseas continuar?",
                        "Advertencia",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (opcion == 0) {
                    guardarCambios();
                    dispose();
                } else dispose();
            }
        });

        btnCancelar.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        btnCancelar.setLabel("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(105, 30));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFechaInicio)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblFechaInicial)
                                                        .addComponent(lblFechaFinal)
                                                        .addComponent(lblFormaPago))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtFechaFin)
                                        .addComponent(cboFormaPago, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblFechaInicial)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFechaFinal)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFormaPago)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboFormaPago, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        lblFechaInicial.getAccessibleContext().setAccessibleName("Fecha de ingreso");
        txtFechaInicio.getAccessibleContext().setAccessibleName("Fecha de ingreso");
        txtFechaFin.getAccessibleContext().setAccessibleName("Fecha de egreso");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarCambios() {

        try {

            new ReservaServicio().modificarReserva(new RecEditarReserva(
                    reserva.id(),
                    LocalDate.parse(txtFechaInicio.getValue().toString()),
                    LocalDate.parse(txtFechaFin.getValue().toString()),
                    FormaPago.valueOf(cboFormaPago.getSelectedItem().toString())
            ));

            JOptionPane.showMessageDialog(
                    null,
                    "Los cambios se efectuaron correctamente.",
                    "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Ocurrio un error en la carga de los datos.\n" +
                            "Verifica los datos ingresados",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarReservaView(reserva).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JComboBox<FormaPago> cboFormaPago;
    private JLabel lblFechaFinal;
    private JLabel lblFechaInicial;
    private JLabel lblFormaPago;
    private JFormattedTextField txtFechaFin;
    private JFormattedTextField txtFechaInicio;
    private static RecEditarReserva reserva;
    private MaskFormatter formatter;
    // End of variables declaration//GEN-END:variables
}
