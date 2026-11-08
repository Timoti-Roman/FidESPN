
package fidespn;

public class Administrador extends Usuario {

    public Administrador(int id, String nombre,
                         String correo, String contrasena) {

        super(id, nombre, correo, contrasena);
    }

    // Muestro las funciones que puedo realizar como administrador
    @Override
    public String mostrarFunciones() {

        return "Funciones del administrador:\n"
                + "- Administrar usuarios\n"
                + "- Registrar equipos\n"
                + "- Registrar partidos\n"
                + "- Consultar partidos";
    }
}
