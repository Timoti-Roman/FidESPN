
package fidespn;

public class FidESPN {

    public static void main(String[] args) {

        // Inicio el sistema donde voy a guardar los usuarios
        SistemaFidESPN sistema = new SistemaFidESPN();

        // Registro un usuario de prueba para cada perfil
        Administrador administrador = new Administrador(
                1, "Administrador", "admin@fidespn.com", "1234");

        ReporteroDeportivo reportero = new ReporteroDeportivo(
                2, "Reportero", "reportero@fidespn.com", "1234");

        Fanatico fanatico = new Fanatico(
                3, "Aficionado", "fanatico@fidespn.com", "1234");

        sistema.agregarUsuario(administrador);
        sistema.agregarUsuario(reportero);
        sistema.agregarUsuario(fanatico);

        // Abro la ventana principal con los usuarios registrados
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        ventana.setVisible(true);
    }
}
