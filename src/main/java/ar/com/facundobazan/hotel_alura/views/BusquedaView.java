package ar.com.facundobazan.hotel_alura.views;

import ar.com.facundobazan.hotel_alura.entities.records.RecEditarHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecEditarReserva;
import ar.com.facundobazan.hotel_alura.entities.records.RecHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecReserva;
import ar.com.facundobazan.hotel_alura.services.HuespedServicio;
import ar.com.facundobazan.hotel_alura.services.ReservaServicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class BusquedaView extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modeloReserva;
    private ArrayList<RecReserva> reservas = new ArrayList<>();
    private ArrayList<RecHuesped> huespedes = new ArrayList<>();
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
        lblNewLabel_4.setBounds(331, 62, 318, 42);
        contentPane.add(lblNewLabel_4);

        this.panel = new JTabbedPane(JTabbedPane.TOP);
        this.panel.setBackground(new Color(12, 138, 199));
        this.panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        this.panel.setBounds(20, 169, 865, 328);
        contentPane.add(this.panel);

        /*DefaultTableModel modelo = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto hace que todas las celdas sean no editables
            }
        };*/

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
        tbReservas.setDefaultEditor(Object.class, null);
        panel.addTab("Reservas", new ImageIcon(Objects.requireNonNull(BusquedaView.class.getResource("/imagenes/reservado.png"))), scroll_table, null);
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
        tbHuespedes.setDefaultEditor(Object.class, null);
        panel.addTab("Huéspedes", new ImageIcon(Objects.requireNonNull(BusquedaView.class.getResource("/imagenes/pessoas.png"))), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(BusquedaView.class.getResource("/imagenes/Ha-100px.png"))));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = getjPanel();
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

                filtroBusqueda = txtBuscar.getText().toLowerCase();
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
                //System.out.println(tbReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()));
                editarRegistro();
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

        this.filtroBusqueda = txtBuscar.getText().toLowerCase();
        poblarTabla();
    }

    private JPanel getjPanel() {
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
        return header;
    }

    private void borrarRegistro() {

        switch (tabSeleccionado) {

            case HUESPEDES -> borrarRegistro(tbHuespedes);
            case RESERVAS -> borrarRegistro(tbReservas);
        }
    }

    private void borrarRegistro(JTable tabla) {

        if (tabla.getSelectedRow() != -1) {

            long id = Long.parseLong(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

            switch (tabSeleccionado) {
                case RESERVAS -> {

                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            String.format("Estas por borrar la reserva nro=%s.\n¿Deseas continuar?", id),
                            "Borrar reserva",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (opcion == 0) {

                        try {

                            new HuespedServicio().cancelarReserva(id);
                            //reservaServicio.borrarReservaAsignada(id);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Reserva cancelada.",
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
                case HUESPEDES -> {

                    String apellido = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
                    String nombre = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            String.format("¿Deseas borrar a %s , %s?\n.Se borraran también todas sus reservas asociadas.", apellido, nombre, id),
                            "Borrar huésped",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (opcion == 0) {

                        try {

                            HuespedServicio huespedServicio = new HuespedServicio();
                            huespedServicio.borrarHuesped(id);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Huésped eliminado.",
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

    private Object[][] generarArrayReserva() {
        poblarListaReservas();

        Object[][] arrayReserva = new Object[reservas.size()][4];

        int i = 0;
        for (RecReserva r : reservas) {
            arrayReserva[i][0] = r.id();
            arrayReserva[i][1] = r.fechaEntrada();
            arrayReserva[i][2] = r.fechaSalida();
            arrayReserva[i][3] = r.formaPago().name();
            i++;
        }

        return arrayReserva;
    }

    private void borrarTabla(JTable tabla) {

        ((DefaultTableModel) tabla.getModel()).getDataVector().clear();
    }

    private void editarRegistro() {

        int opcion = JOptionPane.showConfirmDialog(
                null,
                "Estas por modificar el registro seleccionado.\n¿Deseas continuar?",
                String.format("Modificar %s", tabSeleccionado.ordinal() == 0 ? "Huésped" : "Reserva"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == 0)

            switch (tabSeleccionado) {
                case HUESPEDES -> editarHuesped();
                case RESERVAS -> editarReserva();
            }
    }

    private void mostrarCancelacion() {

        JOptionPane.showMessageDialog(
                null,
                "Operación cancelada.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarReserva() {

        int row = tbReservas.getSelectedRow();
        if (row != -1) {

            long id = Long.parseLong(tbReservas.getValueAt(row, 0).toString());

            RecEditarReserva reserva = reservas.stream()
                    .filter(r -> r.id() == id)
                    .map(r -> new RecEditarReserva(r.id(), r.fechaEntrada(), r.fechaSalida(), r.formaPago()))
                    .findAny()
                    .orElse(null);

            try {
                if (reserva == null) throw new Exception("Ocurrio un error al obtener la reserva.");

                EditarReservaView view = new EditarReservaView(reserva);
                view.setLocationRelativeTo(this);
                view.setVisible(true);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            txtBuscar.setText("");
            poblarTabla();
            /*RecReserva reservaAux = reservas.stream()
                    .filter(r -> r.id() == id)
                    .toList().get(0);

            String fechaEntradaStr = tbReservas.getModel().getValueAt(row, 1).toString();
            String fechaSalidaStr = tbReservas.getValueAt(row, 2).toString();
            String formaPagoStr = tbReservas.getValueAt(row, 4).toString();

            LocalDate fechaEntrada;
            LocalDate fechaSalida;
            FormaPago formaPago;

            try {

                fechaEntrada = LocalDate.parse(fechaEntradaStr);
                fechaSalida = LocalDate.parse(fechaSalidaStr);

                if (formaPagoStr.equalsIgnoreCase(FormaPago.EFECTIVO.name()))
                    formaPago = FormaPago.EFECTIVO;
                else if (formaPagoStr.equalsIgnoreCase(FormaPago.CREDITO.name()))
                    formaPago = FormaPago.CREDITO;
                else if (formaPagoStr.equalsIgnoreCase(FormaPago.DEBITO.name()))
                    formaPago = FormaPago.CREDITO;
                else throw new Exception("Los valores ingresados son erroneos.");

                RecReserva reserva = new RecReserva(
                        id,
                        fechaEntrada,
                        fechaSalida,
                        reservaAux.valor(),
                        formaPago);

                if (fechaEntrada.isEqual(reservaAux.fechaEntrada())
                        && fechaSalida.isEqual(reservaAux.fechaSalida())
                        && formaPago == reservaAux.formaPago()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "No se encontraron modificaciones.",
                            "Operación cancelada",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    try {

                        new ReservaServicio().modificarReserva(reserva);

                        mostrarConfirmacion();

                        filtroBusqueda = "";
                        poblarTabla();
                    } catch (Exception e) {

                        mostrarCancelacion();
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Ocurrio un error en la carga de los datos.\n" +
                                "Verifica los datos ingresados",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }*/
        }
    }

    private void editarHuesped() {

        int row = tbHuespedes.getSelectedRow();
        if (row != -1) {

            long id = Long.parseLong(tbHuespedes.getValueAt(row, 0).toString());

            RecEditarHuesped huesped = new RecEditarHuesped(
                    id,
                    tbHuespedes.getValueAt(row, 2).toString(),
                    tbHuespedes.getValueAt(row, 1).toString(),
                    tbHuespedes.getValueAt(row, 5).toString()
            );

            /*JDialog view = new  JDialog(new EditarHuespedView(this, huesped, true));
            view.setVisible(true);*/


            EditarHuespedView dialog = new EditarHuespedView(this, huesped, true);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);


            /*RecHuesped huespedAux = huespedes.stream()
                    .filter(h -> h.id() == id)
                    .toList().get(0);

            String apellidoAux = tbHuespedes.getModel().getValueAt(row, 2).toString();
            String nombreAux = tbHuespedes.getValueAt(row, 1).toString();
            String telefonoAux = tbHuespedes.getValueAt(row, 5).toString();

            RecHuesped huesped = new RecHuesped(
                    id,
                    apellidoAux,
                    nombreAux,
                    huespedAux.documento(),
                    huespedAux.fechaNacimiento(),
                    huespedAux.nacionalidad(),
                    telefonoAux,
                    new ArrayList<RecReserva>());

            if (apellidoAux.equals(huespedAux.apellido())
                    && nombreAux.equals(huespedAux.nombre())
                    && telefonoAux.equals(huespedAux.telefono())) {

                JOptionPane.showMessageDialog(
                        null,
                        "No se encontraron modificaciones.",
                        "Operación cancelada",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    new HuespedServicio().modificar(huesped);

                    mostrarConfirmacion();

                    filtroBusqueda = "";
                    poblarTabla();
                } catch (Exception e) {

                    mostrarCancelacion();
                    e.printStackTrace();
                }
            }*/
        }

    }

    private void mostrarConfirmacion() {

        JOptionPane.showMessageDialog(
                null,
                "Los cambios se efectuaron correctamente.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);
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
        ArrayList<RecHuesped> lista = new ArrayList<>();
        if (filtroBusqueda.isEmpty()) {

            poblarListaHuespedes();
            lista = huespedes;

        } else {

            lista = filtrarListaHuespedes();
        }

        for (RecHuesped h : lista)
            ((DefaultTableModel) tbHuespedes.getModel()).addRow(new String[]{
                    h.id().toString(),
                    h.nombre(),
                    h.apellido(),
                    h.fechaNacimiento().toString(),
                    h.nacionalidad(),
                    h.telefono(),
                    "NO DISPONIBLE"});
    }

    private ArrayList<RecReserva> filtrarListaReservas() {

        return (ArrayList<RecReserva>) reservas.stream()
                .filter(r -> r.id().toString().toLowerCase().contains(filtroBusqueda))
                .collect(Collectors.toList());
    }

    private ArrayList<RecHuesped> filtrarListaHuespedes() {

        return (ArrayList<RecHuesped>) huespedes.stream()
                .filter(h -> h.apellido().toLowerCase().contains(filtroBusqueda)
                        || h.nombre().toLowerCase().contains(filtroBusqueda))
                .collect(Collectors.toList());
    }

    private void poblarTablaReservas() {

        borrarTabla(tbReservas);
        ArrayList<RecReserva> lista = new ArrayList<>();
        if (filtroBusqueda.isEmpty()) {

            poblarListaReservas();
            lista = reservas;

        } else {

            lista = filtrarListaReservas();
        }

        for (RecReserva r : lista)
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
