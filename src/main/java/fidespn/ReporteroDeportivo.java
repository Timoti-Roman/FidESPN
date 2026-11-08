
package fidespn;

public class ReporteroDeportivo extends Usuario {

    // Guardo los datos del reportero deportivo
    public ReporteroDeportivo(int id, String nombre, String correo,
                              String contrasena) {
        super(id, nombre, correo, contrasena);
    }

    // Muestro las funciones que tiene el reportero
    @Override
    public String mostrarFunciones() {
        return "Funciones del reportero deportivo:\n"
                + "- Consultar partidos asignados\n"
                + "- Registrar goles y eventos del partido\n"
                + "- Actualizar resultados";
    }
}
