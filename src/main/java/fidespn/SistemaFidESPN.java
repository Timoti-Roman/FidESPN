
package fidespn;

import java.util.ArrayList;

public class SistemaFidESPN {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;
    private ArrayList<EventoPartido> eventos;
    private ArrayList<MensajeChat> mensajes;

    // Preparo las listas donde voy a guardar la informacion
    public SistemaFidESPN() {
        usuarios = new ArrayList<>();
        equipos = new ArrayList<>();
        partidos = new ArrayList<>();
        eventos = new ArrayList<>();
        mensajes = new ArrayList<>();
    }

    // Agrego un usuario al sistema
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Agrego una seleccion al sistema
    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    // Registro un partido
    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    // Guardo un evento que sucedio en un partido
    public void agregarEvento(EventoPartido evento) {
        eventos.add(evento);
    }

    // Guardo un mensaje del chat
    public void agregarMensaje(MensajeChat mensaje) {
        mensajes.add(mensaje);
    }

    // Busco un partido por su numero de identificacion
    public Partido buscarPartido(int id) {
        for (Partido partido : partidos) {
            if (partido.getId() == id) {
                return partido;
            }
        }
        return null;
    }

    // Busco al usuario por su correo y contrasena
    public Usuario iniciarSesion(String correo, String contrasena) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCorreo().equalsIgnoreCase(correo)
                    && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }

        return null;
    }

    // Devuelvo los partidos registrados
    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    // Devuelvo las selecciones registradas
    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    // Devuelvo los usuarios registrados
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    // Devuelvo los eventos registrados
    public ArrayList<EventoPartido> getEventos() {
        return eventos;
    }

    // Devuelvo los mensajes del chat
    public ArrayList<MensajeChat> getMensajes() {
        return mensajes;
    }
}
