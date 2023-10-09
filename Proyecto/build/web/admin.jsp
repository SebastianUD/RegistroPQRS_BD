<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Se incluyen los estilos de Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
              crossorigin="anonymous">
        <!-- Se incluyen los estilos de los iconos de Bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
        <!-- Se incluye un archivo de estilos personalizado -->
        <link rel="stylesheet" href="admin.css">
        <title>Panel de Administración - PQRs</title>
    </head>

    <% Boolean mostrarLabel = (Boolean) request.getAttribute("mostrarLabel"); %>

    <body>
        <!-- Barra de navegación -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
            <section class="container">
                <a class="navbar-brand" href="https://www.udistrital.edu.co/inicio" target="_blank">
                    <i class="bi bi-building"></i> Universidad Distrital
                </a>
                <!-- Botón de navegación desplegable -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Elementos de la barra de navegación -->
                <section class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp"><i class="bi bi-arrow-left"></i> Volver</a>
                        </li>
                    </ul>
                </section>
            </section>
        </nav>

        <!-- Panel de administración -->
        <section id="admin-panel" class="container py-5">
            <h2 class="text-center">Panel de Administración</h2>
            <!-- Opciones del panel de administración -->
            <div class="admin-options ">
                <a href="#pqrs-in-process" class="btn btn-primary">Revisar las PQRs</a>
                <a href="#respond-pqrs" class="btn btn-primary">Contestar las PQRs</a>
                <a href="#work-record" class="btn btn-primary">Consultar el record de trabajo</a>
                <a href="#block-pqrs" class="btn btn-primary">Bloquear una PQRs</a>
            </div>


            <% String datos = (String) request.getAttribute("datos"); %>
            <!-- Sección de PQRs en proceso -->
            <section id="pqrs-in-process" class="admin-section">
                <h3 class="text-center">PQRS en Proceso</h3>
                <!-- Tabla para mostrar las PQRs en proceso -->
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Fecha</th>
                            <th scope="col"># de Radicado</th>
                            <th scope="col">Categoria</th>
                            <th scope="col">Mensaje</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Dividir la cadena de datos en filas --%>
                        <% String[] filas = datos.split("<br>"); %>
                        <%-- Generar las filas de la tabla con los datos obtenidos --%>
                        <% if (filas.length > 1) { %>
                        <% for (int i = 1; i < filas.length; i++) { %>
                        <% String[] campos = filas[i].split("\t"); %>
                        <% if (campos.length >= 11) { %>
                        <% String nombre = campos[3]; %>
                        <% String codigo = campos[7]; %>
                        <% String documento = campos[1]; %>
                        <% String mensaje = campos[9]; %>
                        <% String correo = campos[6]; %>
                        <% String telefono = campos[5]; %>
                        <% String radicado = campos[0]; %>
                        <% String tipodoc = campos[2];%>
                        <tr>
                            <th scope="row"><%= i%></th>
                            <td><%= campos[10]%></td>
                            <td><%= radicado%></td>
                            <td><%= campos[4]%></td>
                            <td>
                                <a href="#" onclick="mostrarMensaje('<%= nombre%>', '<%= radicado%>', '<%= codigo%>', '<%= telefono%>', '<%= correo%>', '<%= tipodoc%>', '<%= documento%>', '<%= mensaje%>'); return false;">
                                    Ver Texto
                                </a>
                            </td>
                        </tr>
                        <% } %>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="5">No hay PQRs en proceso</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </section>

            <!-- Script del cuadro de diálogo -->
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                                    function mostrarMensaje(nombre, radicado, codigo, telefono, correo, tipo, documento, mensaje) {
                                        Swal.fire({
                                            title: 'PQRS con radicado: ' + radicado,
                                            html: '<div style="display: flex;">' +
                                                    '<div style="flex-grow: 1;">' +
                                                    '<strong><span style="font-size: 14px;">Código:</span></strong> ' +
                                                    '<span style="font-size: 12px;">' + codigo + '</span>' +
                                                    '</div>' +
                                                    '<div style="flex-grow: 1;">' +
                                                    '<strong><span style="font-size: 14px;">Documento:</span></strong> ' +
                                                    '<span style="font-size: 12px;">' + tipo + documento + '</span>' +
                                                    '</div>' +
                                                    '<div style="flex-grow: 1;">' +
                                                    '<strong><span style="font-size: 14px;">Teléfono:</span></strong> ' +
                                                    '<span style="font-size: 12px;">' + telefono + '</span>' +
                                                    '</div>' +
                                                    '<div style="flex-grow: 1;">' +
                                                    '<strong><span style="font-size: 14px;">Correo:</span></strong> ' +
                                                    '<span style="font-size: 12px;">' + correo + '</span>' +
                                                    '</div>' +
                                                    '</div>' +
                                                    '<div style="text-align: center;"><br/>' +
                                                    '<strong>Mensaje de ' + nombre + '</strong><br/>' +
                                                    '<span style="font-size: 18px;">' + mensaje + '</span>' +
                                                    '</div>',
                                            icon: 'info'
                                        });
                                    }
            </script>


            <!-- Sección de respuesta a PQRs -->
            <section id="respond-pqrs" class="admin-section">
                <h3 class="text-center">Responder PQRS</h3>
                <!-- Formulario para responder a una PQR -->
                <form action="ServletAdmin" method="post">
                    <div class="mb-3">
                        <label for="pqrs-id" class="form-label">ID de PQRS</label>
                        <input type="number" class="form-control" id="pqrs-id" required name="IDRespuesta">
                    </div>

                    <div class="mb-3">
                        <label for="response" class="form-label">Respuesta</label>
                        <textarea class="form-control" id="response" rows="3" required name="respuesta"></textarea>
                    </div>
                    <input type="submit" name="accion" value="Responder PQRS" id="btn-consultar-datos">
                </form>
            </section>


            <!-- Sección de registro de trabajo -->
            <section id="work-record" class="admin-section">
                <h3 class="text-center">Registro de Trabajo</h3>
                <form action="ServletAdmin" methid="post">
                    <div class="mb-3">
                        <label for="record-date" class="form-label">Fecha</label>
                        <input type="date" class="form-control" id="record-date" required name="fechabuscar">
                    </div>
                    <input type="submit" name="accion" value="Consultar" id="btn-consultar">
                </form>
                <!-- Contenedor para mostrar los resultados de la consulta -->
                <div id="record-result">
                    <!-- AquÃ­ se mostrarÃ¡ el resultado de la consulta -->
                </div>
            </section>

            <!-- Sección de bloqueo de PQRs -->
            <section id="block-pqrs" class="admin-section">
                <h3 class="text-center">Bloquear PQRS</h3>
                <!-- Formulario para bloquear una PQRS -->
                <form action="ServletAdmin" methid="post">
                    <div class="mb-3">
                        <label for="block-pqrs-id" class="form-label">ID de PQRS</label>
                        <input type="number" class="form-control" id="block-pqrs-id" required name="IDBloquear">
                    </div>
                    <div class="mb-3">
                        <label for="block-reason" class="form-label">Motivo del Bloqueo</label>
                        <textarea class="form-control" id="block-reason" rows="3" required name="MBloqueo"></textarea>
                    </div>

                    <input type="submit" name="accion" value="Bloquear PQRS" id="btn-bloquear" class="btn btn-danger" name="Bloquear">
                </form>
            </section>
        </section>


        <script>
            function mostrarAlerta(mensaje, icono, titulo) {
                Swal.fire({//Para mostrar cuadro de dialogo
                    title: titulo,
                    text: mensaje,
                    icon: icono,
                    confirmButtonText: "Aceptar"
                });
            }
        </script>

        <!-- Llamado a la función alerta -->
        <script>
            window.onload = function () { // Para que después de cargar la página llame la función
            <% String mensaje = (String) request.getAttribute("mensaje"); %> // Request porque viene desde request el mensaje
            <% String nofound = (String) request.getAttribute("mensaje2"); %> // Request porque viene desde request el mensaje
            <% String mensaje3 = (String) request.getAttribute("mensaje3"); %> // Request porque viene desde request el mensaje

            <% if (mensaje != null && !mensaje.isEmpty()) {%> // Si no hay mensaje es porque no se ha oprimido antes el botón enviar pqrs
                mostrarAlerta('<%= mensaje%>', "success", "PQRS Contestada");
            <% }%>

            <% if (nofound != null && !nofound.isEmpty()) {%> // Si no hay mensaje es porque no se ha oprimido antes el botón enviar pqrs
                mostrarAlerta('<%= nofound%>', "error", "PQRS No encontrada");
            <% }%>

            <% if (mensaje3 != null && !mensaje3.isEmpty()) {%> // Si no hay mensaje es porque no se ha oprimido antes el botón enviar pqrs
                mostrarAlerta('<%= mensaje3%>', "info", "Registro de Trabajo");
            <% }%>
            };
        </script>



        <!-- Estilo del mensaje de alerta -->
        <style>

            .swal2-styled.swal2-confirm {
                background-color: #1c1b1b;
            }

            .swal2-title {
                color: #000000;
            }


            .swal2-popup .swal2-content {
                color: #000000;
            }

        </style>

        <!-- Se incluye el archivo JavaScript de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-KyZ+rDKv0L4fsTgRlOXTTIYB/2D5ZtIYJ2TD1zTThBXTG4PvHLzc4KDTG8hd+RA5"
        crossorigin="anonymous"></script>
    </body>
</html>