// src/modelo/Socio.java
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Socio extends Persona {
    private String telefono;
    private List<PlanEntrenamiento> planesAsignados; // Relación con Plan
    private List<ClaseEntrenamiento> clasesAsignadas; // Relación con Clase

    public Socio(String nombre, String apellido, LocalDate fechaNacimiento, String dni, String telefono, String email) {
        super(nombre, apellido, dni, email, fechaNacimiento);
        this.telefono = telefono;
        this.planesAsignados = new ArrayList<>();
        this.clasesAsignadas = new ArrayList<>();
    }

    // Getters
    public String getTelefono() {
        return telefono;
    }

    public List<PlanEntrenamiento> getPlanesAsignados() {
        return planesAsignados;
    }

    public List<ClaseEntrenamiento> getClasesAsignadas() {
        return clasesAsignadas;
    }

    // Setters
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Métodos para asignar planes y clases
    public void asignarPlan(PlanEntrenamiento plan) {
        if (plan != null && !planesAsignados.contains(plan)) {
            this.planesAsignados.add(plan);
            System.out.println("Plan '" + plan.getNombre() + "' asignado a socio " + this.getNombre() + " " + this.getApellido());
        } else if (plan != null) {
            System.out.println("El plan '" + plan.getNombre() + "' ya está asignado a este socio.");
        }
    }

    public void asignarClase(ClaseEntrenamiento clase) {
        if (clase != null && !clasesAsignadas.contains(clase)) {
            this.clasesAsignadas.add(clase);
            System.out.println("Clase '" + clase.getNombre() + "' asignada a socio " + this.getNombre() + " " + this.getApellido());
        } else if (clase != null) {
            System.out.println("La clase '" + clase.getNombre() + "' ya está asignada a este socio.");
        }
    }

    // Implementación del método abstracto de Persona (Polimorfismo)
    @Override
    public String mostrarDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Detalle del Socio ---\n");
        sb.append("ID: ").append(getDni()).append("\n"); // Usamos DNI como ID único
        sb.append("Nombre Completo: ").append(getNombre()).append(" ").append(getApellido()).append("\n");
        sb.append("Fecha Nacimiento: ").append(getFechaNacimiento()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Teléfono: ").append(getTelefono()).append("\n");

        sb.append("Planes Asignados:\n");
        if (planesAsignados.isEmpty()) {
            sb.append("  (Ningún plan asignado)\n");
        } else {
            for (PlanEntrenamiento plan : planesAsignados) {
                sb.append("  - ").append(plan.getNombre()).append(" (").append(plan.getDescripcion()).append(")\n");
            }
        }

        sb.append("Clases Asignadas:\n");
        if (clasesAsignadas.isEmpty()) {
            sb.append("  (Ninguna clase asignada)\n");
        } else {
            for (ClaseEntrenamiento clase : clasesAsignadas) {
                sb.append("  - ").append(clase.getNombre()).append(" con ").append(clase.getEntrenador().getNombre()).append(" (")
                  .append(clase.getHorario()).append(")\n");
            }
        }
        return sb.toString();
    }
}