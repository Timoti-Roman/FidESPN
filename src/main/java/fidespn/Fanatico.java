
package fidespn;

public class Fanatico extends Usuario {

    // Guardo los datos del fanatico
    public Fanatico(int id, String nombre, String correo,
                    String contrasena) {
        super(id, nombre, correo, contrasena);
    }

    // Muestro lo que puede hacer el fanatico en el sistema
    @Override
    public String mostrarFunciones() {
        return "Funciones del fanatico:\n"
                + "- Consultar partidos del Mundial 2026\n"
                + "- Ver resultados y eventos de los partidos\n"
                + "- Participar en el chat de aficionados";
    }
}
