<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <!-- Metadatos y configuración de la página -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
              rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
              crossorigin="anonymous" />
        <!-- Enlace al archivo CSS de Bootstrap -->

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
        <!-- Enlace al archivo de iconos de Bootstrap -->

        <link rel="stylesheet" href="pqrs.css" />
        <!-- Enlace al archivo CSS local 'pqrs.css' -->

        <style>
            .response-link {
                display: inline-block;
                margin-left: 10px;
            }

            /* Estilos personalizados */


        </style>

        <title>PQRS U.Distrital</title>
        <!-- Título de la página -->
    </head>
    <body>



        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
            <section class="container">
                <!-- Barra de navegación -->

                <a class="navbar-brand" href="https://www.udistrital.edu.co/inicio" target="_blank"><i
                        class="bi bi-building"></i> Universidad Distrital</a>
                <!-- Marca de la universidad en la barra de navegación -->

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Botón de despliegue del menú en dispositivos móviles -->

                <section class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <!-- Lista de elementos de navegación en la barra de navegación -->

                        <li class="nav-item">
                            <a class="nav-link" href="#pqrs"><i class="bi bi-file-earmark-medical"></i> PQRS</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#pqrs-list"><i class="bi bi-reply"></i> Respuesta</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp"><i class="bi bi-arrow-left"></i> Volver</a>
                        </li>
                    </ul>
                </section>
                <!-- Menú de navegación -->
            </section>
        </nav>


        <section id="pqrs-list" class="container py-5">

            <form action="ServletEstudiantes" method="post">

                <div class="container">
                    <h2 class="text-center">Respuesta de PQRS</h2>
                    <div class="row">
                        <div class="col-md-6">
                            <h4>Consultar PQRS por número de radicado:</h4>
                            <div class="mb-3">
                                <label for="radicado" class="form-label">Número de radicado:</label>
                                <input type="text" class="form-control" id="radicado" name="radicado" required>
                            </div>
                            <div class="mb-3">
                                <input type="submit" name="accion" value="Consultar PQRS" id="btn-consultar-datos" class="btn btn-success bg-danger">
                            </div>
                        </div>
                    </div>
            </form>

            <div class="row" id="informacion-pqrs" style="display: none;">
                <div class="col-md-6">
                    <h4>Información de la PQRS:</h4>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= request.getAttribute("nombre")%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="radicado-info" class="form-label">Radicado:</label>
                                <input type="text" class="form-control" id="radicado-info" name="radicado-info" value="<%= request.getAttribute("radicado")%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="documento" class="form-label">Documento:</label>
                                <input type="text" class="form-control" id="documento" name="documento" value="<%= request.getAttribute("documento")%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="telefono" class="form-label">Teléfono:</label>
                                <input type="text" class="form-control" id="telefono" name="telefono" value="<%= request.getAttribute("telefono")%>" readonly>
                            </div>

                            <div class="mb-3">
                                <label for="solicitud" class="form-label">Solicitud:</label>
                                <input type="text" class="form-control" id="solicitud" name="solicitud" value="<%= request.getAttribute("solicitud")%>" readonly>
                            </div>

                            <div class="mb-3">
                                <label for="fecha" class="form-label" <% if ("En proceso".equals(request.getAttribute("estado"))) { %> style="display: none;" <% }%>>Fecha de respuesta:</label>
                                <input type="text" class="form-control" id="fecha" name="fecha" value="<%= request.getAttribute("fecha")%>" readonly <% if ("En proceso".equals(request.getAttribute("estado"))) { %> style="display: none;" <% }%>>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="mb-3">
                                <label for="correo" class="form-label">Correo:</label>
                                <input type="text" class="form-control" id="correo" name="correo" value="<%= request.getAttribute("correo")%>" readonly>
                            </div>

                            <div class="mb-3">
                                <label for="estado" class="form-label">Estado:</label>
                                <input type="text" class="form-control" id="estado" name="estado" value="<%= request.getAttribute("estado")%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="tipo" class="form-label">Tipo:</label>
                                <input type="text" class="form-control" id="tipo" name="tipo" value="<%= request.getAttribute("tipo")%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="codigo" class="form-label">Código:</label>
                                <input type="text" class="form-control" id="codigo" name="codigo" value="<%= request.getAttribute("codigo")%>" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="categoria" class="form-label">Categoría:</label>
                                <input type="text" class="form-control" id="categoria" name="categoria" value="<%= request.getAttribute("categoria")%>" readonly>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="respuesta" class="form-label" <% if ("En proceso".equals(request.getAttribute("estado"))) { %> style="display: none;" <% } %>>Respuesta:</label>
                            <% if ("En proceso".equals(request.getAttribute("estado"))) {%>
                            <input type="text" class="form-control" id="respuesta" name="respuesta" value="<%= request.getAttribute("respuesta")%>" readonly style="display: none;">
                            <% } else {%>
                            <textarea class="form-control" id="respuesta" name="respuesta" readonly rows="3"><%= request.getAttribute("respuesta")%></textarea>
                            <% } %>
                        </div>


                    </div>
                </div>
            </div>
        </div>

    </section>


    <section id="pqrs" class="container-fluid bg-secondary py-5">
        <!-- Sección principal de PQRS -->

        <section id="pqrs-form" class="container bg-light p-5 rounded">
            <!-- Formulario de PQRS -->

            <form action="ServletEstudiantes" method="post">             <!-- Se conecta con el ServletEstudiantes -->
                <h2 class="text-center">Formato de PQRS</h2>
                <!-- Título del formulario -->

                <div class="form-group text-center">
                    <label  for="radioTypePetition">Para diligenciar el siguiente formato marque la casilla con el tipo de solicitud.</label>
                    <!-- Etiqueta y descripción de las opciones de solicitud -->

                    <div class="d-flex justify-content-center">
                        <div class="form-check form-check-inline">
                            <input type="radio" name="radioTypePetition" id="radioTypePetitionP" class="form-check-input" value="Peticion" required>
                            <label for="radioTypePetitionP" class="form-check-label">P</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="radioTypePetition" id="radioTypePetitionQ" class="form-check-input" value="Queja" required>
                            <label for="radioTypePetitionQ" class="form-check-label">Q</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="radioTypePetition" id="radioTypePetitionR" class="form-check-input" value="Reclamo" required>
                            <label for="radioTypePetitionR" class="form-check-label">R</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input type="radio" name="radioTypePetition" id="radioTypePetitionS" class="form-check-input" value="Solicitud" required>
                            <label for="radioTypePetitionS" class="form-check-label">S</label>
                        </div>
                    </div>
                    <!-- Opciones de solicitud -->
                </div>

                <div class="form-group">
                    <label for="email">Correo institucional</label> <!-- Se rellena automáticamente el valor de usuario que viene desde index-->
                    <input type="email" class="form-control" id="email" name="inputCorreo" placeholder="name@udistrital.edu.co" required readonly value=${correo}>
                </div>
                <!-- Campo de entrada para el correo institucional -->

                <div class="form-group">
                    <label for="inputName">Nombre completo</label>
                    <input type="text" class="form-control" id="inputName" name="inputNombre" placeholder="Pepito Perez" required readonly value="${nombre}">
                </div>

                <!-- Campo de entrada para el nombre completo -->

                <div class="form-group">
                    <label for="inputCc">Tipo de documento</label>
                    <div class="d-flex justify-content-between">
                        <!-- Se agrega el atributo selected al que coincida con tipo -->
                        <input type="text" id="inputCc" name="inputTipo" class="form-control" style="width: 90px;" value="${tipo}" readonly>

                        <input type="number" id="inputCc" name="inputDoc" class="form-control" required readonly value=${documento}>
                    </div>
                </div>
                <!-- Campo de entrada para el tipo de documento y el número de documento -->

                <div class="form-group">
                    <label for="inputTel">Número de contacto</label>
                    <div class="d-flex justify-content-between">
                        <input type="number" id="inputTel" name="inputNumero" class="form-control" required readonly value=${telefono}>
                    </div>
                </div>
                <!-- Campo de entrada para el número de contacto -->

                <div class="form-group">
                    <label for="code">Código</label>
                    <input type="number" name="inputCodigo" id="petitionTitle" class="form-control" required readonly value=${codigo}>
                </div>
                <!-- Campo de entrada para el código -->

                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Descripción de la solicitud</label>
                    <textarea class="form-control" name="inputSolicitud" id="exampleFormControlTextarea1" rows="3" maxlength="200" required></textarea>
                </div>
                <!-- Campo de entrada para la descripción de la solicitud -->

                <div class="d-flex justify-content-end pt-2">
                    <input type="submit" name="accion" value="Enviar PQRS" id="btn-enviar-datos" class="btn btn-success bg-danger">
                </div>
                <!-- Botón de envío del formulario -->
            </form>


        </section>

        <p> 

        <form action="ServletEstudiantes" method="post">             <!-- Se conecta con el ServletEstudiantes -->

            <div class="container">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <input type="submit" name="accion" value="Salir" id="btn-salir">
                    </div>
                </div>
            </div>

        </form>

    </section>






    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-KyZ+rDKv0L4fsTgRlOXTTIYB/2D5ZtIYJ2TD1zTThBXTG4PvHLzc4KDTG8hd+RA5"
    crossorigin="anonymous"></script>
    <!-- Script de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.0/dist/sweetalert2.all.min.js"></script> <!<!-- Para mensaje de alerta -->



    <!-- Mensajes de alerta -->


    <script>
        // Verificar si el atributo "presionado" está establecido en "si"
        <% String presionado = (String) request.getAttribute("presionado"); %>
        <% if (presionado != null && presionado.equals("si")) { %>
        // Mostrar el div con la información
        document.getElementById('informacion-pqrs').style.display = 'block';
        <% } %>
    </script>



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

        <% if (mensaje != null && !mensaje.isEmpty()) {%> // Si no hay mensaje es porque no se ha oprimido antes el botón enviar pqrs
            mostrarAlerta('<%= mensaje%>', "success", "PQRS Exitosa");
        <% }%>

        <% if (nofound != null && !nofound.isEmpty()) {%> // Si no hay mensaje es porque no se ha oprimido antes el botón enviar pqrs
            mostrarAlerta('<%= nofound%>', "error", "PQRS No encontrada");
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





</body>



</html>
<!-- Fin del archivo HTML -->
