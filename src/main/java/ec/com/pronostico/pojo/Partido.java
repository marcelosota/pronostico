package ec.com.pronostico.pojo;

public class Partido {
  private int id;
  private String pais1;
  private String pais2;
  private String fechaJuego;
  private String etapa;
  private String marcador1;
  private String marcador2;
  
  public int getId() { return this.id; }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getPais1() { return this.pais1; }
  
  public void setPais1(String pais1) {
    this.pais1 = pais1;
  }
  
  public String getPais2() { return this.pais2; }
  
  public void setPais2(String pais2) {
    this.pais2 = pais2;
  }
  
  public String getFechaJuego() { return this.fechaJuego; }
  
  public void setFechaJuego(String fechaJuego) {
    this.fechaJuego = fechaJuego;
  }
  
  public String getEtapa() { return this.etapa; }
  
  public void setEtapa(String etapa) {
    this.etapa = etapa;
  }
  
  public String getMarcador1() { return this.marcador1; }
  
  public void setMarcador1(String marcador1) {
    this.marcador1 = marcador1;
  }
  
  public String getMarcador2() { return this.marcador2; }
  
  public void setMarcador2(String marcador2) {
    this.marcador2 = marcador2;
  }
}
