package Control;

import ControlDAO.OperacionesBDAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ServletAdmin"})
public class ServletAdmin extends HttpServlet {

    private OperacionesBDAdmin opBD;

    public ServletAdmin() throws Exception {
        opBD = new OperacionesBDAdmin();
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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String solicitud = "";
        try {

            String presionado = request.getParameter("accion"); //Dos botones que se llaman acción, pero con diferente valor
            String datos = opBD.mostrarDatos();
            request.setAttribute("datos", datos);

            if (presionado.equals("Responder PQRS")) {
                String radicado = request.getParameter("IDRespuesta");
                String respuesta = request.getParameter("respuesta");

                if (opBD.consultarSolicitud(radicado).equals("mal")) {
                    request.setAttribute("mensaje2", "El número de radicado no corresponde a ninguna pqrs");
                } else {
                    opBD.modificarDatos(radicado, "con Respuesta", respuesta);
                    request.setAttribute("mensaje", "La PQRS fue contestada exitosamente");
                }
                datos = opBD.mostrarDatos(); //Para que cuando se recargue se elimine el respondido

                request.setAttribute("datos", datos);
                request.getRequestDispatcher("admin.jsp").forward(request, response);

            } else if (presionado.equals("Bloquear PQRS")) {
                String radicado = request.getParameter("IDBloquear");
                String respuesta = request.getParameter("MBloqueo");


                if (opBD.consultarSolicitud(radicado).equals("mal")) {
                    request.setAttribute("mensaje2", "El número de radicado no corresponde a ninguna pqrs");
                } else {
                opBD.modificarDatos(radicado, "Bloqueada", respuesta);
                    request.setAttribute("mensaje", "La PQRS fue bloqueada exitosamente");
                }

                datos = opBD.mostrarDatos(); //Para que cuando se recargue se elimine el respondido
                request.setAttribute("datos", datos); //Siempre para que inicie y lo cargue de una

                //HTML CON RESPUESTA 
                request.getRequestDispatcher("admin.jsp").forward(request, response);

            } else if (presionado.equals("Consultar")) {
                boolean mostrarLabel = true;
                request.setAttribute("mostrarLabel", mostrarLabel);

                String fecha = request.getParameter("fechabuscar");

                String recordpro = "Las PQRS contestadas el " + fecha + "fueron: " + opBD.record(fecha);
                request.setAttribute("mensaje3", recordpro);

                request.getRequestDispatcher("admin.jsp").forward(request, response);

            }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServletAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
