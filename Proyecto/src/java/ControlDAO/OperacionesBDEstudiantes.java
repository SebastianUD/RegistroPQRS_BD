package ControlDAO;

import Modelo.PQRSVO;
import ControlConexion.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesBDEstudiantes {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    public OperacionesBDEstudiantes() throws Exception {
        try {
            con = ConexionBD.getConexion();
        } catch (SQLException ex) {
            System.out.println("No se  pudo realizar la conexion");
        }
    }

    //Escribe la PQRS en la base de datos
    public void escribirPQRS(PQRSVO pqrs) throws SQLException, Exception {
        String radicado = pqrs.getRadicado();
        String documento = pqrs.getDocumento();
        String nombre = pqrs.getNombre();
        String telefono = pqrs.getTelefono();
        String correo = pqrs.getCorreo();
        String estado = pqrs.getEstado();
        String tipo = pqrs.getTipo();
        String codigo = pqrs.getCodigo();
        String categoria = pqrs.getCategoria();
        String solicitud = pqrs.getSolicitud();

        String insertar = "INSERT INTO App.PQRS (radicado, documento, nombre, telefono, correo, estado, tipo, codigo, categoria, solicitud, fecha) "
                + "VALUES ('" + radicado + "', '" + documento + "', '" + nombre + "', '" + telefono + "', '" + correo + "', '" + estado + "', '" + tipo + "', '" + codigo + "', '" + categoria + "', '" + solicitud + "', CURRENT_DATE)";

        try {
            //con = (Connection) ConexionBD.getConexion();
            st = con.createStatement();
            st.executeUpdate(insertar); //Actualiza la tabla con lo que se vaya a insertar
            st.close();

        } catch (SQLException ex) {
            throw new SQLException("No se pudo realizar el ingreso", ex);
        }

    }

    //Consulta la PQRS en la base de datos
    public PQRSVO consultarPQRS(String radicado, String documento) throws SQLException, Exception {

        PQRSVO pqrs = null;
        String consulta = "SELECT * FROM App.PQRS WHERE radicado = '" + radicado + "' AND documento = '" + documento + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) { //Si se encontró la consulta se establece el usuario
                pqrs = new PQRSVO();
                pqrs.setRadicado(radicado);
                pqrs.setDocumento(rs.getString("documento"));
                pqrs.setTipo(rs.getString("tipo"));
                pqrs.setNombre(rs.getString("nombre"));
                pqrs.setCategoria(rs.getString("categoria"));
                pqrs.setTelefono(rs.getString("telefono"));
                pqrs.setCorreo(rs.getString("correo"));
                pqrs.setCodigo(rs.getString("codigo"));
                pqrs.setEstado(rs.getString("estado"));
                pqrs.setSolicitud(rs.getString("solicitud"));
                pqrs.setRespuesta(rs.getString("respuesta"));
                pqrs.setFecha(rs.getString("fecharespuesta"));

                return pqrs;
            } else {
                return null; //En caso de que no coincida no retorna algo
            }

        } catch (SQLException ex) {
            return null;
        }

    }

    public void cerrarCnx() throws SQLException {
        rs.close();
        rs = null;
        st.close();
        st = null;
        con.close();
        con = null;
    }
}
