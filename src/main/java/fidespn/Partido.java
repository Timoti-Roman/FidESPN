
package fidespn;

public class Partido {

    private int id;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String fecha;
    private String estadio;
    private int golesLocal;
    private int golesVisitante;
    private String estado;
    private ReporteroDeportivo reporteroAsignado;

    // Guardo los datos principales del partido
    public Partido(int id, Equipo equipoLocal,
                   Equipo equipoVisitante, String fecha,
                   String estadio) {
        this.id = id;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.estado = "Programado";
        this.reporteroAsignado = null;
    }

    public int getId() {
        return id;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public String getEstado() {
        return estado;
    }

    public ReporteroDeportivo getReporteroAsignado() {
        return reporteroAsignado;
    }

    // Asigno el reportero que va a cubrir el partido
    public void asignarReportero(ReporteroDeportivo reportero) {
        this.reporteroAsignado = reportero;
    }

    // Actualizo el marcador del partido
    public void actualizarMarcador(int golesLocal,
                                  int golesVisitante) {
        if (golesLocal >= 0 && golesVisitante >= 0) {
            this.golesLocal = golesLocal;
            this.golesVisitante = golesVisitante;
        }
    }

    // Cambio el estado del partido
    public void cambiarEstado(String estado) {
        this.estado = estado;
    }

    // Muestro el resultado de los dos equipos
    public String mostrarResultado() {
        return equipoLocal.getNombre() + " "
                + golesLocal + " - " + golesVisitante
                + " " + equipoVisitante.getNombre();
    }

    // Muestro los datos del partido
    public String mostrarInformacion() {

        String nombreReportero = "Sin asignar";

        if (reporteroAsignado != null) {
            nombreReportero = reporteroAsignado.getNombre();
        }

        return mostrarResultado()
                + "\nFecha: " + fecha
                + "\nEstadio: " + estadio
                + "\nEstado: " + estado
                + "\nReportero: " + nombreReportero;
    }
}
