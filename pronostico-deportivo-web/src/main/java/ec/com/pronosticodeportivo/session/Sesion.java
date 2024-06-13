package ec.com.pronosticodeportivo.session;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

public class Sesion {
	private String nombre;
	private String cedula;
	private final FacesContext faceContext;
	private final HttpServletRequest httpServletRequest;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
	public Sesion(){
		faceContext=FacesContext.getCurrentInstance();
		httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("nombre")!=null)
        {
            setNombre(httpServletRequest.getSession().getAttribute("nombre").toString());
            setCedula(httpServletRequest.getSession().getAttribute("cedula").toString());
        }
	}
	public boolean cerrarSession()
    { 
		try{
			if(httpServletRequest.getSession().getAttribute("nombre") != null){
		        httpServletRequest.getSession().removeAttribute("nombre");
		        httpServletRequest.getSession().removeAttribute("cedula");
			}
			limpiarDatos();
			return true;
		} catch(NullPointerException e){
			limpiarDatos();
			return false;
		}
    }
	
	private void limpiarDatos(){
		setNombre(null);
		setCedula(null);
	}
}
