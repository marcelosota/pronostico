package ec.com.pronostico.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexionMSSQL
{
  //private final String url = "jdbc:sqlserver://";
  private String msHost;
  private String msPuerto;
  private String msBaseDatos;
  private String msUsuario;
  private String msContrasena;
  private ResourceBundle objPropiedades = new ArchivoProperties().getPropiedades();
  public java.sql.Connection conexion;
  
  public ConexionMSSQL() {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      asignarValores();
      this.conexion = DriverManager.getConnection("jdbc:sqlserver://" + this.msHost + ":" + this.msPuerto + ";databaseName=" + this.msBaseDatos, this.msUsuario, this.msContrasena);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  private void asignarValores() { this.msHost = this.objPropiedades.getString("msHost");
    this.msPuerto = this.objPropiedades.getString("msPuerto");
    this.msBaseDatos = this.objPropiedades.getString("msBaseDatos");
    this.msUsuario = this.objPropiedades.getString("msUsuario");
    this.msContrasena = this.objPropiedades.getString("msContrasena");
  }
}
