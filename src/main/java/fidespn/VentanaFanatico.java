
package fidespn;

import javax.swing.*;
import java.awt.*;

public class VentanaFanatico extends JFrame {

    private SistemaFidESPN sistema;
    private Fanatico fanatico;

    private JTextArea areaPartidos;
    private JTextArea areaChat;
    private JTextField txtMensaje;

    private final Color azul = new Color(0, 32, 96);
    private final Color amarillo = new Color(255, 192, 0);

    public VentanaFanatico(SistemaFidESPN sistema,
                           Fanatico fanatico) {

        // Guardo el sistema y el usuario que ingreso
        this.sistema = sistema;
        this.fanatico = fanatico;

        setTitle("FidESPN - Fanatico");
        setSize(720, 580);
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

        JScrollPane scrollChat = new JScrollPane(areaChat);
        scrollChat.setBorder(
                BorderFactory.createTitledBorder(
                        "Chat de fanaticos"));

        txtMensaje = new JTextField();

        JButton btnEnviar = new JButton("ENVIAR");
        btnEnviar.setBackground(amarillo);
        btnEnviar.setForeground(azul);

        btnEnviar.addActionListener(e -> enviarMensaje());

        JPanel panelMensaje = new JPanel(
                new BorderLayout(10, 0));

        panelMensaje.add(txtMensaje, BorderLayout.CENTER);
        panelMensaje.add(btnEnviar, BorderLayout.EAST);

        JPanel panelChat = new JPanel(
                new BorderLayout(5, 8));

        panelChat.add(scrollChat, BorderLayout.CENTER);
        panelChat.add(panelMensaje, BorderLayout.SOUTH);

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

        // Muestro el mensaje con el nombre del fanatico
        areaChat.append(
                fanatico.getNombre() + ": " + mensaje + "\n");

        txtMensaje.setText("");
    }

    // Muestro los mensajes del chat
    private void mostrarMensajes() {

        areaChat.setText(
                "Bienvenido al chat de FidESPN.\n");
    }
}
