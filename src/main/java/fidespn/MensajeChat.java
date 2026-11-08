
package fidespn;

public class MensajeChat {

    private int id;
    private Fanatico fanatico;
    private Partido partido;
    private String mensaje;
    private String fechaHora;

    // Guardo el mensaje que envia un aficionado
    public MensajeChat(int id, Fanatico fanatico, Partido partido,
                       String mensaje, String fechaHora) {
        this.id = id;
        this.fanatico = fanatico;
        this.partido = partido;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public Fanatico getFanatico() {
        return fanatico;
    }

    public Partido getPartido() {
        return partido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    // Muestro quien envio el mensaje y lo que escribio
    public String mostrarMensaje() {
        return "[" + fechaHora + "] "
                + fanatico.getNombre() + ": " + mensaje;
    }
}
