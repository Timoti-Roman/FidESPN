
package fidespn;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private String perfil;
    private SistemaFidESPN sistema;

    public VentanaLogin(String perfil, SistemaFidESPN sistema) {

        // Guardo el perfil seleccionado y el sistema
        this.perfil = perfil;
        this.sistema = sistema;

        setTitle("FidESPN - Inicio de sesion");
        setSize(420, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        Color azul = new Color(0, 32, 96);
        Color amarillo = new Color(255, 192, 0);
        Color morado = new Color(112, 48, 160);

        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 8));
        panel.setBackground(azul);
        panel.setBorder(BorderFactory.createEmptyBorder(
                20, 40, 20, 40));

        JLabel titulo = new JLabel(
                "INGRESO - " + perfil, JLabel.CENTER);

        titulo.setForeground(amarillo);
        titulo.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel lblCorreo = new JLabel("Correo electronico:");
        lblCorreo.setForeground(Color.WHITE);

        txtCorreo = new JTextField();

        JLabel lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setForeground(Color.WHITE);

        txtContrasena = new JPasswordField();

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(amarillo);
        btnIngresar.setForeground(azul);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorder(BorderFactory.createLineBorder(
                morado, 2));

        btnIngresar.addActionListener(e -> iniciarSesion());

        panel.add(titulo);
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(btnIngresar);

        add(panel);
    }

    // Compruebo el correo, la contrasena y el perfil
    private void iniciarSesion() {

        String correo = txtCorreo.getText().trim();
        String contrasena = new String(
                txtContrasena.getPassword());

        if (correo.isEmpty() || contrasena.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this, "Debes completar todos los campos");
            return;
        }

        // Busco al usuario registrado
        Usuario usuario = sistema.iniciarSesion(
                correo, contrasena);

        if (usuario == null) {

            JOptionPane.showMessageDialog(
                    this, "Correo o contrasena incorrectos");
            return;
        }

        // Compruebo que ingrese desde el perfil correcto
        boolean perfilCorrecto = false;

        if (perfil.equals("Administrador")
                && usuario instanceof Administrador) {
            perfilCorrecto = true;
        }

        if (perfil.equals("Reportero Deportivo")
                && usuario instanceof ReporteroDeportivo) {
            perfilCorrecto = true;
        }

        if (perfil.equals("Fanatico")
                && usuario instanceof Fanatico) {
            perfilCorrecto = true;
        }

        if (!perfilCorrecto) {

            JOptionPane.showMessageDialog(
                    this, "Este usuario no pertenece al perfil seleccionado");
            return;
        }

        JOptionPane.showMessageDialog(
                this, "Bienvenido a FidESPN, "
                + usuario.getNombre());

        // Abro la ventana correspondiente al usuario
        if (usuario instanceof Administrador) {

            VentanaAdministrador ventana =
                    new VentanaAdministrador(sistema);

            ventana.setVisible(true);

        } else if (usuario instanceof ReporteroDeportivo) {

            VentanaReportero ventana =
                    new VentanaReportero(sistema);

            ventana.setVisible(true);

        } else if (usuario instanceof Fanatico) {

            VentanaFanatico ventana =
                    new VentanaFanatico(
                            sistema, (Fanatico) usuario);

            ventana.setVisible(true);
        }

        dispose();
    }
}
