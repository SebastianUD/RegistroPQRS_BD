package ControlDAO;

import ControlConexion.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesBDAdmin {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    public OperacionesBDAdmin() throws Exception {
        try {
            con = ConexionBD.getConexion();
        } catch (SQLException ex) {
            System.out.println("No se  pudo realizar la conexion");
        }
    }

    public void modificarDatos(String radicado, String estado, String respuesta) throws SQLException {
        //Consulta para modificar los datos de estado y la respuesta 
        String consulta = "UPDATE App.PQRS SET estado = '" + estado + "', respuesta = '" + respuesta + "', fecharespuesta = CURRENT_DATE WHERE radicado = '" + radicado + "'";
        try {
            st = con.createStatement();
            st.executeUpdate(consulta);
        } catch (SQLException ex) {
            throw new SQLException("No se pudo realizar la actualizacion ex: ", ex);
        }
    }

    public String record(String fecha) throws SQLException { //Contar cuantas PQRS hay en una fecha
        String consulta = "SELECT COUNT(*) AS count FROM App.PQRS WHERE fecharespuesta = '" + fecha + "'";
        String record = "";

        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) { //Si no encuentra el valor 0, y el string llevara el 0 guardado
                int contador = rs.getInt("count");
                record = String.valueOf(contador);
            }

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }

        System.out.println("rec" + record);
        return record;

    }

    public String consultarSolicitud(String radicado) throws SQLException, Exception {

        String consulta = "SELECT * FROM App.PQRS WHERE radicado = '" + radicado + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) { //Si se encontró la consulta se establece el usuario
                return rs.getString("solicitud");
            } else {
                return "mal"; //En caso de que no coincida no retorna algo
            }

        } catch (SQLException ex) {
            return null;
        }

    }

    public String mostrarDatos() {
        String rta = "RADICADO\tDOCUMENTO\tTIPO\tNOMBRE\tCATEGORÍA\tTELÉFONO\tCORREO\tCÓDIGO\tESTADO\tSOLICITUD\tFECHA<br>";
        String consulta = "SELECT * FROM App.PQRS WHERE estado = 'En proceso' ORDER BY fecha ASC";
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                String radicado = rs.getString("radicado");
                String documento = rs.getString("documento");
                String tipo = rs.getString("tipo");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String codigo = rs.getString("codigo");
                String estado = rs.getString("estado");
                String solicitud = rs.getString("solicitud");
                String fecha = rs.getString("fecha");
                rta += radicado + "\t" + documento + "\t" + tipo + "\t" + nombre + "\t" + categoria + "\t" + telefono + "\t" + correo + "\t" + codigo + "\t" + estado + "\t" + solicitud + "\t" + fecha + "<br>";
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta" + ex);
        }
        return rta;
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
