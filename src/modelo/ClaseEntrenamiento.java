// src/modelo/ClaseEntrenamiento.java
package modelo;

public class ClaseEntrenamiento {
    private String id;
    private String nombre;
    private Entrenador entrenador; // Relación con Entrenador
    private String horario; // Ej: "L/M/V 09:00"
    private int capacidad;

    public ClaseEntrenamiento(String id, String nombre, Entrenador entrenador, String horario, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.horario = horario;
        this.capacidad = capacidad;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    // Setters (si se permite modificar)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Entrenador: " + entrenador.getNombre() + " " + entrenador.getApellido() + ", Horario: " + horario;
    }
}