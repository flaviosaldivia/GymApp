// src/main/SistemaGimnasioManager.java
package main;

import modelo.*; // Importar todas las clases del paquete modelo
import frontend.WebPageGenerator; // Importa la clase WebPageGenerator
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaGimnasioManager {

    private List<Socio> socios; // Lista para almacenar socios
    private List<Entrenador> entrenadores; // Lista para almacenar entrenadores
    private List<PlanEntrenamiento> planes; // Lista para almacenar planes
    private List<ClaseEntrenamiento> clases; // Lista para almacenar clases
    private Scanner scanner; // Para leer la entrada del usuario

    public SistemaGimnasioManager() {
        this.socios = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.clases = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarDatosBase(); // Cargar algunos datos para probar
    }

    // Inicializar algunos datos para pruebas
    private void inicializarDatosBase() {
        // Entrenadores
        Entrenador entrenador1 = new Entrenador("Pedro", "García", LocalDate.of(1985, 5, 10), "11122233", "pedro.g@email.com", "Musculación");
        Entrenador entrenador2 = new Entrenador("Laura", "Smith", LocalDate.of(1990, 8, 20), "44455566", "laura.s@email.com", "Yoga");
        entrenadores.add(entrenador1);
        entrenadores.add(entrenador2);

        // Planes
        planes.add(new PlanEntrenamiento("PLAN001", "Plan Básico", "Acceso a sala de máquinas", 30.00));
        planes.add(new PlanEntrenamiento("PLAN002", "Plan Premium", "Acceso total a máquinas y clases", 50.00));

        // Clases
        ClaseEntrenamiento clase1 = new ClaseEntrenamiento("CLASE001", "Yoga Matinal", entrenador2, "L/M/V 09:00", 20);
        ClaseEntrenamiento clase2 = new ClaseEntrenamiento("CLASE002", "Spinning Intenso", entrenador1, "M/J 18:00", 15);
        clases.add(clase1);
        clases.add(clase2);

        // Asociar clases a entrenadores
        entrenador1.agregarClaseImpartida(clase2);
        entrenador2.agregarClaseImpartida(clase1);

        // Socios
        Socio socio1 = new Socio("Juan", "Pérez", LocalDate.of(1995, 3, 15), "30123456", "1123456789", "juan.p@email.com");
        Socio socio2 = new Socio("Ana", "Gómez", LocalDate.of(2000, 7, 22), "35987654", "1198765432", "ana.g@email.com");
        socios.add(socio1);
        socios.add(socio2);

        // Asignar algunos planes y clases a socios
        socio1.asignarPlan(planes.get(0)); // Plan Básico
        socio1.asignarClase(clase1);       // Yoga Matinal
        socio2.asignarPlan(planes.get(1)); // Plan Premium
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- Sistema de Gestión de Gimnasios ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Asignar Plan de Entrenamiento a Socio");
            System.out.println("3. Asignar Clase de Entrenamiento a Socio");
            System.out.println("4. Listar Socios");
            System.out.println("5. Listar Entrenadores");
            System.out.println("6. Listar Planes de Entrenamiento");
            System.out.println("7. Listar Clases de Entrenamiento");
            System.out.println("8. Buscar Socio por DNI");
            System.out.println("9. Ordenar Socios por Apellido");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                gestionarOpcionPrincipal(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                opcion = -1; // Para que el bucle continúe
            }
        } while (opcion != 0);
        System.out.println("Saliendo del sistema. ¡Hasta pronto!");
        scanner.close();
    }

    private void gestionarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1:
                mostrarMenuRegistroUsuario();
                break;
            case 2:
                asignarPlanASocio();
                break;
            case 3:
                asignarClaseASocio();
                break;
            case 4:
                listarSocios();
                break;
            case 5:
                listarEntrenadores();
                break;
            case 6:
                listarPlanes();
                break;
            case 7:
                listarClases();
                break;
            case 8:
                buscarSocioPorDNI();
                break;
            case 9:
                ordenarSociosPorApellido();
                break;
            case 0:
                // Salir del bucle principal
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void mostrarMenuRegistroUsuario() {
        int opcion;
        do {
            System.out.println("\n--- Registrar Nuevo Usuario ---");
            System.out.println("1. Registrar Entrenador");
            System.out.println("2. Registrar Socio");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                switch (opcion) {
                    case 1:
                        registrarEntrenador();
                        break;
                    case 2:
                        registrarSocio();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                opcion = -1;
            }
        } while (opcion != 0);
    }

    private void registrarEntrenador() {
        System.out.println("\n--- Registrar Entrenador ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            try {
                fechaNacimiento = LocalDate.parse(fechaStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
            }
        }
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        // Validar DNI único
        if (buscarPersonaPorDNI(dni) != null) {
            System.out.println("Error: Ya existe una persona (socio o entrenador) con este DNI.");
            return;
        }
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Especialización: ");
        String especializacion = scanner.nextLine();

        Entrenador nuevoEntrenador = new Entrenador(nombre, apellido, fechaNacimiento, dni, email, especializacion);
        entrenadores.add(nuevoEntrenador);
        System.out.println("Entrenador '" + nombre + " " + apellido + "' registrado exitosamente.");
    }

    private void registrarSocio() {
        System.out.println("\n--- Registrar Socio ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            try {
                fechaNacimiento = LocalDate.parse(fechaStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
            }
        }
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        // Validar DNI único
        if (buscarPersonaPorDNI(dni) != null) {
            System.out.println("Error: Ya existe una persona (socio o entrenador) con este DNI.");
            return;
        }
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Socio nuevoSocio = new Socio(nombre, apellido, fechaNacimiento, dni, telefono, email);
        socios.add(nuevoSocio);
        System.out.println("Socio '" + nombre + " " + apellido + "' registrado exitosamente.");
    }

    // Método de búsqueda genérico para personas (socios y entrenadores)
    private Persona buscarPersonaPorDNI(String dni) {
        for (Socio s : socios) {
            if (s.getDni().equals(dni)) {
                return s;
            }
        }
        for (Entrenador e : entrenadores) {
            if (e.getDni().equals(dni)) {
                return e;
            }
        }
        return null;
    }

    private Socio buscarSocioPorDNI(String dni) {
        for (Socio s : socios) {
            if (s.getDni().equals(dni)) {
                return s;
            }
        }
        return null;
    }

    private PlanEntrenamiento buscarPlanPorId(String id) {
        for (PlanEntrenamiento p : planes) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private ClaseEntrenamiento buscarClasePorId(String id) {
        for (ClaseEntrenamiento c : clases) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }


    private void asignarPlanASocio() {
        System.out.println("\n--- Asignar Plan de Entrenamiento ---");
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados para asignar planes.");
            return;
        }
        if (planes.isEmpty()) {
            System.out.println("No hay planes de entrenamiento disponibles para asignar.");
            return;
        }

        System.out.print("DNI del Socio: ");
        String dniSocio = scanner.nextLine();
        Socio socio = buscarSocioPorDNI(dniSocio);

        if (socio == null) {
            System.out.println("Socio con DNI " + dniSocio + " no encontrado.");
            return;
        }

        System.out.println("Planes disponibles:");
        listarPlanes();
        System.out.print("ID del Plan a asignar: ");
        String idPlan = scanner.nextLine();
        PlanEntrenamiento plan = buscarPlanPorId(idPlan);

        if (plan == null) {
            System.out.println("Plan con ID " + idPlan + " no encontrado.");
            return;
        }

        // Simular fecha de asignación (en un sistema real se podría pedir al usuario)
        LocalDate fechaAsignacion = LocalDate.now();

        socio.asignarPlan(plan);
        System.out.println("Plan '" + plan.getNombre() + "' asignado a " + socio.getNombre() + " " + socio.getApellido() + " en la fecha " + fechaAsignacion + ".");
    }

    private void asignarClaseASocio() {
        System.out.println("\n--- Asignar Clase de Entrenamiento ---");
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados para asignar clases.");
            return;
        }
        if (clases.isEmpty()) {
            System.out.println("No hay clases de entrenamiento disponibles para asignar.");
            return;
        }

        System.out.print("DNI del Socio: ");
        String dniSocio = scanner.nextLine();
        Socio socio = buscarSocioPorDNI(dniSocio);

        if (socio == null) {
            System.out.println("Socio con DNI " + dniSocio + " no encontrado.");
            return;
        }

        System.out.println("Clases disponibles:");
        listarClases();
        System.out.print("ID de la Clase a asignar: ");
        String idClase = scanner.nextLine();
        ClaseEntrenamiento clase = buscarClasePorId(idClase);

        if (clase == null) {
            System.out.println("Clase con ID " + idClase + " no encontrada.");
            return;
        }

        // Simular fecha y hora de asignación (en un sistema real se pediría)
        LocalDate fechaAsignacion = LocalDate.now();
        // String horaAsignacion = "10:00"; // Podría ser una entrada del usuario

        socio.asignarClase(clase);
        System.out.println("Clase '" + clase.getNombre() + "' asignada a " + socio.getNombre() + " " + socio.getApellido() + " en la fecha " + fechaAsignacion + ".");
    }

    private void listarSocios() {
        System.out.println("\n--- Listado de Socios ---");
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados.");
            return;
        }
        for (Socio s : socios) {
            System.out.println(s.mostrarDetalles()); // Polimorfismo en acción
            System.out.println("--------------------");
        }
    }

    private void listarEntrenadores() {
        System.out.println("\n--- Listado de Entrenadores ---");
        if (entrenadores.isEmpty()) {
            System.out.println("No hay entrenadores registrados.");
            return;
        }
        for (Entrenador e : entrenadores) {
            System.out.println(e.mostrarDetalles()); // Polimorfismo en acción
            System.out.println("--------------------");
        }
    }

    private void listarPlanes() {
        System.out.println("\n--- Listado de Planes de Entrenamiento ---");
        if (planes.isEmpty()) {
            System.out.println("No hay planes de entrenamiento registrados.");
            return;
        }
        for (PlanEntrenamiento p : planes) {
            System.out.println(p.toString());
        }
    }

    private void listarClases() {
        System.out.println("\n--- Listado de Clases de Entrenamiento ---");
        if (clases.isEmpty()) {
            System.out.println("No hay clases de entrenamiento registradas.");
            return;
        }
        for (ClaseEntrenamiento c : clases) {
            System.out.println(c.toString());
        }
    }

    // Búsqueda de socio por DNI
    private void buscarSocioPorDNI() {
        System.out.println("\n--- Buscar Socio por DNI ---");
        System.out.print("Ingrese el DNI del socio a buscar: ");
        String dni = scanner.nextLine();
        Socio socioEncontrado = buscarSocioPorDNI(dni); // Reutiliza el método de búsqueda

        if (socioEncontrado != null) {
            System.out.println("Socio encontrado:");
            System.out.println(socioEncontrado.mostrarDetalles());
        } else {
            System.out.println("Socio con DNI " + dni + " no encontrado.");
        }
    }

    // Ordenación de socios por apellido
    private void ordenarSociosPorApellido() {
        if (socios.isEmpty()) {
            System.out.println("No hay socios para ordenar.");
            return;
        }
        System.out.println("\n--- Ordenando Socios por Apellido ---");
        // Utilizando Collections.sort con un Comparator lambda para ordenar por apellido
        Collections.sort(socios, Comparator.comparing(Socio::getApellido));
        System.out.println("Socios ordenados por apellido:");
        listarSocios();
    }


    public static void main(String[] args) {
        // Generación de la interfaz web (frontend estático)
        WebPageGenerator.main(new String[]{}); // Llama al main del generador de páginas web

        // Inicia la aplicación de gestión en consola (backend simulado)
        System.out.println("\n\n--- Iniciando Sistema de Gestión de Gimnasio (Consola) ---");
        SistemaGimnasioManager app = new SistemaGimnasioManager();
        app.mostrarMenuPrincipal();
    }
}