package ar.com.facundobazan.hotel_alura.views;

import ar.com.facundobazan.hotel_alura.entities.records.RecHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecNuevaReserva;
import ar.com.facundobazan.hotel_alura.entities.records.RecNuevoHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecReserva;
import ar.com.facundobazan.hotel_alura.services.HuespedServicio;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.Format;
import java.time.ZoneId;

@SuppressWarnings("serial")
public class RegistroHuespedView extends JFrame {

    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtNreserva;
    private JDateChooser txtFechaN;
    private JComboBox<Format> txtNacionalidad;
    private JLabel labelExit;
    private JLabel labelAtras;
    private JPanel btnexit;
    int xMouse, yMouse;
    private long nroReserva;
    private static String documentoHuesped;
    private static RecHuesped huesped;
    private boolean esNuevoHuesped;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistroHuespedView frame = new RegistroHuespedView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegistroHuespedView(Long nroReserva) {

        this.nroReserva = nroReserva;
        cargarCompponentes();
    }

    /**
     * Create the frame.
     */
    public RegistroHuespedView() {

        cargarCompponentes();
    }

    private void cargarCompponentes() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuespedView.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 634);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.text);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
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
        header.setBackground(SystemColor.text);
        header.setOpaque(false);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAtras();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(new Color(12, 138, 199));
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setForeground(Color.WHITE);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);


        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNombre.setBounds(560, 135, 285, 33);
        txtNombre.setBackground(Color.WHITE);
        txtNombre.setColumns(10);
        txtNombre.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtNombre);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtApellido.setBounds(560, 204, 285, 33);
        txtApellido.setColumns(10);
        txtApellido.setBackground(Color.WHITE);
        txtApellido.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtApellido);

        txtFechaN = new JDateChooser();
        txtFechaN.setBounds(560, 278, 285, 36);
        txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuespedView.class.getResource("/imagenes/icon-reservas.png")));
        txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
        txtFechaN.setDateFormatString("yyyy-MM-dd");
        contentPane.add(txtFechaN);

        txtNacionalidad = new JComboBox();
        txtNacionalidad.setBounds(560, 350, 289, 36);
        txtNacionalidad.setBackground(SystemColor.text);
        txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNacionalidad.setModel(new DefaultComboBoxModel(new String[]{"afgano-afgana", "alemán-alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"}));
        contentPane.add(txtNacionalidad);

        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setBounds(562, 119, 253, 14);
        lblNombre.setForeground(SystemColor.textInactiveText);
        lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNombre);

        JLabel lblApellido = new JLabel("APELLIDO");
        lblApellido.setBounds(560, 189, 255, 14);
        lblApellido.setForeground(SystemColor.textInactiveText);
        lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblApellido);

        JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
        lblFechaN.setBounds(560, 256, 255, 14);
        lblFechaN.setForeground(SystemColor.textInactiveText);
        lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblFechaN);

        JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
        lblNacionalidad.setBounds(560, 326, 255, 14);
        lblNacionalidad.setForeground(SystemColor.textInactiveText);
        lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNacionalidad);

        JLabel lblTelefono = new JLabel("TELÉFONO");
        lblTelefono.setBounds(562, 406, 253, 14);
        lblTelefono.setForeground(SystemColor.textInactiveText);
        lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtTelefono.setBounds(560, 424, 285, 33);
        txtTelefono.setColumns(10);
        txtTelefono.setBackground(Color.WHITE);
        txtTelefono.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtTelefono);

        JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
        lblTitulo.setBounds(606, 55, 234, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
        contentPane.add(lblTitulo);

        JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
        lblNumeroReserva.setBounds(560, 474, 253, 14);
        lblNumeroReserva.setForeground(SystemColor.textInactiveText);
        lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNumeroReserva);

        txtNreserva = new JTextField();
        txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNreserva.setBounds(560, 495, 285, 33);
        txtNreserva.setColumns(10);
        txtNreserva.setBackground(Color.WHITE);
        txtNreserva.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtNreserva);
        txtNreserva.setEditable(false);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(560, 170, 289, 2);
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, 289, 2);
        separator_1_2_1.setForeground(new Color(12, 138, 199));
        separator_1_2_1.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 314, 289, 2);
        separator_1_2_2.setForeground(new Color(12, 138, 199));
        separator_1_2_2.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 386, 289, 2);
        separator_1_2_3.setForeground(new Color(12, 138, 199));
        separator_1_2_3.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 457, 289, 2);
        separator_1_2_4.setForeground(new Color(12, 138, 199));
        separator_1_2_4.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 529, 289, 2);
        separator_1_2_5.setForeground(new Color(12, 138, 199));
        separator_1_2_5.setBackground(new Color(12, 138, 199));
        contentPane.add(separator_1_2_5);

        JPanel btnguardar = new JPanel();
        btnguardar.setBounds(723, 560, 122, 35);
        btnguardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                guardarHuesped();
            }
        });
        btnguardar.setLayout(null);
        btnguardar.setBackground(new Color(12, 138, 199));
        contentPane.add(btnguardar);
        btnguardar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel labelGuardar = new JLabel("GUARDAR");
        labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
        labelGuardar.setForeground(Color.WHITE);
        labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelGuardar.setBounds(0, 0, 122, 35);
        btnguardar.add(labelGuardar);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 489, 634);
        panel.setBackground(new Color(12, 138, 199));
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 121, 479, 502);
        panel.add(imagenFondo);
        imagenFondo.setIcon(new ImageIcon(RegistroHuespedView.class.getResource("/imagenes/registro.png")));

        JLabel logo = new JLabel("");
        logo.setBounds(194, 39, 104, 107);
        panel.add(logo);
        logo.setIcon(new ImageIcon(RegistroHuespedView.class.getResource("/imagenes/Ha-100px.png")));

        btnexit = new JPanel();
        btnexit.setBounds(857, 0, 53, 36);
        contentPane.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarSession();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.white);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(SystemColor.black);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        txtNreserva.setText(String.valueOf(nroReserva));

        consultarDocumento();
    }

    private void volverAtras() {
        ReservasView reservas = new ReservasView();
        reservas.setVisible(true);
        dispose();
    }

    private void regresarMenu() {
        MenuUsuarioView menuUsuario = new MenuUsuarioView();
        menuUsuario.setVisible(true);
        dispose();
    }

    private void cerrarSession() {
        MenuPrincipalView principal = new MenuPrincipalView();
        principal.setVisible(true);
        dispose();
    }

    private void guardarHuesped() {

        boolean esValido = validarCampos();
        if (esValido) {

            if (esNuevoHuesped) {


                RecHuesped huespedAux = new HuespedServicio().registrarHuesped(
                        new RecNuevoHuesped(
                                txtApellido.getText(),
                                txtNombre.getText(),
                                documentoHuesped,
                                txtFechaN.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                txtNacionalidad.getSelectedItem().toString(),
                                txtTelefono.getText()));

                new HuespedServicio().asignarReserva(huespedAux.id(), nroReserva);

            } else {

                new HuespedServicio().asignarReserva(huesped.id(), nroReserva);
            }


        }

        JOptionPane.showConfirmDialog(null,
                "Reserva confirmada.",
                "Reserva",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        regresarMenu();

    }

    private boolean validarCampos() {

        if (this.txtNombre.getText().isEmpty()) {

            mostrarErrorValidacion("nombre");
            txtNombre.requestFocus();
            return false;
        }
        if (this.txtApellido.getText().isEmpty()) {
            mostrarErrorValidacion("apellido");
            txtApellido.requestFocus();
            return false;
        }
        if (this.txtFechaN.getDate() == null) {
            mostrarErrorValidacion("fecha de nacimiento");
            txtTelefono.requestFocus();
            return false;
        }
        if (this.txtTelefono.getText().isEmpty()) {
            mostrarErrorValidacion("telefono");
            txtTelefono.requestFocus();
            return false;
        }
        if (this.nroReserva == 0) {
            mostrarErrorValidacion("reserva");
            return false;
        }
        return true;
    }

    private void mostrarErrorValidacion(String elemento) {

        JOptionPane.showConfirmDialog(
                null,
                String.format("El campo %s no puede estar vacío.\n" +
                        "Comprueba los campos antes de continuar.", elemento),
                "Error en validación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    private void consultarDocumento() {

        esNuevoHuesped = true;
        documentoHuesped = "";
        while (documentoHuesped.isBlank()) {

            String result = JOptionPane.showInputDialog(null, "Ingresa el documento del comprador",
                    "Buscador de huespedes", JOptionPane.QUESTION_MESSAGE);

            if (result.matches("^\\d{6,12}$")) {

                documentoHuesped = result;
            } else {

                JOptionPane.showConfirmDialog(null, "Solo se permiten números, de 6 a 12 digitos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        HuespedServicio huespedServicio = new HuespedServicio();
        huesped = huespedServicio.buscarPorDocumento(documentoHuesped);

        if (huesped == null) {

            esNuevoHuesped = true;
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "No se encontraron resultados.\n¿Desea continuar con el registro?",
                    "Resultado de la busqueda",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            switch (opcion) {
                case JOptionPane.YES_OPTION -> continuarReserva();
                case JOptionPane.NO_OPTION -> cancelarReserva();
            }
        } else {
            esNuevoHuesped = false;
            this.txtApellido.setText(huesped.apellido());
            this.txtNombre.setText(huesped.nombre());
            this.txtFechaN.setDate(Date.from(
                    huesped.fechaNacimiento().atStartOfDay()
                            .atZone(ZoneId.systemDefault()).toInstant()));
            this.txtTelefono.setText(huesped.telefono());
            this.txtNacionalidad.setSelectedItem(huesped.nacionalidad());

            txtApellido.setEditable(false);
            txtNombre.setEditable(false);
            txtFechaN.setEnabled(false);
            txtTelefono.setEditable(false);
            txtNacionalidad.setEnabled(false);
        }
    }

    private static void continuarReserva() {
    }

    private static void cancelarReserva() {
        //dispose();

        /*int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Reintentar consulta?",
                "Resultado de la busqueda",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        switch (opcion) {
            case JOptionPane.YES_OPTION -> consultarDocumento();
            case JOptionPane.NO_OPTION -> this.dispose();
        }*/

        // TODO: Borrar Reserva
        /*MenuPrincipal principal = new MenuPrincipal();
        principal.setVisible(true);*/
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
