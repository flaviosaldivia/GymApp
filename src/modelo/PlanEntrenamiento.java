// src/modelo/PlanEntrenamiento.java
package modelo;

public class PlanEntrenamiento {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;

    public PlanEntrenamiento(String id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters (si se permite modificar)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: $" + String.format("%.2f", precio);
    }
}