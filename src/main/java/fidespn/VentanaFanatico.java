
package fidespn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaFanatico extends JFrame {

    private SistemaFidESPN sistema;
    private Fanatico fanatico;

    private JTextArea areaPartidos;
    private JTextArea areaChat;
    private JTextField txtMensaje;

    // Guardo los mensajes para compartirlos entre las ventanas
    private static ArrayList<String> mensajesChat = new ArrayList<>();

    private final Color azul = new Color(0, 32, 96);
    private final Color amarillo = new Color(255, 192, 0);

    public VentanaFanatico(SistemaFidESPN sistema,
                           Fanatico fanatico) {

        // Guardo el sistema y el usuario que ingreso
        this.sistema = sistema;
        this.fanatico = fanatico;

        setTitle("FidESPN - Fanatico");
        setSize(720, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel fondo = new JPanel(new BorderLayout(10, 15));
        fondo.setBackground(azul);
        fondo.setBorder(BorderFactory.createEmptyBorder(
                20, 25, 20, 25));

        JLabel titulo = new JLabel(
                "FIDESPN - ZONA DEL FANATICO", JLabel.CENTER);
        titulo.setForeground(amarillo);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        // Preparo el espacio para consultar los partidos
        areaPartidos = new JTextArea(8, 30);
        areaPartidos.setEditable(false);

        JScrollPane scrollPartidos =
                new JScrollPane(areaPartidos);

        scrollPartidos.setBorder(
                BorderFactory.createTitledBorder(
                        "Resultados de los partidos"));

        JButton btnActualizar = new JButton(
                "ACTUALIZAR RESULTADOS");

        btnActualizar.setBackground(amarillo);
        btnActualizar.setForeground(azul);

        btnActualizar.addActionListener(
                e -> mostrarPartidos());

        JPanel panelPartidos = new JPanel(
                new BorderLayout(5, 8));

        panelPartidos.add(
                scrollPartidos, BorderLayout.CENTER);

        panelPartidos.add(
                btnActualizar, BorderLayout.SOUTH);

        // Preparo el chat para escribir mensajes
        areaChat = new JTextArea(8, 30);
        areaChat.setEditable(false);
        areaChat.setLineWrap(true);
        areaChat.setWrapStyleWord(true);

        JScrollPane scrollChat = new JScrollPane(areaChat);
        scrollChat.setBorder(
                BorderFactory.createTitledBorder(
                        "Chat de fanaticos"));

        txtMensaje = new JTextField();

        JButton btnEnviar = new JButton("ENVIAR");
        btnEnviar.setBackground(amarillo);
        btnEnviar.setForeground(azul);

        btnEnviar.addActionListener(e -> enviarMensaje());
        txtMensaje.addActionListener(e -> enviarMensaje());

        JPanel panelMensaje = new JPanel(
                new BorderLayout(10, 0));

        panelMensaje.add(txtMensaje, BorderLayout.CENTER);
        panelMensaje.add(btnEnviar, BorderLayout.EAST);

        JButton btnActualizarChat = new JButton(
                "ACTUALIZAR CHAT");

        btnActualizarChat.setBackground(amarillo);
        btnActualizarChat.setForeground(azul);

        btnActualizarChat.addActionListener(
                e -> mostrarMensajes());

        JPanel controlesChat = new JPanel(
                new GridLayout(2, 1, 0, 8));

        controlesChat.add(panelMensaje);
        controlesChat.add(btnActualizarChat);

        JPanel panelChat = new JPanel(
                new BorderLayout(5, 8));

        panelChat.add(scrollChat, BorderLayout.CENTER);
        panelChat.add(controlesChat, BorderLayout.SOUTH);

        JPanel contenido = new JPanel(
                new GridLayout(2, 1, 10, 15));

        contenido.add(panelPartidos);
        contenido.add(panelChat);

        fondo.add(titulo, BorderLayout.NORTH);
        fondo.add(contenido, BorderLayout.CENTER);

        add(fondo);

        mostrarPartidos();
        mostrarMensajes();
    }

    // Consulto los partidos que tengo registrados
    private void mostrarPartidos() {

        String texto = "";

        for (Partido partido : sistema.getPartidos()) {
            texto += partido.mostrarInformacion() + "\n\n";
        }

        if (texto.isEmpty()) {
            texto = "Todavia no hay partidos registrados.";
        }

        areaPartidos.setText(texto);
    }

    // Guardo el mensaje que escribo en el chat
    private void enviarMensaje() {

        String mensaje = txtMensaje.getText().trim();

        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Debes escribir un mensaje.");
            return;
        }

        // Agrego el mensaje a la lista compartida
        mensajesChat.add(
                fanatico.getNombre() + ": " + mensaje);

        txtMensaje.setText("");
        mostrarMensajes();
    }

    // Muestro todos los mensajes guardados
    private void mostrarMensajes() {

        String texto = "Bienvenido al chat de FidESPN.\n\n";

        for (String mensaje : mensajesChat) {
            texto += mensaje + "\n";
        }

        areaChat.setText(texto);
        areaChat.setCaretPosition(
                areaChat.getDocument().getLength());
    }
}
