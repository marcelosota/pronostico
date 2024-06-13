package ec.com.pronostico.pojo;

import java.sql.Date;

public class Resultado {
  private String etapa;
  private String pais1;
  private String pais2;
  private int marcador1;
  private int marcador2;
  private String pronostico1;
  private String pronostico2;
  private int puntos;
  private Date fechaPartido;
  
  public String getEtapa() { return this.etapa; }
  
  public void setEtapa(String etapa) {
    this.etapa = etapa;
  }
  
  public String getPais1() { return this.pais1; }
  
  public void setPais1(String pais1) {
    this.pais1 = pais1;
  }
  
  public String getPais2() { return this.pais2; }
  
  public void setPais2(String pais2) {
    this.pais2 = pais2;
  }
  
  public int getMarcador1() { return this.marcador1; }
  
  public void setMarcador1(int marcador1) {
    this.marcador1 = marcador1;
  }
  
  public int getMarcador2() { return this.marcador2; }
  
  public void setMarcador2(int marcador2) {
    this.marcador2 = marcador2;
  }
  
  public String getPronostico1() { return this.pronostico1; }
  
  public void setPronostico1(String pronostico1) {
    this.pronostico1 = pronostico1;
  }
  
  public String getPronostico2() { return this.pronostico2; }
  
  public void setPronostico2(String pronostico2) {
    this.pronostico2 = pronostico2;
  }
  
  public int getPuntos() { return this.puntos; }
  
  public void setPuntos(int puntos) {
    this.puntos = puntos;
  }
  
  public Date getFechaPartido() { return this.fechaPartido; }
  
  public void setFechaPartido(Date fechaPartido) {
    this.fechaPartido = fechaPartido;
  }
}
