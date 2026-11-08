
package fidespn;

import javax.swing.*;
import java.awt.*;

public class VentanaReportero extends JFrame {

    private SistemaFidESPN sistema;

    private JComboBox<String> cmbPartidos;
    private JSpinner spnGolesLocal;
    private JSpinner spnGolesVisitante;
    private JComboBox<String> cmbEstado;
    private JTextArea areaInformacion;

    private final Color azul = new Color(0, 32, 96);
    private final Color amarillo = new Color(255, 192, 0);

    public VentanaReportero(SistemaFidESPN sistema) {

        // Guardo el sistema para consultar los partidos
        this.sistema = sistema;

        setTitle("FidESPN - Reportero Deportivo");
        setSize(650, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel fondo = new JPanel(new BorderLayout(10, 15));
        fondo.setBackground(azul);
        fondo.setBorder(BorderFactory.createEmptyBorder(
                20, 30, 20, 30));

        JLabel titulo = new JLabel(
                "REPORTERO DEPORTIVO", JLabel.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 23));
        titulo.setForeground(amarillo);

        // Preparo los campos para actualizar un partido
        cmbPartidos = new JComboBox<>();

        spnGolesLocal = new JSpinner(
                new SpinnerNumberModel(0, 0, 99, 1));

        spnGolesVisitante = new JSpinner(
                new SpinnerNumberModel(0, 0, 99, 1));

        cmbEstado = new JComboBox<>(new String[]{
            "Programado", "En juego", "Finalizado"
        });

        JPanel formulario = new JPanel(
                new GridLayout(4, 2, 10, 12));

        formulario.setBackground(azul);

        agregarCampo(formulario, "Seleccionar partido:", cmbPartidos);
        agregarCampo(formulario, "Goles equipo local:", spnGolesLocal);
        agregarCampo(formulario, "Goles equipo visitante:", spnGolesVisitante);
        agregarCampo(formulario, "Estado del partido:", cmbEstado);

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

        // Cargo la informacion cuando selecciono un partido
        cmbPartidos.addActionListener(e -> cargarPartido());

        // Guardo los cambios del marcador
        btnActualizar.addActionListener(e -> actualizarPartido());

        // Vuelvo a consultar los partidos registrados
        btnRecargar.addActionListener(e -> cargarListaPartidos());

        JPanel botones = new JPanel(
                new GridLayout(2, 1, 5, 8));

        botones.setBackground(azul);
        botones.add(btnActualizar);
        botones.add(btnRecargar);

        JPanel panelSuperior = new JPanel(
                new BorderLayout(10, 12));

        panelSuperior.setBackground(azul);
        panelSuperior.add(formulario, BorderLayout.CENTER);
        panelSuperior.add(botones, BorderLayout.SOUTH);

        areaInformacion = new JTextArea();
        areaInformacion.setEditable(false);
        areaInformacion.setFont(
                new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaInformacion);
        scroll.setBorder(BorderFactory.createTitledBorder(
                "Informacion del partido"));

        JPanel contenido = new JPanel(
                new BorderLayout(10, 15));

        contenido.setBackground(azul);
        contenido.add(panelSuperior, BorderLayout.NORTH);
        contenido.add(scroll, BorderLayout.CENTER);

        fondo.add(titulo, BorderLayout.NORTH);
        fondo.add(contenido, BorderLayout.CENTER);

        add(fondo);

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

    // Busco los partidos que registro el administrador
    private void cargarListaPartidos() {

        cmbPartidos.removeAllItems();

        for (Partido partido : sistema.getPartidos()) {

            String nombre = partido.getId() + " - "
                    + partido.getEquipoLocal().getNombre()
                    + " vs "
                    + partido.getEquipoVisitante().getNombre();

            cmbPartidos.addItem(nombre);
        }

        if (cmbPartidos.getItemCount() == 0) {
            areaInformacion.setText(
                    "Todavia no hay partidos registrados.");
        } else {
            cmbPartidos.setSelectedIndex(0);
            cargarPartido();
        }
    }

    // Obtengo el partido que seleccione
    private Partido obtenerPartidoSeleccionado() {

        int posicion = cmbPartidos.getSelectedIndex();

        if (posicion < 0) {
            return null;
        }

        String seleccionado =
                cmbPartidos.getSelectedItem().toString();

        String[] partes = seleccionado.split(" - ");

        try {

            int id = Integer.parseInt(partes[0]);

            return sistema.buscarPartido(id);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this, "No se pudo identificar el partido.");

            return null;
        }
    }

    // Muestro el marcador y estado actual del partido
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
    }

    // Actualizo el marcador del partido seleccionado
    private void actualizarPartido() {

        Partido partido = obtenerPartidoSeleccionado();

        if (partido == null) {

            JOptionPane.showMessageDialog(
                    this, "Debes seleccionar un partido.");

            return;
        }

        int golesLocal =
                (Integer) spnGolesLocal.getValue();

        int golesVisitante =
                (Integer) spnGolesVisitante.getValue();

        String estado =
                cmbEstado.getSelectedItem().toString();

        // Guardo el nuevo marcador y el estado
        partido.actualizarMarcador(
                golesLocal, golesVisitante);

        partido.cambiarEstado(estado);

        JOptionPane.showMessageDialog(
                this, "Marcador actualizado correctamente.");

        cargarPartido();
    }
}
