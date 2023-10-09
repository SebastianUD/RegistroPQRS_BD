package ControlConexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static Connection cn = null;
    private static Driver driver = new org.apache.derby.jdbc.ClientDriver();
    private static String URLBD = "jdbc:derby://localhost:1527/PQRS;create=true;user=APP;password=APP";
    private static String rutaArchivoPropiedades = "C:\\Users\\andre\\OneDrive\\Escritorio\\Proyecto\\propiedades\\base.properties";
    
    public static Connection getConexion() throws SQLException, Exception {
        try {
            Properties propiedad = ConexionPropiedades.cargar(rutaArchivoPropiedades);
            
            String usuario = propiedad.getProperty("usuario");
            String contrasena = propiedad.getProperty("contrasena");

            DriverManager.registerDriver(driver);
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            throw new SQLException("No se puede cargar el controlador");
        }
        return cn;
    }
}
