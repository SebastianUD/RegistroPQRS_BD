package ControlDAO;

import ControlConexion.ConexionBD;
import Modelo.UserVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesBDLogin {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    public OperacionesBDLogin() throws Exception {
        try {
            con = ConexionBD.getConexion();
        } catch (SQLException ex) {
            System.out.println("No se  pudo realizar la conexion");
        }
    }

    public UserVO consultarUsuario(String usuario, String tipo, String contrasena) throws Exception { //Tipo UserVO,porque se devolverá un user
        UserVO user = null;
        String consulta = "SELECT * FROM App.USUARIOS WHERE usuario = '" + usuario + "' AND tipo = '" + tipo + "' AND contrasena = '" + contrasena + "'";

        System.out.println(usuario + tipo + contrasena);

        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            if (rs.next()) { //Si se encontró la consulta se establece el usuario
                user = new UserVO();
                user.setUsuario(rs.getString("usuario"));
                user.setTipo(rs.getString("tipo"));
                user.setContrasena(rs.getString("contrasena"));
                user.setNombre(rs.getString("nombre"));
                System.out.println(rs.getString("nombre"));
                user.setCorreo(rs.getString("correo"));
                user.setTelefono(rs.getString("telefono"));
                user.setTipodocumento(rs.getString("tipodocumento"));
                user.setCodigo(rs.getString("codigo"));
                user.setDocumento(rs.getString("documento"));

                return user;

            } else {
                return null; //En caso de que no coincida no retorna algo
            }

        } catch (SQLException ex) {
            return null;
        }

    }

    public String mostrarDatos() { //Datos separados en un string
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
