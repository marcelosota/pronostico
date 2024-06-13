package ec.com.pronosticodeportivo.controller;

import java.io.IOException;
import java.io.Serializable;

import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.session.Sesion;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Named(value = "resetearClaveCtrl")
@ViewScoped
public class ResetearClaveCtrl implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5483339759261617159L;

	@EJB
	private UsuarioServicio usuarioServicio;
	
	private Usuario usuarioModificacion;

	@NotEmpty
	private String claveAnterior;

	@Size(min = 4, max = 10)
	@NotEmpty
	private String nuevaClave;

	@Size(min = 4, max = 10)
	@NotEmpty
	private String nuevaClaveConfirmacion;
	
	private Sesion objSesion = new Sesion();

	
	@PostConstruct
	protected void init() {
		if (objSesion.getCedula() != null && objSesion.getNombre() != null) {
			usuarioModificacion=usuarioServicio.buscarUsuarioPorLogin(objSesion.getCedula());
		}
		
	}
	@SuppressWarnings("unused")
	public void cambiarClave() {
		if(usuarioServicio.encriptarContrasena(claveAnterior).equals(usuarioModificacion.getContrasena())) {
		if (nuevaClave.equals(nuevaClaveConfirmacion)) {
			usuarioModificacion.setContrasena(usuarioServicio.encriptarContrasena(nuevaClave));
			usuarioServicio.update(usuarioModificacion);
			objSesion.cerrarSession();
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			try {
				context.redirect(context.getRequestContextPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			FacesContext.getCurrentInstance().
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Cambio Clave", "Nueva Clave y Confirmación deben ser iguales.")); 
		}}else {
			FacesContext.getCurrentInstance().
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Cambio Clave", "Clave Actual Incorrecta.")); 
			
		}
	}

	public void cancelar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath()+"/paginas/marcador.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getClaveAnterior() {
		return claveAnterior;
	}
	public void setClaveAnterior(String claveAnterior) {
		this.claveAnterior = claveAnterior;
	}
	public String getNuevaClave() {
		return nuevaClave;
	}
	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}
	public String getNuevaClaveConfirmacion() {
		return nuevaClaveConfirmacion;
	}
	public void setNuevaClaveConfirmacion(String nuevaClaveConfirmacion) {
		this.nuevaClaveConfirmacion = nuevaClaveConfirmacion;
	}

}
