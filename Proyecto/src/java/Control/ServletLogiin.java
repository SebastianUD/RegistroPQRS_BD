package Control;

import ControlDAO.OperacionesBDLogin;
import Modelo.UserVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletLogiin", urlPatterns = {"/ServletLogiin"})
public class ServletLogiin extends HttpServlet {

    private OperacionesBDLogin opBD;

    public ServletLogiin() throws Exception {
        opBD = new OperacionesBDLogin();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("password");
            String tipo = request.getParameter("user-role");

            UserVO existe = opBD.consultarUsuario(usuario, tipo, contrasena);

            if (existe != null) {
                if (tipo.equals("admin")) {

                    String datos = opBD.mostrarDatos();
                    out.println("<!DOCTYPE html>");
                    out.println("<html lang=\"es\">");
                    out.println("<head>");
                    out.println("    <meta charset=\"UTF-8\">");
                    out.println("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
                    out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                    out.println("    <!-- Se incluyen los estilos de Bootstrap -->");
                    out.println("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\"");
                    out.println("          rel=\"stylesheet\"");
                    out.println("          integrity=\"sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT\"");
                    out.println("          crossorigin=\"anonymous\">");
                    out.println("    <!-- Se incluyen los estilos de los iconos de Bootstrap -->");
                    out.println("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css\" />");
                    out.println("    <!-- Se incluye un archivo de estilos personalizado -->");
                    out.println("    <link rel=\"stylesheet\" href=\"admin.css\">");
                    out.println("    <title>Panel de Administración - PQRs</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<!-- Barra de navegación -->");
                    out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark sticky-top\">");
                    out.println("    <section class=\"container\">");
                    out.println("        <a class=\"navbar-brand\" href=\"https://www.udistrital.edu.co/inicio\" target=\"_blank\">");
                    out.println("            <i class=\"bi bi-building\"></i> Universidad Distrital");
                    out.println("        </a>");
                    out.println("        <!-- Botón de navegación desplegable -->");
                    out.println("        <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\"");
                    out.println("                aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
                    out.println("            <span class=\"navbar-toggler-icon\"></span>");
                    out.println("        </button>");
                    out.println("        <!-- Elementos de la barra de navegación -->");
                    out.println("        <section class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
                    out.println("            <ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">");
                    out.println("                <li class=\"nav-item\">");
                    out.println("                    <a class=\"nav-link\" href=\"index.jsp\"><i class=\"bi bi-arrow-left\"></i> Volver</a>");
                    out.println("                </li>");
                    out.println("            </ul>");
                    out.println("        </section>");
                    out.println("    </section>");
                    out.println("</nav>");

                    out.println("<!-- Panel de administración -->");
                    out.println("<section id=\"admin-panel\" class=\"container py-5\">");
                    out.println("    <h2 class=\"text-center\">Panel de Administración</h2>");
                    out.println("    <!-- Opciones del panel de administración -->");
                    out.println("    <div class=\"admin-options \">");
                    out.println("        <a href=\"#pqrs-in-process\" class=\"btn btn-primary\">Revisar las PQRs</a>");
                    out.println("        <a href=\"#respond-pqrs\" class=\"btn btn-primary\">Contestar las PQRs</a>");
                    out.println("        <a href=\"#work-record\" class=\"btn btn-primary\">Consultar el record de trabajo</a>");
                    out.println("        <a href=\"#block-pqrs\" class=\"btn btn-primary\">Bloquear una PQRs</a>");
                    out.println("    </div>");

// Dividir la cadena de datos en filas
                    String[] filas = datos.split("<br>");

                    out.println("<!-- Sección de PQRs en proceso -->");
                    out.println("<section id=\"pqrs-in-process\" class=\"admin-section\">");
                    out.println("    <h3 class=\"text-center\">PQRs en Proceso</h3>");
                    out.println("    <!-- Tabla para mostrar las PQRs en proceso -->");
                    out.println("    <table class=\"table\">");
                    out.println("        <thead>");
                    out.println("        <tr>");
                    out.println("            <th scope=\"col\">#</th>");
                    out.println("            <th scope=\"col\">Fecha</th>");
                    out.println("            <th scope=\"col\"># de Radicado</th>");
                    out.println("            <th scope=\"col\">Categoria</th>");

                    out.println("            <th scope=\"col\">Mensaje</th>");
                    out.println("        </tr>");
                    out.println("        </thead>");
                    out.println("        <tbody>");

// Generar las filas de la tabla con los datos obtenidos
                    if (filas.length > 1) { // Verificar que hay más de una fila (la primera es la cabecera)
                        for (int i = 1; i < filas.length; i++) {
                            String[] campos = filas[i].split("\t"); // Dividir la fila en campos
                            // Verificar que hay suficientes campos para generar la fila
                            if (campos.length >= 11) {
                                String nombre = campos[3];
                                String codigo = campos[7];
                                String documento = campos[1];
                                String mensaje = campos[9];
                                String correo = campos[6];
                                String telefono = campos[5];
                                String radicado = campos[0];
                                String tipodoc = campos[2];

                                out.println("        <tr>");
                                out.println("            <th scope=\"row\">" + i + "</th>");
                                out.println("            <td>" + campos[10] + "</td>"); //FECHA
                                out.println("            <td>" + radicado + "</td>");
                                out.println("            <td>" + campos[4] + "</td>"); //Categoría de solicitud
                                out.println("            <td><a href=\"#\" onclick=\"mostrarMensaje('" + nombre + "', '" + radicado + "', '" + codigo + "', '" + telefono + "', '" + correo + "', '" + tipodoc + "', '" + documento + "', '" + mensaje + "'); return false;\">Ver Texto</a></td>");
                                out.println("        </tr>");
                            }
                        }
                    } else {
                        // Código para mostrar una fila indicando que no hay datos
                        out.println("        <tr>");
                        out.println("            <td colspan=\"7\">No hay datos disponibles</td>");
                        out.println("        </tr>");
                    }
                    out.println("        </tbody>");
                    out.println("    </table>");
                    out.println("</section>");

                    //Script del cuadro de dialogo
                    out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
                    out.println("<script>");
                    out.println("function mostrarMensaje(nombre, radicado, codigo, telefono, correo, tipo, documento, mensaje) {");
                    out.println("    Swal.fire({");
                    out.println("        title: 'PQRS con radicado: '+radicado,");
                    out.println("html: '<div style=\"display: flex;\">' + '<div style=\"flex-grow: 1;\">' + '<strong><span style=\"font-size: 14px;\">Código:</span></strong> ' + '<span style=\"font-size: 12px;\">' + codigo + '</span></div>' + '<div style=\"flex-grow: 1;\">' + "
                            + "'<strong><span style=\"font-size: 14px;\">Documento:</span></strong> ' + '<span style=\"font-size: 12px;\">' + tipo + documento + '</span></div>' + '<div style=\"flex-grow: 1;\">' + "
                            + "'<strong><span style=\"font-size: 14px;\">Teléfono:</span></strong> ' + '<span style=\"font-size: 12px;\">' + telefono + '</span></div>' + '<div style=\"flex-grow: 1;\">' + "
                            + "'<strong><span style=\"font-size: 14px;\">Correo:</span></strong> ' + '<span style=\"font-size: 12px;\">' + correo + '</span></div>' + '</div>' + '<div style=\"text-align: center;\"><br/>"
                            + "<strong>Mensaje de ' + nombre + '</strong><br/>' + '<span style=\"font-size: 18px;\">' + mensaje + '</span></div>',");

                    out.println("        icon: 'info'");
                    out.println("    });");
                    out.println("}");
                    out.println("</script>");

                    out.println("<!-- Sección de respuesta a PQRs -->");
                    out.println("<section id=\"respond-pqrs\" class=\"admin-section\">");
                    out.println("    <h3 class=\"text-center\">Responder PQRS</h3>");
                    out.println("    <!-- Formulario para responder a una PQR -->");
                    out.println("    <form action=\"ServletAdmin\" method=\"post\">");
                    out.println("        <div class=\"mb-3\">");
                    out.println("            <label for=\"pqrs-id\" class=\"form-label\">ID de PQRS</label>");
                    out.println("            <input type=\"number\" class=\"form-control\" id=\"pqrs-id\" required name=\"IDRespuesta\">");
                    out.println("        </div>");
                    out.println("        <div class=\"mb-3\">");
                    out.println("            <label for=\"response\" class=\"form-label\">Respuesta</label>");
                    out.println("            <textarea class=\"form-control\" id=\"response\" rows=\"3\" required name=\"respuesta\"></textarea>");
                    out.println("        </div>");
                    out.println("        <input type=\"submit\" name=\"accion\" value=\"Responder PQRS\" id=\"btn-consultar-datos\">");
                    out.println("    </form>");
                    out.println("</section>");

                    out.println("    <!-- Sección de registro de trabajo -->");
                    out.println("    <section id=\"work-record\" class=\"admin-section\">");
                    out.println("        <h3 class=\"text-center\">Registro de Trabajo</h3>");
                    out.println("<form action=\"ServletAdmin\" method=\"POST\">");
                    out.println("            <div class=\"mb-3\">");
                    out.println("                <label for=\"record-date\" class=\"form-label\">Fecha</label>");
                    out.println("                <input type=\"date\" class=\"form-control\" id=\"record-date\" required name=\"fechabuscar\">");
                    out.println("            </div>");
                    out.println("<input type=\"submit\" name=\"accion\" value=\"Consultar\" id=\"btn-consultar-registro\">");
                    out.println("        </form>");
                    out.println("        <!-- Contenedor para mostrar los resultados de la consulta -->");
                    out.println("        <div id=\"record-result\">");
                    out.println("            <!-- Aquí se mostrará el resultado de la consulta -->");
                    out.println("        </div>");
                    out.println("    </section>");

                    out.println("<!-- Sección de bloqueo de PQRs -->");
                    out.println("<section id=\"block-pqrs\" class=\"admin-section\">");
                    out.println("    <h3 class=\"text-center\">Bloquear PQRS</h3>");
                    out.println("    <!-- Formulario para bloquear una PQRS -->");
                    out.println("    <form action=\"ServletAdmin\" method=\"post\">");
                    out.println("        <div class=\"mb-3\">");
                    out.println("            <label for=\"block-pqrs-id\" class=\"form-label\">ID de PQRS</label>");
                    out.println("            <input type=\"number\" class=\"form-control\" id=\"block-pqrs-id\" required name=\"IDBloquear\">");
                    out.println("        </div>");
                    out.println("        <div class=\"mb-3\">");
                    out.println("            <label for=\"block-reason\" class=\"form-label\">Motivo del Bloqueo</label>");
                    out.println("            <textarea class=\"form-control\" id=\"block-reason\" rows=\"3\" required name=\"MBloqueo\"></textarea>");
                    out.println("        </div>");
                    out.println("        <input type=\"submit\" name=\"accion\" value=\"Bloquear PQRS\" id=\"btn-bloquear\" class=\"btn btn-danger\" name=\"Bloquear\">");
                    out.println("    </form>");
                    out.println("</section>");
                    out.println("</section>");

                    out.println("<!-- Se incluye el archivo JavaScript de Bootstrap -->");
                    out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js\"");
                    out.println("        integrity=\"sha384-KyZ+rDKv0L4fsTgRlOXTTIYB/2D5ZtIYJ2TD1zTThBXTG4PvHLzc4KDTG8hd+RA5\"");
                    out.println("        crossorigin=\"anonymous\"></script>");
                    out.println("</body>");
                    out.println("</html>");

                } else {
                    //En este caso se sube al request de datos, y este se va a enviar al PQRS.JSP
                    //De esta forma, enviamos el valor del usuario internamente al pqrs.jsp

                    request.setAttribute("nombre", existe.getNombre());
                    request.setAttribute("documento", existe.getDocumento());
                    request.setAttribute("telefono", existe.getTelefono());
                    request.setAttribute("correo", existe.getCorreo());
                    request.setAttribute("tipo", existe.getTipodocumento());
                    request.setAttribute("codigo", existe.getCodigo());
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("pqrs.jsp").forward(request, response);
                }
            } else {
                //Vuelve a mostrar la página de inicio, pero con buffer para el requerimiento de crear de las formas aprendidas
                StringBuffer buffer = new StringBuffer();
                buffer.append("<!DOCTYPE html>\n");
                buffer.append("<html lang=\"es\">\n");
                buffer.append("<head>\n");
                buffer.append("  <link rel=\"stylesheet\" href=\"login.css\">\n");
                buffer.append("  <script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.js\"></script>\n");
                buffer.append("  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css\">\n");
                buffer.append("  <title>Login PQRS</title>\n");

                //Función de alert para indicar que las credenciales son inválidas.
                buffer.append("</head>\n");
                buffer.append("<body onload=\"showAlert()\">\n"); //Aquí llama la función apenas carga
                buffer.append("    <section>\n");
                buffer.append("        <div class=\"box\">\n");
                buffer.append("            <div class=\"value\">\n");
                buffer.append("                <form action=\"ServletLogiin\" method=\"post\">\n");
                buffer.append("                    <div style=\"text-align: center;\">\n");
                buffer.append("                        <img src=\"login_images/logo.png\" alt=\"Logo de la universidad\" height=\"100\">\n");
                buffer.append("                    </div>\n");
                buffer.append("                    <h2>Registro PQRS</h2>\n");
                buffer.append("                    <div class=\"inputbox\">\n");
                buffer.append("                        <ion-icon name=\"person-outline\"></ion-icon>\n");
                buffer.append("                        <input type=\"text\" required name=\"usuario\" oninvalid=\"this.setCustomValidity('Por favor, ingresa un nombre de usuario válido.')\" oninput=\"this.setCustomValidity('')\">\n");
                buffer.append("                        <label for=\"\">Usuario</label>\n");
                buffer.append("                    </div>\n");
                buffer.append("                    <div class=\"inputbox\">\n");
                buffer.append("                        <ion-icon name=\"lock-closed-outline\"></ion-icon>\n");
                buffer.append("                        <input type=\"password\" required name=\"password\" required oninvalid=\"this.setCustomValidity('Por favor, rellene el campo')\" oninput=\"this.setCustomValidity('')\">\n");
                buffer.append("                        <label for=\"\">Contraseña</label>\n");
                buffer.append("                    </div>\n");
                buffer.append("                    <div class=\"selector\">\n");
                buffer.append("                        <label for=\"\">\n");
                buffer.append("                            <input type=\"radio\" name=\"user-role\" value=\"estudiante\" required> Estudiante\n");
                buffer.append("                            <input type=\"radio\" name=\"user-role\" value=\"admin\"> Administrador\n");
                buffer.append("                        </label>\n");
                buffer.append("                    </div>\n");
                buffer.append("                    <button type=\"submit\" name=\"Enviar\">Ingresar</button>\n");
                buffer.append("                    <div class=\"link_ud\">\n");
                buffer.append("                        <p><a href=\"https://www.udistrital.edu.co/inicio\" target=\"_blank\">U. Distrital Francisco Jose de Caldas</a></p>\n");
                buffer.append("                    </div>\n");
                buffer.append("                </form>\n");
                buffer.append("            </div>\n");
                buffer.append("        </div>\n");
                buffer.append("    </section>\n");
                buffer.append("    <script type=\"module\" src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js\"></script>\n");
                buffer.append("    <script nomodule src=\"https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js\"></script>\n");

                // Función de alert para indicar que las credenciales son inválidas.
                buffer.append("<script>");
                buffer.append("window.onload = function() {");
                buffer.append("  Swal.fire({");
                buffer.append("    title: 'Verifique sus credenciales',");
                buffer.append("    html: '<span style=\"color: white;\">Los datos ingresados no coinciden con ningún usuario, intente nuevamente.</span>',");
                buffer.append("    icon: 'error',");
                buffer.append("    confirmButtonText: 'Aceptar'");
                buffer.append("  });");
                buffer.append("};");
                buffer.append("</script>");

                //Estilo del cuadro de alerta
                buffer.append("<style>");
                buffer.append(".swal2-popup {");
                buffer.append("  border-color: #000000;");
                buffer.append("  background-color: #3f4240;");
                buffer.append("}");

                buffer.append(".swal2-title {");
                buffer.append("  color: #ffffff;");
                buffer.append("}");

                buffer.append(".swal2-popup .swal2-content {");
                buffer.append("  color: #000000;");
                buffer.append("}");

                buffer.append(".swal2-styled.swal2-confirm {");
                buffer.append("  background-color: #000000;");
                buffer.append("}");
                buffer.append("</style>");

                buffer.append("</body>\n");
                buffer.append("</html>");
                response.getWriter().print(buffer.toString());

            }

        } catch (Exception ex) {
            Logger.getLogger(ServletLogiin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
