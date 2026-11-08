
package fidespn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VentanaAdministrador extends JFrame {

    private SistemaFidESPN sistema;

    private JComboBox<String> cmbLocal;
    private JComboBox<String> cmbVisitante;
    private JComboBox<String> cmbEstadio;
    private JComboBox<String> cmbEstado;
    private JComboBox<String> cmbHora;
    private JComboBox<String> cmbMinutos;

    private JComboBox<String> cmbPartido;
    private JComboBox<String> cmbReportero;

    private JButton btnFecha;
    private Date fechaSeleccionada = new Date();

    private DefaultTableModel modeloTabla;

    private final Color azul = new Color(0, 32, 96);
    private final Color amarillo = new Color(255, 192, 0);

    public VentanaAdministrador(SistemaFidESPN sistema) {

        // Guardo el sistema para trabajar con los partidos
        this.sistema = sistema;

        setTitle("FidESPN - Administrador");
        setSize(950, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel fondo = new JPanel(new BorderLayout(10, 15));
        fondo.setBackground(azul);
        fondo.setBorder(BorderFactory.createEmptyBorder(
                20, 25, 20, 25));

        JLabel titulo = new JLabel(
                "ADMINISTRACION DE PARTIDOS", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(amarillo);

        // Preparo los equipos disponibles
        String[] equipos = {
            "Seleccionar equipo",
            "Argentina", "Brasil", "Costa Rica",
            "Mexico", "Estados Unidos", "Canada",
            "España", "Francia", "Alemania",
            "Portugal", "Inglaterra", "Japon",
            "Uruguay", "Colombia", "Ecuador",
            "Marruecos"
        };

        cmbLocal = new JComboBox<>(equipos);
        cmbVisitante = new JComboBox<>(equipos);

        String[] estadios = {
            "Seleccionar estadio",
            "Estadio Azteca",
            "MetLife Stadium",
            "SoFi Stadium",
            "AT&T Stadium",
            "BC Place",
            "BMO Field",
            "NRG Stadium",
            "Mercedes-Benz Stadium"
        };

        cmbEstadio = new JComboBox<>(estadios);

        cmbEstado = new JComboBox<>(new String[]{
            "Programado", "En juego", "Finalizado"
        });

        String[] horas = new String[24];

        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d", i);
        }

        cmbHora = new JComboBox<>(horas);
        cmbHora.setSelectedItem("18");

        cmbMinutos = new JComboBox<>(new String[]{
            "00", "15", "30", "45"
        });

        // Preparo el calendario
        btnFecha = new JButton();
        btnFecha.setBackground(Color.WHITE);
        btnFecha.setForeground(azul);

        actualizarTextoFecha();
        btnFecha.addActionListener(e -> abrirCalendario());

        JPanel formulario = new JPanel(
                new GridLayout(6, 2, 10, 12));
        formulario.setBackground(azul);

        agregarCampo(formulario, "Equipo local:", cmbLocal);
        agregarCampo(formulario, "Equipo visitante:", cmbVisitante);
        agregarCampo(formulario, "Fecha:", btnFecha);

        JPanel panelHora = new JPanel(
                new GridLayout(1, 2, 10, 0));
        panelHora.setBackground(azul);

        panelHora.add(cmbHora);
        panelHora.add(cmbMinutos);

        agregarCampo(formulario, "Hora del partido:", panelHora);
        agregarCampo(formulario, "Estadio:", cmbEstadio);
        agregarCampo(formulario, "Estado:", cmbEstado);

        JButton btnRegistrar = new JButton("REGISTRAR PARTIDO");
        btnRegistrar.setBackground(amarillo);
        btnRegistrar.setForeground(azul);
        btnRegistrar.setFont(
                new Font("Arial", Font.BOLD, 14));

        btnRegistrar.addActionListener(e -> registrarPartido());

        JPanel panelRegistro = new JPanel(
                new BorderLayout(10, 12));
        panelRegistro.setBackground(azul);

        panelRegistro.add(formulario, BorderLayout.CENTER);
        panelRegistro.add(btnRegistrar, BorderLayout.SOUTH);

        // Preparo la asignacion de reporteros
        cmbPartido = new JComboBox<>();
        cmbReportero = new JComboBox<>();

        JPanel formularioAsignacion = new JPanel(
                new GridLayout(2, 2, 10, 10));
        formularioAsignacion.setBackground(azul);

        agregarCampo(formularioAsignacion,
                "Seleccionar partido:", cmbPartido);

        agregarCampo(formularioAsignacion,
                "Seleccionar reportero:", cmbReportero);

        JButton btnAsignar = new JButton("ASIGNAR REPORTERO");
        btnAsignar.setBackground(amarillo);
        btnAsignar.setForeground(azul);
        btnAsignar.setFont(
                new Font("Arial", Font.BOLD, 14));

        btnAsignar.addActionListener(e -> asignarReportero());

        JPanel panelAsignacion = new JPanel(
                new BorderLayout(10, 10));
        panelAsignacion.setBackground(azul);
        panelAsignacion.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(amarillo),
                        "Asignacion de reporteros",
                        0, 0,
                        new Font("Arial", Font.BOLD, 14),
                        Color.WHITE));

        panelAsignacion.add(
                formularioAsignacion, BorderLayout.CENTER);
        panelAsignacion.add(
                btnAsignar, BorderLayout.SOUTH);

        // Muestro tambien el reportero asignado
        modeloTabla = new DefaultTableModel(
                new String[]{
                    "ID", "Local", "Visitante",
                    "Fecha", "Hora", "Estadio",
                    "Estado", "Reportero"
                }, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] anchos = {45, 110, 110, 95, 60, 170, 95, 140};

        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i)
                    .setPreferredWidth(anchos[i]);
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder(
                "Partidos registrados"));

        JPanel panelSuperior = new JPanel(
                new BorderLayout(10, 15));
        panelSuperior.setBackground(azul);

        panelSuperior.add(
                panelRegistro, BorderLayout.NORTH);
        panelSuperior.add(
                panelAsignacion, BorderLayout.SOUTH);

        JPanel contenido = new JPanel(
                new BorderLayout(10, 15));
        contenido.setBackground(azul);

        contenido.add(panelSuperior, BorderLayout.NORTH);
        contenido.add(scroll, BorderLayout.CENTER);

        fondo.add(titulo, BorderLayout.NORTH);
        fondo.add(contenido, BorderLayout.CENTER);

        add(fondo);

        mostrarPartidos();
        cargarReporteros();
    }

    // Coloco cada etiqueta junto a su campo
    private void agregarCampo(JPanel panel, String nombre,
                              JComponent componente) {

        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setForeground(Color.WHITE);

        panel.add(etiqueta);
        panel.add(componente);
    }

    // Muestro la fecha seleccionada
    private void actualizarTextoFecha() {

        SimpleDateFormat formato =
                new SimpleDateFormat("dd/MM/yyyy");

        btnFecha.setText(
                formato.format(fechaSeleccionada)
                + "  -  Abrir calendario");
    }

    // Abro el calendario para seleccionar la fecha
    private void abrirCalendario() {

        JDialog ventana = new JDialog(
                this, "Seleccionar fecha", true);

        ventana.setSize(370, 350);
        ventana.setLocationRelativeTo(this);
        ventana.setLayout(new BorderLayout(5, 10));

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaSeleccionada);

        JPanel encabezado = new JPanel(new BorderLayout());

        JButton btnAnterior = new JButton("<");
        JButton btnSiguiente = new JButton(">");

        JLabel lblMes = new JLabel("", JLabel.CENTER);
        lblMes.setFont(new Font("Arial", Font.BOLD, 16));

        encabezado.add(btnAnterior, BorderLayout.WEST);
        encabezado.add(lblMes, BorderLayout.CENTER);
        encabezado.add(btnSiguiente, BorderLayout.EAST);

        JPanel dias = new JPanel(
                new GridLayout(0, 7, 3, 3));

        Runnable mostrarMes = () -> {

            dias.removeAll();

            SimpleDateFormat formatoMes =
                    new SimpleDateFormat("MMMM yyyy");

            lblMes.setText(
                    formatoMes.format(calendario.getTime()));

            String[] nombres = {
                "L", "M", "M", "J", "V", "S", "D"
            };

            for (String nombre : nombres) {

                JLabel etiqueta = new JLabel(
                        nombre, JLabel.CENTER);

                etiqueta.setFont(
                        new Font("Arial", Font.BOLD, 12));

                dias.add(etiqueta);
            }

            Calendar inicio = (Calendar) calendario.clone();
            inicio.set(Calendar.DAY_OF_MONTH, 1);

            int primerDia = inicio.get(Calendar.DAY_OF_WEEK);
            int espacios = (primerDia + 5) % 7;

            for (int i = 0; i < espacios; i++) {
                dias.add(new JLabel(""));
            }

            int cantidadDias =
                    inicio.getActualMaximum(
                            Calendar.DAY_OF_MONTH);

            for (int dia = 1; dia <= cantidadDias; dia++) {

                final int numeroDia = dia;

                JButton botonDia =
                        new JButton(String.valueOf(dia));

                botonDia.setMargin(
                        new Insets(2, 2, 2, 2));

                botonDia.addActionListener(e -> {

                    calendario.set(
                            Calendar.DAY_OF_MONTH, numeroDia);

                    fechaSeleccionada = calendario.getTime();

                    actualizarTextoFecha();
                    ventana.dispose();
                });

                dias.add(botonDia);
            }

            dias.revalidate();
            dias.repaint();
        };

        btnAnterior.addActionListener(e -> {
            calendario.add(Calendar.MONTH, -1);
            mostrarMes.run();
        });

        btnSiguiente.addActionListener(e -> {
            calendario.add(Calendar.MONTH, 1);
            mostrarMes.run();
        });

        mostrarMes.run();

        ventana.add(encabezado, BorderLayout.NORTH);
        ventana.add(dias, BorderLayout.CENTER);

        ventana.setVisible(true);
    }

    // Registro el partido con los datos seleccionados
    private void registrarPartido() {

        if (cmbLocal.getSelectedIndex() == 0
                || cmbVisitante.getSelectedIndex() == 0
                || cmbEstadio.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this, "Debes seleccionar los datos del partido");
            return;
        }

        String local = cmbLocal.getSelectedItem().toString();
        String visitante = cmbVisitante.getSelectedItem().toString();
        String estadio = cmbEstadio.getSelectedItem().toString();
        String estado = cmbEstado.getSelectedItem().toString();

        String hora = cmbHora.getSelectedItem().toString()
                + ":"
                + cmbMinutos.getSelectedItem().toString();

        if (local.equals(visitante)) {

            JOptionPane.showMessageDialog(
                    this, "Los equipos deben ser diferentes");
            return;
        }

        SimpleDateFormat formato =
                new SimpleDateFormat("dd/MM/yyyy");

        String fecha = formato.format(fechaSeleccionada);

        int id = sistema.getPartidos().size() + 1;

        Equipo equipoLocal = new Equipo(
                id * 2 - 1, local, "Por definir");

        Equipo equipoVisitante = new Equipo(
                id * 2, visitante, "Por definir");

        sistema.agregarEquipo(equipoLocal);
        sistema.agregarEquipo(equipoVisitante);

        // Guardo la fecha y la hora del partido
        Partido partido = new Partido(
                id, equipoLocal, equipoVisitante,
                fecha + " " + hora, estadio);

        partido.cambiarEstado(estado);
        sistema.agregarPartido(partido);

        JOptionPane.showMessageDialog(
                this, "Partido registrado correctamente");

        cmbLocal.setSelectedIndex(0);
        cmbVisitante.setSelectedIndex(0);
        cmbEstadio.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        cmbHora.setSelectedItem("18");
        cmbMinutos.setSelectedItem("00");

        mostrarPartidos();
    }

    // Cargo los partidos que puedo asignar
    private void cargarPartidos() {

        cmbPartido.removeAllItems();
        cmbPartido.addItem("Seleccionar partido");

        for (Partido partido : sistema.getPartidos()) {

            cmbPartido.addItem(
                    partido.getId() + " - "
                    + partido.getEquipoLocal().getNombre()
                    + " vs "
                    + partido.getEquipoVisitante().getNombre());
        }
    }

    // Busco los usuarios que son reporteros deportivos
    private void cargarReporteros() {

        cmbReportero.removeAllItems();
        cmbReportero.addItem("Seleccionar reportero");

        for (Usuario usuario : sistema.getUsuarios()) {

            if (usuario instanceof ReporteroDeportivo) {

                cmbReportero.addItem(
                        usuario.getId() + " - "
                        + usuario.getNombre());
            }
        }
    }

    // Asigno el reportero al partido seleccionado
    private void asignarReportero() {

        if (cmbPartido.getSelectedIndex() <= 0
                || cmbReportero.getSelectedIndex() <= 0) {

            JOptionPane.showMessageDialog(
                    this, "Debes seleccionar un partido y un reportero");
            return;
        }

        int indicePartido = cmbPartido.getSelectedIndex() - 1;
        Partido partido = sistema.getPartidos().get(indicePartido);

        int indiceReportero = cmbReportero.getSelectedIndex() - 1;
        int contador = 0;
        ReporteroDeportivo reportero = null;

        for (Usuario usuario : sistema.getUsuarios()) {

            if (usuario instanceof ReporteroDeportivo) {

                if (contador == indiceReportero) {
                    reportero = (ReporteroDeportivo) usuario;
                    break;
                }

                contador++;
            }
        }

        if (reportero != null) {

            partido.asignarReportero(reportero);

            JOptionPane.showMessageDialog(
                    this, "Reportero asignado correctamente");

            mostrarPartidos();
        }
    }

    // Actualizo la tabla de partidos registrados
    private void mostrarPartidos() {

        modeloTabla.setRowCount(0);

        for (Partido partido : sistema.getPartidos()) {

            String fechaCompleta = partido.getFecha();
            String fecha = fechaCompleta;
            String hora = "";

            String[] partes = fechaCompleta.split(" ");

            if (partes.length >= 2) {
                fecha = partes[0];
                hora = partes[1];
            }

            String nombreReportero = "Sin asignar";

            if (partido.getReporteroAsignado() != null) {
                nombreReportero =
                        partido.getReporteroAsignado().getNombre();
            }

            modeloTabla.addRow(new Object[]{
                partido.getId(),
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre(),
                fecha,
                hora,
                partido.getEstadio(),
                partido.getEstado(),
                nombreReportero
            });
        }

        cargarPartidos();
    }
}
