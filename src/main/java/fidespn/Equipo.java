
package fidespn;

public class Equipo {

    private int id;
    private String nombre;
    private String grupo;

    // Guardo los datos de cada seleccion
    public Equipo(int id, String nombre, String grupo) {
        this.id = id;
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    // Muestro los datos principales del equipo
    public String mostrarInformacion() {
        return nombre + " - Grupo " + grupo;
    }
}
