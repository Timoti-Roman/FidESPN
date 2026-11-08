
package fidespn;

public class EventoPartido {

    private int id;
    private Partido partido;
    private String tipo;
    private int minuto;
    private String equipo;
    private String descripcion;

    // Guardo lo que sucedio durante el partido
    public EventoPartido(int id, Partido partido, String tipo,
                         int minuto, String equipo,
                         String descripcion) {

        this.id = id;
        this.partido = partido;
        this.tipo = tipo;
        this.minuto = minuto;
        this.equipo = equipo;
        this.descripcion = descripcion;
    }

    // Mantengo este constructor para no afectar otras clases
    public EventoPartido(int id, Partido partido, String tipo,
                         int minuto, String descripcion) {

        this(id, partido, tipo, minuto, "Sin especificar",
                descripcion);
    }

    public int getId() {
        return id;
    }

    public Partido getPartido() {
        return partido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Muestro los datos del acontecimiento registrado
    public String mostrarInformacion() {

        return "Minuto " + minuto
                + " - " + tipo
                + " - " + equipo
                + ": " + descripcion;
    }
}
