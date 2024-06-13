package ec.com.pronostico.utils;

import javax.servlet.http.HttpServletRequest;

public class Sesion
{
  private String nombre;
  private String cedula;
  private final javax.faces.context.FacesContext faceContext;
  private final HttpServletRequest httpServletRequest;
  
  public String getNombre()
  {
    return this.nombre;
  }
  
  public void setNombre(String nombre) { this.nombre = nombre; }
  
  public String getCedula() {
    return this.cedula;
  }
  
  public void setCedula(String cedula) { this.cedula = cedula; }
  

  public Sesion()
  {
    this.faceContext = javax.faces.context.FacesContext.getCurrentInstance();
    this.httpServletRequest = ((HttpServletRequest)this.faceContext.getExternalContext().getRequest());
    if (this.httpServletRequest.getSession().getAttribute("nombre") != null)
    {
      setNombre(this.httpServletRequest.getSession().getAttribute("nombre").toString());
      setCedula(this.httpServletRequest.getSession().getAttribute("cedula").toString());
    }
  }
  
  public boolean cerrarSession() {
    try {
      if (this.httpServletRequest.getSession().getAttribute("nombre") != null) {
        this.httpServletRequest.getSession().removeAttribute("nombre");
        this.httpServletRequest.getSession().removeAttribute("cedula");
      }
      limpiarDatos();
      return true;
    } catch (NullPointerException e) {
      limpiarDatos(); }
    return false;
  }
  
  private void limpiarDatos()
  {
    setNombre(null);
    setCedula(null);
  }
}
