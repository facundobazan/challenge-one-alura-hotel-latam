package ar.com.facundobazan.hotel_alura.views;

import ar.com.facundobazan.hotel_alura.entities.records.RegistroHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroReserva;
import ar.com.facundobazan.hotel_alura.services.HuespedServicio;
import ar.com.facundobazan.hotel_alura.services.ReservaServicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@SuppressWarnings("serial")
public class BusquedaView extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modeloReserva;
    private ArrayList<RegistroReserva> reservas = new ArrayList<>();
    private ArrayList<RegistroHuesped> huespedes = new ArrayList<>();
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    private Tabla tabSeleccionado;
    private JTabbedPane panel;
    private String filtroBusqueda;
    int xMouse, yMouse;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BusquedaView frame = new BusquedaView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BusquedaView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(BusquedaView.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        this.panel = new JTabbedPane(JTabbedPane.TOP);
        this.panel.setBackground(new Color(12, 138, 199));
        this.panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        this.panel.setBounds(20, 169, 865, 328);
        contentPane.add(this.panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloReserva = (DefaultTableModel) tbReservas.getModel();
        modeloReserva.addColumn("Numero de Reserva");
        modeloReserva.addColumn("Fecha Check In");
        modeloReserva.addColumn("Fecha Check Out");
        modeloReserva.addColumn("Valor");
        modeloReserva.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(BusquedaView.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(BusquedaView.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(BusquedaView.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuarioView usuario = new MenuUsuarioView();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuarioView usuario = new MenuUsuarioView();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                filtroBusqueda = txtBuscar.getText();
                poblarTabla();
                txtBuscar.setText("");
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);
        btnEditar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println(tbReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
        btnEliminar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                borrarRegistro();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        panel.addChangeListener(c -> {

            txtBuscar.setText("");
            poblarTabla();
        });

        this.filtroBusqueda = txtBuscar.getText();
        System.out.println(txtBuscar.getText());
        System.out.println(txtBuscar);
        poblarTabla();
    }

    private void borrarRegistro() {

        switch (tabSeleccionado) {

            case HUESPEDES -> borrarRegistro(tbHuespedes);
            case RESERVAS -> borrarRegistro(tbReservas);
        }
    }

    private void borrarRegistro(JTable tabla) {

        if (tabla.getSelectedRow() != -1) {
            long id;

            switch (tabSeleccionado) {
                case RESERVAS -> {

                    id = Long.valueOf(tabla.getValueAt(tabla.getSelectedRow(),0).toString());
                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            String.format("Estas por borrar la reserva nro=%s.\n¿Deseas continuar?", id),
                            "Borrar reserva",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (opcion == 0) {

                        try {

                            ReservaServicio reservaServicio = new ReservaServicio();
                            reservaServicio.borrarReservaAsignada(id);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Operación cancelada.",
                                    "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE);

                            filtroBusqueda = "";
                            poblarTabla();
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Operación cancelada.",
                                "Aviso",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            //((DefaultTableModel) tabla.getModel()).removeRow(row);
        }
    }

    private void borrarTabla(JTable tabla) {

        ((DefaultTableModel) tabla.getModel()).getDataVector().clear();
    }

    private void poblarTabla(JTable tabla) {

        switch (tabSeleccionado) {

            case RESERVAS -> poblarTablaReservas();
            case HUESPEDES -> poblarTablaHuespedes();
        }
    }

    private void poblarTabla() {

        tabSeleccionado = Tabla.values()[this.panel.getSelectedIndex()];
        switch (tabSeleccionado) {

            case RESERVAS -> poblarTablaReservas();
            case HUESPEDES -> poblarTablaHuespedes();
        }
    }

    private void poblarTablaHuespedes() {

        borrarTabla(tbHuespedes);
        ArrayList<RegistroHuesped> lista = new ArrayList<>();
        if (filtroBusqueda.isEmpty()) {

            poblarListaHuespedes();
            lista = huespedes;

        } else {

            lista = filtrarListaHuespedes();
        }

        for (RegistroHuesped h : lista)
            ((DefaultTableModel) tbHuespedes.getModel()).addRow(new String[]{
                    h.id().toString(),
                    h.nombre(),
                    h.apellido(),
                    h.fechaNacimiento().toString(),
                    h.nacionalidad(),
                    h.telefono(),
                    "NO DISPONIBLE"});
    }

    private ArrayList<RegistroReserva> filtrarListaReservas() {

        return (ArrayList<RegistroReserva>) reservas.stream()
                .filter(r -> r.id().toString().contains(filtroBusqueda))
                .collect(Collectors.toList());
    }

    private ArrayList<RegistroHuesped> filtrarListaHuespedes() {

        return (ArrayList<RegistroHuesped>) huespedes.stream()
                .filter(h -> h.apellido().contains(filtroBusqueda) || h.nombre().contains(filtroBusqueda))
                .collect(Collectors.toList());
    }

    private void poblarTablaReservas() {

        borrarTabla(tbReservas);
        ArrayList<RegistroReserva> lista = new ArrayList<>();
        if (filtroBusqueda.isEmpty()) {

            poblarListaReservas();
            lista = reservas;

        } else {

            lista = filtrarListaReservas();
        }

        for (RegistroReserva r : lista)
            ((DefaultTableModel) tbReservas.getModel()).addRow(new String[]{
                    r.id().toString(),
                    r.fechaEntrada().toString(),
                    r.fechaSalida().toString(),
                    String.format("$ %.2f", r.valor()),
                    r.formaPago().name()});
    }

    private void poblarListaReservas() {

        ReservaServicio reservaServicio = new ReservaServicio();
        this.reservas = reservaServicio.obtenerReservasAsignadas();
    }

    private void poblarListaHuespedes() {

        HuespedServicio huespedServicio = new HuespedServicio();
        this.huespedes = huespedServicio.obtenerHuespedes();
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}

enum Tabla {
    RESERVAS,
    HUESPEDES
}
