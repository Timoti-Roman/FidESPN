
package fidespn;

public class EventoPartido {

    private int id;
    private Partido partido;
    private String tipo;
    private int minuto;
    private String descripcion;

    // Guardo lo que sucedio durante el partido
    public EventoPartido(int id, Partido partido, String tipo,
                         int minuto, String descripcion) {
        this.id = id;
        this.partido = partido;
        this.tipo = tipo;
        this.minuto = minuto;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Muestro el evento registrado en el partido
    public String mostrarInformacion() {
        return "Minuto " + minuto + " - " + tipo
                + ": " + descripcion;
    }
}
