
package fidespn;

import javax.swing.*;
import java.awt.*;

public class VentanaReportero extends JFrame {

    private SistemaFidESPN sistema;
    private ReporteroDeportivo reportero;

    private JComboBox<String> cmbPartidos;
    private JSpinner spnGolesLocal;
    private JSpinner spnGolesVisitante;
    private JComboBox<String> cmbEstado;
    private JTextArea areaInformacion;

    private JComboBox<String> cmbTipoEvento;
    private JComboBox<String> cmbEquipoEvento;
    private JSpinner spnMinuto;
    private JTextField txtDescripcion;
    private JTextArea areaEventos;

    private final Color azul = new Color(0, 32, 96);
    private final Color amarillo = new Color(255, 192, 0);

    public VentanaReportero(SistemaFidESPN sistema,
                            ReporteroDeportivo reportero) {

        // Guardo el sistema y el reportero que inicio sesion
        this.sistema = sistema;
        this.reportero = reportero;

        setTitle("FidESPN - Reportero Deportivo");
        setSize(780, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel fondo = new JPanel(new BorderLayout(10, 15));
        fondo.setBackground(azul);
        fondo.setBorder(BorderFactory.createEmptyBorder(
                15, 25, 15, 25));

        JLabel titulo = new JLabel(
                "REPORTERO DEPORTIVO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 23));
        titulo.setForeground(amarillo);

        // Preparo los campos del marcador
        cmbPartidos = new JComboBox<>();

        spnGolesLocal = new JSpinner(
                new SpinnerNumberModel(0, 0, 99, 1));

        spnGolesVisitante = new JSpinner(
                new SpinnerNumberModel(0, 0, 99, 1));

        cmbEstado = new JComboBox<>(new String[]{
            "Programado", "En juego", "Finalizado"
        });

        JPanel formulario = new JPanel(
                new GridLayout(4, 2, 10, 10));
        formulario.setBackground(azul);

        agregarCampo(formulario,
                "Seleccionar partido:", cmbPartidos);
        agregarCampo(formulario,
                "Goles equipo local:", spnGolesLocal);
        agregarCampo(formulario,
                "Goles equipo visitante:", spnGolesVisitante);
        agregarCampo(formulario,
                "Estado del partido:", cmbEstado);

        JButton btnActualizar = new JButton(
                "ACTUALIZAR MARCADOR");
        btnActualizar.setBackground(amarillo);
        btnActualizar.setForeground(azul);
        btnActualizar.setFont(
                new Font("Arial", Font.BOLD, 14));

        JButton btnRecargar = new JButton(
                "ACTUALIZAR LISTA DE PARTIDOS");
        btnRecargar.setBackground(Color.WHITE);
        btnRecargar.setForeground(azul);

        JPanel botones = new JPanel(
                new GridLayout(2, 1, 5, 7));
        botones.setBackground(azul);
        botones.add(btnActualizar);
        botones.add(btnRecargar);

        JPanel panelMarcador = new JPanel(
                new BorderLayout(10, 10));
        panelMarcador.setBackground(azul);
        panelMarcador.add(formulario, BorderLayout.CENTER);
        panelMarcador.add(botones, BorderLayout.SOUTH);

        areaInformacion = new JTextArea(5, 30);
        areaInformacion.setEditable(false);
        areaInformacion.setFont(
                new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollInformacion =
                new JScrollPane(areaInformacion);
        scrollInformacion.setBorder(
                BorderFactory.createTitledBorder(
                        "Informacion del partido"));

        // Preparo los campos de los acontecimientos
        cmbTipoEvento = new JComboBox<>(new String[]{
            "Gol",
            "Tarjeta amarilla",
            "Tarjeta roja",
            "Sustitucion",
            "Otro"
        });

        cmbEquipoEvento = new JComboBox<>();

        spnMinuto = new JSpinner(
                new SpinnerNumberModel(1, 1, 120, 1));

        txtDescripcion = new JTextField();

        JPanel formularioEventos = new JPanel(
                new GridLayout(4, 2, 10, 10));
        formularioEventos.setBackground(azul);

        agregarCampo(formularioEventos,
                "Tipo de acontecimiento:", cmbTipoEvento);
        agregarCampo(formularioEventos,
                "Equipo:", cmbEquipoEvento);
        agregarCampo(formularioEventos,
                "Minuto:", spnMinuto);
        agregarCampo(formularioEventos,
                "Descripcion:", txtDescripcion);

        JButton btnRegistrarEvento = new JButton(
                "REGISTRAR ACONTECIMIENTO");
        btnRegistrarEvento.setBackground(amarillo);
        btnRegistrarEvento.setForeground(azul);
        btnRegistrarEvento.setFont(
                new Font("Arial", Font.BOLD, 14));

        JPanel panelEventos = new JPanel(
                new BorderLayout(10, 10));
        panelEventos.setBackground(azul);
        panelEventos.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(amarillo),
                        "ACONTECIMIENTOS DEL PARTIDO",
                        0, 0,
                        new Font("Arial", Font.BOLD, 13),
                        amarillo));

        panelEventos.add(formularioEventos, BorderLayout.CENTER);
        panelEventos.add(btnRegistrarEvento, BorderLayout.SOUTH);

        areaEventos = new JTextArea(6, 30);
        areaEventos.setEditable(false);
        areaEventos.setFont(
                new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollEventos =
                new JScrollPane(areaEventos);
        scrollEventos.setBorder(
                BorderFactory.createTitledBorder(
                        "Historial de acontecimientos"));

        // Organizo las secciones en la ventana
        JPanel contenido = new JPanel();
        contenido.setLayout(
                new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBackground(azul);

        panelMarcador.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEventos.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollEventos.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenido.add(panelMarcador);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(scrollInformacion);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(panelEventos);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(scrollEventos);

        JScrollPane scrollPrincipal =
                new JScrollPane(contenido);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar()
                .setUnitIncrement(16);

        fondo.add(titulo, BorderLayout.NORTH);
        fondo.add(scrollPrincipal, BorderLayout.CENTER);

        add(fondo);

        cmbPartidos.addActionListener(e -> cargarPartido());
        btnActualizar.addActionListener(e -> actualizarPartido());
        btnRecargar.addActionListener(e -> cargarListaPartidos());
        btnRegistrarEvento.addActionListener(
                e -> registrarAcontecimiento());

        cargarListaPartidos();
    }

    // Coloco cada campo junto a su nombre
    private void agregarCampo(JPanel panel, String nombre,
                              JComponent componente) {

        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setForeground(Color.WHITE);

        panel.add(etiqueta);
        panel.add(componente);
    }

    // Compruebo si el partido esta asignado a mi usuario
    private boolean tienePermiso(Partido partido) {

        return partido != null
                && partido.getReporteroAsignado() != null
                && partido.getReporteroAsignado().getId()
                        == reportero.getId();
    }

    // Cargo solamente los partidos que tengo asignados
    private void cargarListaPartidos() {

        cmbPartidos.removeAllItems();

        for (Partido partido : sistema.getPartidos()) {

            if (tienePermiso(partido)) {

                String nombre = partido.getId() + " - "
                        + partido.getEquipoLocal().getNombre()
                        + " vs "
                        + partido.getEquipoVisitante().getNombre();

                cmbPartidos.addItem(nombre);
            }
        }

        if (cmbPartidos.getItemCount() == 0) {

            areaInformacion.setText(
                    "No tienes partidos asignados.\n"
                    + "Consulta con el administrador.");

            areaEventos.setText("");
            cmbEquipoEvento.removeAllItems();

        } else {

            cmbPartidos.setSelectedIndex(0);
            cargarPartido();
        }
    }

    // Busco el partido seleccionado
    private Partido obtenerPartidoSeleccionado() {

        if (cmbPartidos.getSelectedIndex() < 0) {
            return null;
        }

        String seleccionado =
                cmbPartidos.getSelectedItem().toString();

        String[] partes = seleccionado.split(" - ");

        try {

            int id = Integer.parseInt(partes[0]);

            Partido partido = sistema.buscarPartido(id);

            if (tienePermiso(partido)) {
                return partido;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this, "No se pudo identificar el partido.");
        }

        return null;
    }

    // Muestro la informacion del partido seleccionado
    private void cargarPartido() {

        Partido partido = obtenerPartidoSeleccionado();

        if (partido == null) {
            return;
        }

        spnGolesLocal.setValue(partido.getGolesLocal());
        spnGolesVisitante.setValue(partido.getGolesVisitante());
        cmbEstado.setSelectedItem(partido.getEstado());

        areaInformacion.setText(
                partido.mostrarInformacion());

        cmbEquipoEvento.removeAllItems();
        cmbEquipoEvento.addItem(
                partido.getEquipoLocal().getNombre());
        cmbEquipoEvento.addItem(
                partido.getEquipoVisitante().getNombre());

        mostrarAcontecimientos();
    }

    // Actualizo el marcador del partido asignado
    private void actualizarPartido() {

        Partido partido = obtenerPartidoSeleccionado();

        if (partido == null) {

            JOptionPane.showMessageDialog(
                    this, "Debes seleccionar un partido asignado.");
            return;
        }

        int golesLocal =
                (Integer) spnGolesLocal.getValue();

        int golesVisitante =
                (Integer) spnGolesVisitante.getValue();

        String estado =
                cmbEstado.getSelectedItem().toString();

        partido.actualizarMarcador(
                golesLocal, golesVisitante);

        partido.cambiarEstado(estado);

        JOptionPane.showMessageDialog(
                this, "Marcador actualizado correctamente.");

        cargarPartido();
    }

    // Registro un acontecimiento del partido
    private void registrarAcontecimiento() {

        Partido partido = obtenerPartidoSeleccionado();

        if (partido == null) {

            JOptionPane.showMessageDialog(
                    this, "Debes seleccionar un partido asignado.");
            return;
        }

        String descripcion = txtDescripcion.getText().trim();

        if (descripcion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this, "Debes escribir una descripcion.");
            return;
        }

        String tipo =
                cmbTipoEvento.getSelectedItem().toString();

        String equipo =
                cmbEquipoEvento.getSelectedItem().toString();

        int minuto =
                (Integer) spnMinuto.getValue();

        // Guardo el acontecimiento en el sistema
        int id = sistema.getEventos().size() + 1;

        EventoPartido evento = new EventoPartido(
                id, partido, tipo, minuto, equipo, descripcion);

        sistema.getEventos().add(evento);

        JOptionPane.showMessageDialog(
                this, "Acontecimiento registrado correctamente.");

        txtDescripcion.setText("");
        spnMinuto.setValue(1);

        mostrarAcontecimientos();
    }

    // Muestro los acontecimientos del partido seleccionado
    private void mostrarAcontecimientos() {

        Partido partido = obtenerPartidoSeleccionado();

        if (partido == null) {
            areaEventos.setText("");
            return;
        }

        String historial = "";

        for (EventoPartido evento : sistema.getEventos()) {

            if (evento.getPartido().getId() == partido.getId()) {

                historial += evento.mostrarInformacion() + "\n";
            }
        }

        if (historial.isEmpty()) {

            areaEventos.setText(
                    "Todavia no hay acontecimientos registrados.");

        } else {

            areaEventos.setText(historial);
        }
    }
}
