package ec.com.pronostico.modelo;

import java.util.Date;

public class Torneo {
  private int torneoId;
  private String nombre;
  private String urlFixture;
  private String urlTabla;
  private Date fechaInicio;
  private Date fechaFinalizacion;
  private String log;
  private String ganador;
  private int estado;
  
  public int getTorneoId() {
    return this.torneoId;
  }
  
  public void setTorneoId(int torneoId) { this.torneoId = torneoId; }
  
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(String nombre) { this.nombre = nombre; }
  
  public String getUrlFixture() {
    return this.urlFixture;
  }
  
  public void setUrlFixture(String urlFixture) { this.urlFixture = urlFixture; }
  
  public String getUrlTabla() {
    return this.urlTabla;
  }
  
  public void setUrlTabla(String urlTabla) { this.urlTabla = urlTabla; }
  
  public Date getFechaInicio() {
    return this.fechaInicio;
  }
  
  public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
  
  public Date getFechaFinalizacion() {
    return this.fechaFinalizacion;
  }
  
  public void setFechaFinalizacion(Date fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; }
  
  public String getLog() {
    return this.log;
  }
  
  public void setLog(String log) { this.log = log; }
  
  public String getGanador() {
    return this.ganador;
  }
  
  public void setGanador(String ganador) { this.ganador = ganador; }
  
  public int getEstado() {
    return this.estado;
  }
  
  public void setEstado(int estado) { this.estado = estado; }
}
