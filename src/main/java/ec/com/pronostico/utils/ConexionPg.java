package ec.com.pronostico.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexionPg
{
  //private final String url = "jdbc:postgresql://";
  //private final String driver = "org.postgresql.Driver";
  private String pgHost;
  private String pgPuerto;
  private String pgBaseDatos;
  private String pgUsuario;
  private String pgContrasena;
  private ResourceBundle objPropiedades = new ArchivoProperties().getPropiedades();
  public java.sql.Connection conexion;
  
  public ConexionPg() {
    try {
      Class.forName("org.postgresql.Driver").newInstance();
      asignarValores();
      this.conexion = DriverManager.getConnection("jdbc:postgresql://" + this.pgHost + ":" + this.pgPuerto + "/" + this.pgBaseDatos, this.pgUsuario, this.pgContrasena);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    catch (InstantiationException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
  
  private void asignarValores() { this.pgHost = this.objPropiedades.getString("pgHost");
    this.pgPuerto = this.objPropiedades.getString("pgPuerto");
    this.pgBaseDatos = this.objPropiedades.getString("pgBaseDatos");
    this.pgUsuario = this.objPropiedades.getString("pgUsuario");
    this.pgContrasena = this.objPropiedades.getString("pgContrasena");
  }
}
