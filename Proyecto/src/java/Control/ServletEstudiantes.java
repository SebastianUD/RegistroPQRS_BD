package Control;

import ControlDAO.OperacionesBDEstudiantes;
import Modelo.PQRSVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ServletEstudiantes"})
public class ServletEstudiantes extends HttpServlet {

    private OperacionesBDEstudiantes opBD;

    public ServletEstudiantes() throws Exception {
        opBD = new OperacionesBDEstudiantes();
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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            //UserVO user = opBD.consultarUsuario(usuario, tipo, contrasena);
            String presionado = request.getParameter("accion"); //Dos botones que se llaman acción, pero con diferente valor

            if (presionado.equals("Enviar PQRS")) {

                //Se guardan los valores de los input
                String radicado = String.valueOf(generarNumero()); //Es casi imposible que se repita
                String documento = request.getParameter("inputDoc");
                String tipo = request.getParameter("inputTipo");
                String nombre = request.getParameter("inputNombre");
                String categoria = request.getParameter("radioTypePetition");
                String telefono = request.getParameter("inputNumero");
                String correo = request.getParameter("inputCorreo");
                String codigo = request.getParameter("inputCodigo");
                String estado = "En proceso"; //Siempre se inicia en proceso
                String solicitud = request.getParameter("inputSolicitud");

                //Se escribe la PQRS
                PQRSVO pqrs = new PQRSVO(radicado, documento, nombre, telefono, correo, estado, tipo, codigo, categoria, solicitud);
                opBD.escribirPQRS(pqrs);

                //Set para que se puedan volver a poner cuando se recargue la página
                request.setAttribute("nombre", nombre);
                request.setAttribute("documento", documento);
                request.setAttribute("telefono", telefono);
                request.setAttribute("correo", correo);
                request.setAttribute("tipo", tipo);
                request.setAttribute("codigo", codigo);
                //Se vuelve a utilizar lo de index, para que empiece con los campos rellenados
                request.setAttribute("presionado", "no"); //Si se envía una se cierra la consulta, si hubo consulta

                //Se envía el mensaje cuando se le da en enviar PQRS
                request.setAttribute("mensaje", "Su PQRS ha sido enviada, espere a que sea respondida. Su número de radicado es: " + radicado);

                //Siempre se envía el usuario para hacer la verificación del radicado
                request.setAttribute("usuario", request.getParameter("usuario"));
                request.getRequestDispatcher("pqrs.jsp").forward(request, response);
            } else if (presionado.equals("Consultar PQRS")) { //O sea si se oprime consultar

                //se busca el numero de radicado en la base
                String radicadoreal = request.getParameter("radicado");
                String documento = request.getParameter("documento");
                String nombre = request.getParameter("nombre");
                String tipo = request.getParameter("tipo");
                String codigo = request.getParameter("codigo");
                String telefono = request.getParameter("telefono");
                String correo = request.getParameter("correo");

                PQRSVO pqrs = opBD.consultarPQRS(radicadoreal, documento);

                if (pqrs == null) { //Si no corresponde
                    //Mensaje de que no existe
                    request.setAttribute("mensaje2", "El número de radicado no corresponde a ninguna pqrs");
                    request.setAttribute("presionado", "no");
                } else {

                    //Si corresponde se consulta y se busca
                    request.setAttribute("nombre", pqrs.getNombre());
                    request.setAttribute("radicado", pqrs.getRadicado());
                    request.setAttribute("documento", pqrs.getDocumento());
                    request.setAttribute("telefono", pqrs.getTelefono());
                    request.setAttribute("correo", pqrs.getCorreo());
                    request.setAttribute("estado", pqrs.getEstado());
                    request.setAttribute("tipo", pqrs.getTipo());
                    request.setAttribute("codigo", pqrs.getCodigo());
                    request.setAttribute("categoria", pqrs.getCategoria());
                    request.setAttribute("solicitud", pqrs.getSolicitud());
                    request.setAttribute("respuesta", pqrs.getRespuesta());
                    request.setAttribute("fecha", pqrs.getFecha());

                    request.setAttribute("presionado", "si");

                }

                //Set para que se puedan volver a poner en los input de enviar cuando se recargue la página
                request.setAttribute("nombre", nombre);
                request.setAttribute("documento", documento);
                request.setAttribute("telefono", telefono);
                request.setAttribute("correo", correo);
                request.setAttribute("tipo", tipo);
                request.setAttribute("codigo", codigo);
                //Siempre se envía el usuario para hacer la verificación del radicado
                request.setAttribute("usuario", request.getParameter("usuario"));
                request.getRequestDispatcher("pqrs.jsp").forward(request, response);
            } else if (presionado.equals("Salir")) { //Para cumplir requerimiento de las formas de redireccionar
                response.sendRedirect("index.jsp");
            }

        } finally {
            out.close();
        }

    }

    public long generarNumero() { //Método para generar un número random
        Random rand = new Random();
        long numeroAleatorio = rand.nextLong();
        // Convertir a positivo y asegurarse de que tenga 12 dígitos
        numeroAleatorio = Math.abs(numeroAleatorio) % 1000000000000L;
        return numeroAleatorio;
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
