<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Página JSP con configuración de lenguaje y codificación -->

<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="stylesheet" type="text/css"  href="login.css">

        <!-- Enlace a una hoja de estilo externa llamada "login.css" -->

        <title>Login PQRS</title>
        <!-- Título de la página -->

    </head>
    <body>
        <section>
            <!-- Sección principal de la página -->
            <div class="box">
                <!-- Contenedor del formulario de inicio de sesión -->
                <div class="value">
                    <!-- Contenedor interno -->

                    <form action="ServletLogiin" method="post">
                        <!-- Formulario de inicio de sesión con método POST y acción "ServletLogiin" -->

                        <div style="text-align: center;">
                            <img src="login_images/logo.png" alt="Logo de la universidad" height="100">
                            <!-- Logo de la universidad -->
                        </div>

                        <h2>Registro PQRS</h2>
                        <!-- Encabezado del formulario -->

                        <div class="inputbox">
                            <ion-icon name="person-outline"></ion-icon>
                            <input type="text" required name="usuario" id="usuario" oninvalid="this.setCustomValidity('Por favor, ingresa un nombre de usuario válido.')" oninput="this.setCustomValidity('')" maxlength="20">
                            <!-- Campo de entrada para el nombre de usuario,con oninput para que para restablecer el mensaje de validación personalizado cuando el usuario comienza a escribir en el campo -->
                            <label for="">Usuario</label>
                            <!-- Etiqueta para describir el campo -->
                        </div>

                        <div class="inputbox">
                            <ion-icon name="lock-closed-outline"></ion-icon>
                            <input type="password" required name="password" required oninvalid="this.setCustomValidity('Por favor, rellene el campo')" oninput="this.setCustomValidity('')" maxlength="25">
                            <!-- Campo de entrada para la contraseña -->
                            <label for="">Contraseña</label>
                            <!-- Etiqueta para describir el campo -->
                        </div>

                        <div class="selector">
                            <label for="">
                                <input type="radio" name="user-role" value="estudiante" required> Estudiante
                                <!-- Opción de radio para el rol "estudiante" con mensaje de seleccionar personalizado -->
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="user-role" value="admin"> Administrador
                                <!-- Opción de radio para el rol "administrador" -->
                            </label>
                        </div>

                        <button type="submit" name="Enviar">Ingresar</button>
                        <!-- Botón de envío del formulario -->

                        <div class="link_ud">
                            <p><a href="https://www.udistrital.edu.co/inicio" target="_blank">U. Distrital Francisco Jose de Caldas</a></p>
                            <!-- Enlace externo a la página de la universidad -->
                        </div>
                    </form>
                </div>
            </div>
        </section>



        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <!-- Script de Ionicons cargado como módulo -->

        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <!-- Script de Ionicons para navegadores antiguos -->

        <script>
            // Seleccionar el campo de entrada del usuario por su identificador
            const usuarioInput = document.getElementById('usuario');

            // Agregar un controlador de eventos para el evento 'input'
            usuarioInput.addEventListener('input', function () {
                // Obtener el valor actual del campo de entrada
                let usuarioValue = usuarioInput.value;

                // Convertir el valor a mayúsculas
                usuarioValue = usuarioValue.toLowerCase();

                // Asignar el valor en mayúsculas al campo de entrada
                usuarioInput.value = usuarioValue;
            });
        </script>
    </body>
</html>
