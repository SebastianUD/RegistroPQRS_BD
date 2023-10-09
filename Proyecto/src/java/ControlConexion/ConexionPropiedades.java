package ControlConexion;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;

public class ConexionPropiedades {
    private static Properties mispropiedades = null;
    private static File archivo = null;

    public static Properties cargar(String rutaArchivo) throws Exception {
        if (mispropiedades == null) {
            archivo = new File(rutaArchivo);
            FileInputStream archivoprop = new FileInputStream(archivo);
            Properties propiedadesCargadas = new Properties();
            propiedadesCargadas.load(archivoprop);
            archivoprop.close();
            if (!propiedadesCargadas.isEmpty()) {
                mispropiedades = propiedadesCargadas;
            }
        }
        return mispropiedades;
    }
}
