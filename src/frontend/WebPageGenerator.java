// src/frontend/WebPageGenerator.java
package frontend; // Nuevo paquete para separar el generador de UI

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WebPageGenerator {

    private static final String OUTPUT_DIR = "generated_webpages/";
    private static final String IMAGE_DIR = OUTPUT_DIR + "images/"; // Directorio para las imágenes

    public static void main(String[] args) {
        // Crear los directorios de salida si no existen
        try {
            Files.createDirectories(Paths.get(OUTPUT_DIR));
            Files.createDirectories(Paths.get(IMAGE_DIR));
        } catch (IOException e) {
            System.err.println("Error al crear directorios: " + e.getMessage());
            return; // Salir si no se pueden crear los directorios
        }

        System.out.println("Asegúrate de colocar las imágenes en la carpeta: " + IMAGE_DIR);
        System.out.println("Las imágenes sugeridas son: gym_bg.jpg, trainer_icon.png, member_icon.png, ");
        System.out.println("trainer_form_img.jpg, member_form_img.jpg, plan_form_img.jpg, class_form_img.jpg.");


        // Generar el archivo CSS
        generateCSS();

        // Generar las páginas HTML
        generateIndexPage();
        generateRegisterChoicePage();
        generateRegisterTrainerPage();
        generateRegisterMemberPage();
        generateAssignPlanPage();
        generateAssignClassPage();

        System.out.println("\n--- Proceso Completado (Generación de Páginas Web Estáticas) ---");
        System.out.println("Páginas web y CSS generados en el directorio: " + OUTPUT_DIR);
        System.out.println("Abre los archivos HTML en tu navegador para ver el resultado.");
        System.out.println("Recuerda que debes copiar las imágenes manualmente en la carpeta 'images' dentro de 'generated_webpages'.");
    }

    private static void generateCSS() {
        String cssContent = """
                :root {
                    --primary-color: #007bff; /* Azul vibrante */
                    --secondary-color: #6c757d; /* Gris oscuro */
                    --accent-color: #28a745; /* Verde para acciones */
                    --background-light: #f8f9fa; /* Fondo claro */
                    --text-color: #343a40; /* Texto oscuro */
                    --border-color: #dee2e6; /* Borde claro */
                    --card-bg: #ffffff; /* Fondo de tarjetas */
                    --shadow-light: rgba(0, 0, 0, 0.1);
                    --shadow-medium: rgba(0, 0, 0, 0.15);
                }

                body {
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: var(--background-light);
                    color: var(--text-color);
                    line-height: 1.6;
                }

                .container {
                    max-width: 1200px;
                    margin: 0 auto;
                    padding: 20px;
                }

                h1, h2, h3 {
                    color: var(--primary-color);
                    margin-bottom: 20px;
                }

                a {
                    color: var(--primary-color);
                    text-decoration: none;
                }

                a:hover {
                    text-decoration: underline;
                }

                button {
                    background-color: var(--primary-color);
                    color: white;
                    padding: 12px 25px;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    font-size: 1rem;
                    transition: background-color 0.3s ease;
                }

                button:hover {
                    background-color: #0056b3; /* Un poco más oscuro */
                }

                .btn-secondary {
                    background-color: var(--secondary-color);
                }

                .btn-secondary:hover {
                    background-color: #5a6268;
                }

                .btn-accent {
                    background-color: var(--accent-color);
                }

                .btn-accent:hover {
                    background-color: #218838;
                }

                /* --- Page 1: Landing/Login Page --- */
                .landing-page {
                    position: relative;
                    min-height: 100vh;
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    align-items: center;
                    color: white;
                    text-align: center;
                    overflow: hidden;
                    padding: 20px; /* Añadido padding para evitar que el contenido toque los bordes */
                    box-sizing: border-box; /* Incluye padding en el ancho/alto total */
                }

                .landing-bg {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background: url('images/gym_bg.jpg') no-repeat center center/cover;
                    filter: brightness(0.6); /* Oscurecer la imagen de fondo */
                    z-index: -1;
                }

                .landing-content {
                    z-index: 1;
                    max-width: 900px; /* Aumentado el ancho máximo */
                    padding: 40px; /* Aumentado el padding */
                    background: rgba(0, 0, 0, 0.5); /* Fondo semi-transparente más oscuro */
                    border-radius: 10px;
                    box-shadow: 0 8px 25px var(--shadow-medium); /* Sombra más pronunciada */
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                }

                .landing-page .logo {
                    font-size: 4rem; /* Aumentado tamaño del logo */
                    font-weight: bold;
                    margin-bottom: 20px; /* Más espacio */
                    color: var(--primary-color); /* Color del logo */
                    text-shadow: 3px 3px 6px rgba(0,0,0,0.6);
                }

                .landing-page .tagline {
                    font-size: 2rem; /* Aumentado tamaño de tagline */
                    margin-bottom: 30px;
                }

                .landing-page h1 {
                    font-size: 3.5rem; /* Aumentado tamaño del título */
                    margin-bottom: 15px;
                    color: white; /* Título principal en blanco */
                }

                .landing-page p {
                    font-size: 1.3rem; /* Aumentado tamaño del subtítulo */
                    margin-bottom: 50px; /* Más espacio antes de los botones */
                    max-width: 700px;
                }

                .landing-page .top-actions {
                    display: flex;
                    justify-content: center;
                    margin-bottom: 20px; /* Espacio antes de los botones de abajo */
                    flex-wrap: wrap;
                }

                .landing-page .top-actions button {
                    margin: 0 20px; /* Más espacio entre botones */
                    padding: 18px 45px; /* Botones más grandes */
                    font-size: 1.3rem; /* Fuente más grande */
                    border-radius: 8px;
                }

                .landing-page .bottom-actions {
                    margin-top: 40px; /* Espacio superior para los botones de abajo */
                    display: flex;
                    flex-wrap: wrap; /* Permitir que los botones se envuelvan */
                    justify-content: center;
                    gap: 20px; /* Espacio entre los botones */
                }

                .landing-page .bottom-actions button {
                    padding: 15px 35px;
                    font-size: 1.1rem;
                    background-color: var(--secondary-color); /* Color diferente para destacar */
                }

                .landing-page .bottom-actions button:hover {
                    background-color: #5a6268;
                }


                /* --- Back Button (Common) --- */
                .back-button-container {
                    padding: 20px 40px;
                    text-align: left;
                    background-color: var(--card-bg);
                    border-bottom: 1px solid var(--border-color);
                    box-shadow: 0 2px 5px rgba(0,0,0,0.05); /* Sombra ligera */
                }
                .back-button {
                    background-color: var(--secondary-color);
                    padding: 10px 20px;
                    border-radius: 5px;
                    color: white;
                    text-decoration: none;
                    transition: background-color 0.3s ease;
                    display: inline-block; /* Para que sea un botón de verdad */
                }
                .back-button:hover {
                    background-color: #5a6268;
                    text-decoration: none; /* Quitar el subrayado al pasar el ratón */
                }


                /* --- Page 2: Register Choice --- */
                .register-choice-page {
                    text-align: center;
                    padding: 40px 20px;
                }

                .card-grid {
                    display: flex;
                    justify-content: center;
                    flex-wrap: wrap;
                    gap: 30px;
                    margin-top: 50px;
                }

                .card {
                    background-color: var(--card-bg);
                    border-radius: 10px;
                    box-shadow: 0 5px 15px var(--shadow-light);
                    padding: 30px;
                    text-align: center;
                    width: 300px;
                    transition: transform 0.3s ease, box-shadow 0.3s ease;
                }

                .card:hover {
                    transform: translateY(-5px);
                    box-shadow: 0 8px 20px var(--shadow-medium);
                }

                .card img {
                    width: 80px;
                    height: 80px;
                    margin-bottom: 20px;
                    border-radius: 50%;
                    background-color: var(--background-light);
                    padding: 10px;
                    object-fit: contain; /* Asegura que la imagen se ajuste bien */
                }

                .card h3 {
                    margin-bottom: 15px;
                    color: var(--text-color);
                }

                .card p {
                    font-size: 0.95rem;
                    color: var(--secondary-color);
                    margin-bottom: 25px;
                }

                .card button {
                    width: 100%;
                    padding: 12px 0;
                    background-color: var(--accent-color);
                }

                .card button:hover {
                    background-color: #218838;
                }

                /* --- Form Pages (Common: Page 3, 4, 5, 6) --- */
                .form-page {
                    display: flex;
                    justify-content: center;
                    align-items: flex-start; /* Alinea los ítems al inicio del contenedor flex */
                    padding: 40px 20px;
                    gap: 40px; /* Espacio entre el formulario y la imagen */
                    flex-wrap: wrap; /* Permite que los elementos se envuelvan en pantallas pequeñas */
                }

                .form-section {
                    flex: 1; /* Permite que la sección del formulario crezca */
                    min-width: 350px; /* Ancho mínimo para el formulario */
                    background-color: var(--card-bg);
                    padding: 40px;
                    border-radius: 10px;
                    box-shadow: 0 5px 15px var(--shadow-light);
                }

                .form-section h1 {
                    margin-bottom: 30px;
                    text-align: center;
                }

                .form-group {
                    margin-bottom: 20px;
                }

                .form-group label {
                    display: block;
                    margin-bottom: 8px;
                    font-weight: bold;
                    color: var(--secondary-color);
                }

                .form-group input[type="text"],
                .form-group input[type="email"],
                .form-group input[type="date"],
                .form-group input[type="tel"],
                .form-group select {
                    width: calc(100% - 22px); /* Ancho completo menos padding y borde */
                    padding: 12px; /* Aumentado padding */
                    border: 1px solid var(--border-color);
                    border-radius: 5px;
                    font-size: 1rem;
                    box-sizing: border-box; /* Incluir padding y border en el width */
                }

                .form-section button {
                    width: 100%;
                    padding: 15px;
                    margin-top: 30px;
                }

                .image-section {
                    flex: 1; /* Permite que la sección de la imagen crezca */
                    min-width: 300px; /* Ancho mínimo para la imagen */
                    text-align: center;
                }

                .image-section img {
                    max-width: 100%;
                    height: auto;
                    border-radius: 10px;
                    box-shadow: 0 5px 15px var(--shadow-light);
                }

                /* Responsive Adjustments */
                @media (max-width: 992px) {
                    .landing-page .landing-content {
                        max-width: 700px;
                    }
                    .landing-page .logo {
                        font-size: 3.5rem;
                    }
                    .landing-page .tagline {
                        font-size: 1.7rem;
                    }
                    .landing-page h1 {
                        font-size: 2.8rem;
                    }
                    .landing-page p {
                        font-size: 1.1rem;
                    }
                }


                @media (max-width: 768px) {
                    .landing-page .landing-content {
                        padding: 30px;
                    }
                    .landing-page .logo {
                        font-size: 2.8rem;
                    }
                    .landing-page .tagline {
                        font-size: 1.4rem;
                    }
                    .landing-page h1 {
                        font-size: 2.2rem;
                    }
                    .landing-page p {
                        font-size: 1rem;
                        margin-bottom: 30px;
                    }
                    .landing-page .top-actions button {
                        padding: 14px 35px;
                        font-size: 1.1rem;
                        margin: 10px; /* Ajuste para evitar desbordamiento */
                    }
                     .landing-page .bottom-actions {
                        flex-direction: column; /* Apila los botones de abajo */
                        align-items: center;
                        margin-top: 30px;
                        gap: 15px;
                    }
                    .landing-page .bottom-actions button {
                         width: 80%; /* Ocupa más ancho */
                         max-width: 300px; /* Ancho máximo para los botones apilados */
                    }

                    .form-page {
                        flex-direction: column;
                        align-items: center;
                        gap: 30px;
                    }

                    .form-section, .image-section {
                        width: 90%;
                        min-width: unset; /* Quitar ancho mínimo en móviles */
                    }

                    .form-section {
                        padding: 30px;
                    }
                }

                @media (max-width: 480px) {
                    .landing-page .logo {
                        font-size: 2.2rem;
                    }
                    .landing-page h1 {
                        font-size: 1.8rem;
                    }
                    .landing-page .top-actions {
                        flex-direction: column;
                        gap: 15px;
                    }
                     .landing-page .top-actions button {
                         width: 80%;
                         max-width: 250px;
                    }
                    .card {
                        width: 90%;
                        padding: 25px;
                    }
                    .form-section {
                        padding: 20px;
                    }
                }
                """;
        try (PrintWriter out = new PrintWriter(OUTPUT_DIR + "style.css")) {
            out.println(cssContent);
        } catch (IOException e) {
            System.err.println("Error al generar style.css: " + e.getMessage());
        }
    }

    private static void generateIndexPage() {
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Gimnasio - ¡Organiza tu Gimnasio!</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <div class="landing-page">
                        <div class="landing-bg"></div>
                        <div class="landing-content">
                            <div class="logo">GYM APP</div>
                            <p class="tagline">¡Organiza tu Gimnasio!</p>
                            <h1>Sistema para la Gestión de Entrenamientos en Gimnasios</h1>
                            <p>Plataforma para optimizar la administración de socios y entrenamientos.</p>
                            <div class="top-actions">
                                <a href="register_choice.html"><button class="btn-accent">Registrar</button></a>
                                <a href="#"><button>Ingresar</button></a> </div>
                            <div class="bottom-actions">
                                <a href="assign_plan.html"><button>Asignar Plan de Entrenamiento</button></a>
                                <a href="assign_class.html"><button>Asignar Clase de Entrenamiento</button></a>
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """;
        writeHtmlFile("index.html", htmlContent);
    }

    private static void generateRegisterChoicePage() {
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Elegir Tipo de Usuario - Gimnasio</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <div class="back-button-container">
                        <a href="index.html" class="back-button">Volver</a>
                    </div>
                    <div class="register-choice-page container">
                        <h1>Elige qué tipo de usuario deseas registrar</h1>
                        <div class="card-grid">
                            <div class="card">
                                <img src="images/trainer_icon.png" alt="Icono Entrenador">
                                <h3>Entrenador</h3>
                                <p>Registra un nuevo entrenador para tu gimnasio. Podrá gestionar clases y rutinas.</p>
                                <a href="register_trainer.html"><button class="btn-accent">Agregar nuevo Entrenador</button></a>
                            </div>
                            <div class="card">
                                <img src="images/member_icon.png" alt="Icono Socio">
                                <h3>Socio</h3>
                                <p>Inscribe a un nuevo socio. Asigna planes y clases personalizadas.</p>
                                <a href="register_member.html"><button class="btn-accent">Agregar nuevo Socio</button></a>
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """;
        writeHtmlFile("register_choice.html", htmlContent);
    }

    private static void generateRegisterTrainerPage() {
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Registrar Entrenador - Gimnasio</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <div class="back-button-container">
                        <a href="register_choice.html" class="back-button">Volver</a>
                    </div>
                    <div class="form-page container">
                        <div class="form-section">
                            <h1>Registrar Entrenador</h1>
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="nombre_trainer">Nombre</label>
                                    <input type="text" id="nombre_trainer" name="nombre_trainer" required>
                                </div>
                                <div class="form-group">
                                    <label for="apellido_trainer">Apellido</label>
                                    <input type="text" id="apellido_trainer" name="apellido_trainer" required>
                                </div>
                                <div class="form-group">
                                    <label for="especializacion">Especialización</label>
                                    <input type="text" id="especializacion" name="especializacion" placeholder="Ej: Musculación, Yoga, CrossFit" required>
                                </div>
                                <div class="form-group">
                                    <label for="fecha_nacimiento_trainer">Fecha de Nacimiento</label>
                                    <input type="date" id="fecha_nacimiento_trainer" name="fecha_nacimiento_trainer" required>
                                </div>
                                <div class="form-group">
                                    <label for="dni_trainer">DNI</label>
                                    <input type="text" id="dni_trainer" name="dni_trainer" required>
                                </div>
                                <div class="form-group">
                                    <label for="email_trainer">E-mail</label>
                                    <input type="email" id="email_trainer" name="email_trainer" required>
                                </div>
                                <button type="submit" class="btn-accent">Agregar Entrenador</button>
                            </form>
                        </div>
                        <div class="image-section">
                            <img src="images/trainer_form_img.jpg" alt="Imagen Entrenador">
                        </div>
                    </div>
                </body>
                </html>
                """;
        writeHtmlFile("register_trainer.html", htmlContent);
    }

    private static void generateRegisterMemberPage() {
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Registrar Socio - Gimnasio</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <div class="back-button-container">
                        <a href="register_choice.html" class="back-button">Volver</a>
                    </div>
                    <div class="form-page container">
                        <div class="form-section">
                            <h1>Registrar Socio</h1>
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="nombre_socio">Nombre</label>
                                    <input type="text" id="nombre_socio" name="nombre_socio" required>
                                </div>
                                <div class="form-group">
                                    <label for="apellido_socio">Apellido</label>
                                    <input type="text" id="apellido_socio" name="apellido_socio" required>
                                </div>
                                <div class="form-group">
                                    <label for="fecha_nacimiento_socio">Fecha de Nacimiento</label>
                                    <input type="date" id="fecha_nacimiento_socio" name="fecha_nacimiento_socio" required>
                                </div>
                                <div class="form-group">
                                    <label for="dni_socio">DNI</label>
                                    <input type="text" id="dni_socio" name="dni_socio" required>
                                </div>
                                <div class="form-group">
                                    <label for="telefono_socio">Teléfono</label>
                                    <input type="tel" id="telefono_socio" name="telefono_socio" required>
                                </div>
                                <div class="form-group">
                                    <label for="email_socio">E-mail</label>
                                    <input type="email" id="email_socio" name="email_socio" required>
                                </div>
                                <button type="submit" class="btn-accent">Agregar Socio</button>
                            </form>
                        </div>
                        <div class="image-section">
                            <img src="images/member_form_img.jpg" alt="Imagen Socio">
                        </div>
                    </div>
                </body>
                </html>
                """;
        writeHtmlFile("register_member.html", htmlContent);
    }

    private static void generateAssignPlanPage() {
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Asignar Plan de Entrenamiento - Gimnasio</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <div class="back-button-container">
                        <a href="index.html" class="back-button">Volver</a>
                    </div>
                    <div class="form-page container">
                        <div class="form-section">
                            <h1>Asignar Plan de Entrenamiento</h1>
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="dni_socio_plan">DNI del Socio</label>
                                    <input type="text" id="dni_socio_plan" name="dni_socio_plan" required>
                                </div>
                                <div class="form-group">
                                    <label for="plan_asignado">Plan Asignado</label>
                                    <select id="plan_asignado" name="plan_asignado" required>
                                        <option value="">Seleccione un plan</option>
                                        <option value="basico">Plan Básico</option>
                                        <option value="premium">Plan Premium</option>
                                        <option value="vip">Plan VIP</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="fecha_asignacion_plan">Fecha de Asignación</label>
                                    <input type="date" id="fecha_asignacion_plan" name="fecha_asignacion_plan" required>
                                </div>
                                <button type="submit" class="btn-accent">Asignar Plan</button>
                            </form>
                        </div>
                        <div class="image-section">
                            <img src="images/plan_form_img.jpg" alt="Imagen Plan de Entrenamiento">
                        </div>
                    </div>
                </body>
                </html>
                """;
        writeHtmlFile("assign_plan.html", htmlContent);
    }

    private static void generateAssignClassPage() {
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Asignar Clase de Entrenamiento - Gimnasio</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <div class="back-button-container">
                        <a href="index.html" class="back-button">Volver</a>
                    </div>
                    <div class="form-page container">
                        <div class="form-section">
                            <h1>Asignar Clase de Entrenamiento</h1>
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="dni_socio_clase">DNI del Socio</label>
                                    <input type="text" id="dni_socio_clase" name="dni_socio_clase" required>
                                </div>
                                <div class="form-group">
                                    <label for="clase_asignada">Clase Asignada</label>
                                    <select id="clase_asignada" name="clase_asignada" required>
                                        <option value="">Seleccione una clase</option>
                                        <option value="yoga">Yoga</option>
                                        <option value="spinning">Spinning</option>
                                        <option value="zumba">Zumba</option>
                                        <option value="crossfit">CrossFit</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="fecha_asignacion_clase">Fecha</label>
                                    <input type="date" id="fecha_asignacion_clase" name="fecha_asignacion_clase" required>
                                </div>
                                <div class="form-group">
                                    <label for="hora_asignacion_clase">Hora</label>
                                    <input type="time" id="hora_asignacion_clase" name="hora_asignacion_clase" required>
                                </div>
                                <button type="submit" class="btn-accent">Asignar Clase</button>
                            </form>
                        </div>
                        <div class="image-section">
                            <img src="images/class_form_img.jpg" alt="Imagen Clase de Entrenamiento">
                        </div>
                    </div>
                </body>
                </html>
                """;
        writeHtmlFile("assign_class.html", htmlContent);
    }

    private static void writeHtmlFile(String fileName, String content) {
        try (PrintWriter out = new PrintWriter(OUTPUT_DIR + fileName)) {
            out.println(content);
        } catch (IOException e) {
            System.err.println("Error al generar " + fileName + ": " + e.getMessage());
        }
    }
}