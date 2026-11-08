
package fidespn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

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

        // Preparo los campos para actualizar el marcador
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

        // Preparo los campos para registrar acontecimientos
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

    private void agregarCampo(JPanel panel, String nombre,
                              JComponent componente) {

        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setForeground(Color.WHITE);
        panel.add(etiqueta);
        panel.add(componente);
    }

    // Compruebo que el partido este asignado a mi usuario
    private boolean tienePermiso(Partido partido) {

        return partido != null
                && partido.getReporteroAsignado() != null
                && partido.getReporteroAsignado().getId()
                        == reportero.getId();
    }

    // Muestro solamente mis partidos asignados
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

    // Actualizo el marcador cuando necesito corregirlo
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

    // Registro el acontecimiento y actualizo el marcador si es gol
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

        if (tipo.equals("Gol")) {

            int golesLocal = partido.getGolesLocal();
            int golesVisitante = partido.getGolesVisitante();

            if (equipo.equals(
                    partido.getEquipoLocal().getNombre())) {

                golesLocal++;

            } else if (equipo.equals(
                    partido.getEquipoVisitante().getNombre())) {

                golesVisitante++;
            }

            // Evito superar el limite del marcador
            if (golesLocal > 99 || golesVisitante > 99) {

                JOptionPane.showMessageDialog(
                        this, "No se pueden registrar mas de 99 goles.");
                return;
            }

            partido.actualizarMarcador(
                    golesLocal, golesVisitante);
        }

        int id = sistema.getEventos().size() + 1;

        EventoPartido evento = new EventoPartido(
                id, partido, tipo, minuto, equipo, descripcion);

        sistema.getEventos().add(evento);

        txtDescripcion.setText("");
        spnMinuto.setValue(1);

        // Refresco el marcador y el historial
        cargarPartido();

        JOptionPane.showMessageDialog(
                this, "Acontecimiento registrado correctamente.");
    }

    // Ordeno los acontecimientos por minuto
    private void mostrarAcontecimientos() {

        Partido partido = obtenerPartidoSeleccionado();

        if (partido == null) {
            areaEventos.setText("");
            return;
        }

        ArrayList<EventoPartido> eventosPartido =
                new ArrayList<>();

        for (EventoPartido evento : sistema.getEventos()) {

            if (evento.getPartido().getId() == partido.getId()) {
                eventosPartido.add(evento);
            }
        }

        eventosPartido.sort(
                Comparator.comparingInt(EventoPartido::getMinuto));

        StringBuilder historial = new StringBuilder();

        for (EventoPartido evento : eventosPartido) {
            historial.append(evento.mostrarInformacion())
                    .append("\n");
        }

        if (historial.length() == 0) {

            areaEventos.setText(
                    "Todavia no hay acontecimientos registrados.");

        } else {

            areaEventos.setText(historial.toString());
        }
    }
}
