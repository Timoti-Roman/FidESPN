
package fidespn;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private SistemaFidESPN sistema;

    // Defino los colores que voy a usar en FidESPN
    private final Color azul = new Color(0, 32, 96);
    private final Color amarillo = new Color(255, 192, 0);
    private final Color morado = new Color(112, 48, 160);

    public VentanaPrincipal(SistemaFidESPN sistema) {

        // Guardo el sistema para usar los usuarios registrados
        this.sistema = sistema;

        // Preparo la ventana principal
        setTitle("FidESPN - Mundial 2026");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel(new BorderLayout(15, 20));
        fondo.setBackground(azul);
        fondo.setBorder(BorderFactory.createEmptyBorder(
                35, 65, 35, 65));

        // Coloco el titulo principal
        JLabel titulo = new JLabel(
                "FIDESPN - MUNDIAL 2026", JLabel.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel(
                "Vivi la emocion del futbol", JLabel.CENTER);

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitulo.setForeground(amarillo);

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 5, 5));
        encabezado.setOpaque(false);
        encabezado.add(titulo);
        encabezado.add(subtitulo);

        // Creo los botones para ingresar al sistema
        JButton btnAdministrador = crearBoton("ADMINISTRADOR");
        JButton btnReportero = crearBoton("REPORTERO DEPORTIVO");
        JButton btnFanatico = crearBoton("FANATICO");

        JPanel botones = new JPanel(new GridLayout(3, 1, 0, 15));
        botones.setOpaque(false);

        botones.add(btnAdministrador);
        botones.add(btnReportero);
        botones.add(btnFanatico);

        // Abro el ingreso del administrador
        btnAdministrador.addActionListener(e -> {
            VentanaLogin login = new VentanaLogin(
                    "Administrador", sistema);
            login.setVisible(true);
        });

        // Abro el ingreso del reportero deportivo
        btnReportero.addActionListener(e -> {
            VentanaLogin login = new VentanaLogin(
                    "Reportero Deportivo", sistema);
            login.setVisible(true);
        });

        // Abro el ingreso del fanatico
        btnFanatico.addActionListener(e -> {
            VentanaLogin login = new VentanaLogin(
                    "Fanatico", sistema);
            login.setVisible(true);
        });

        JLabel pie = new JLabel(
                "MUNDIAL FIFA 2026", JLabel.CENTER);

        pie.setForeground(Color.WHITE);
        pie.setFont(new Font("Arial", Font.BOLD, 12));

        // Organizo los elementos dentro de la ventana
        fondo.add(encabezado, BorderLayout.NORTH);
        fondo.add(botones, BorderLayout.CENTER);
        fondo.add(pie, BorderLayout.SOUTH);

        add(fondo);
    }

    // Uso el mismo estilo para los tres botones
    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setBackground(amarillo);
        boton.setForeground(azul);
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        boton.setBorder(BorderFactory.createLineBorder(
                morado, 2));

        return boton;
    }
}
