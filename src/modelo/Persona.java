// src/modelo/Persona.java
package modelo;

import java.time.LocalDate;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String email;
    protected LocalDate fechaNacimiento; // Usar LocalDate para fechas

    public Persona(String nombre, String apellido, String dni, String email, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    // Setters (opcionales, dependiendo de si se permite modificar después de la creación)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método abstracto que será implementado por las subclases (Polimorfismo)
    public abstract String mostrarDetalles();

    // Sobrescritura de toString para una representación básica
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", DNI: " + dni;
    }
}