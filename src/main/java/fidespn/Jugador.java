
package fidespn;

public class Jugador {

    private int id;
    private String nombre;
    private int numero;
    private String posicion;
    private Equipo equipo;

    // Guardo los datos del jugador y su equipo
    public Jugador(int id, String nombre, int numero,
                   String posicion, Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.posicion = posicion;
        this.equipo = equipo;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    // Muestro la informacion principal del jugador
    public String mostrarInformacion() {
        return numero + " - " + nombre + " - "
                + posicion + " - " + equipo.getNombre();
    }
}
