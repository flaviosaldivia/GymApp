// src/modelo/Entrenador.java
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Entrenador extends Persona {
    private String especializacion;
    private List<ClaseEntrenamiento> clasesImpartidas; // Clases que imparte el entrenador

    public Entrenador(String nombre, String apellido, LocalDate fechaNacimiento, String dni, String email, String especializacion) {
        super(nombre, apellido, dni, email, fechaNacimiento);
        this.especializacion = especializacion;
        this.clasesImpartidas = new ArrayList<>();
    }

    // Getters
    public String getEspecializacion() {
        return especializacion;
    }

    public List<ClaseEntrenamiento> getClasesImpartidas() {
        return clasesImpartidas;
    }

    // Setters
    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    // Método para agregar clases impartidas por el entrenador
    public void agregarClaseImpartida(ClaseEntrenamiento clase) {
        if (clase != null && !clasesImpartidas.contains(clase)) {
            this.clasesImpartidas.add(clase);
            System.out.println("Clase '" + clase.getNombre() + "' añadida a la lista de clases impartidas por " + this.getNombre());
        } else if (clase != null) {
            System.out.println("La clase '" + clase.getNombre() + "' ya está en la lista de clases impartidas por este entrenador.");
        }
    }

    // Implementación del método abstracto de Persona (Polimorfismo)
    @Override
    public String mostrarDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Detalle del Entrenador ---\n");
        sb.append("ID: ").append(getDni()).append("\n"); // Usamos DNI como ID único
        sb.append("Nombre Completo: ").append(getNombre()).append(" ").append(getApellido()).append("\n");
        sb.append("Fecha Nacimiento: ").append(getFechaNacimiento()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Especialización: ").append(getEspecializacion()).append("\n");
        sb.append("Clases que Imparte:\n");
        if (clasesImpartidas.isEmpty()) {
            sb.append("  (Ninguna clase asignada)\n");
        } else {
            for (ClaseEntrenamiento clase : clasesImpartidas) {
                sb.append("  - ").append(clase.getNombre()).append(" (Horario: ").append(clase.getHorario()).append(")\n");
            }
        }
        return sb.toString();
    }
}