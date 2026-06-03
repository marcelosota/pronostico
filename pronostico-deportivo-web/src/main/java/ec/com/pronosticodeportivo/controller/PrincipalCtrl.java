package ec.com.pronosticodeportivo.controller;

import java.io.IOException;
import java.io.Serializable;

import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.PartidoServicio;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.session.Sesion;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "principalCtrl")
@ViewScoped
public class PrincipalCtrl implements Serializable {

	/**
	 * 
	 */

//	@EJB
//	private VwListaPartidoServicio vwListaPartidoServicio;

	@EJB
	private PartidoServicio partidoServicio;

	@EJB
	private UsuarioServicio usuarioServicio;

//	@EJB
//	private VwPartidosPronosticosUsuarioServicio vwPronosticoServicio;

	private static final long serialVersionUID = 7058602135192992714L;

	@SuppressWarnings("unused")
	private Usuario usuario;
	private Sesion objSesion = new Sesion();
	private Boolean permiso;
	

	@PostConstruct
	public void init() {
		System.out.println("Principal");
		if(objSesion != null) {
			if(objSesion.getCedula().equals("1714284856") || objSesion.getCedula().equals("1713407490"))
				setPermiso(Boolean.TRUE);
			else
				setPermiso(Boolean.FALSE);
		}else
			cerrarSesion();
	}

	public void cerrarSesion() {
		objSesion.cerrarSession();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/index.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cambiarPassword() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/cambiarPassword.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void resultadosOficiales() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/resultados.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void notificaciones() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/notificaciones.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void registrarParticipantes() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/registrarParticipantes.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String principal() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/marcador.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean getPresentarRegistroResultadosOficiales() {
		if (objSesion.getCedula() != null && objSesion.getNombre() != null) {
			usuario=usuarioServicio.buscarUsuarioPorLogin(objSesion.getCedula());
		}
		return usuario.getLogin().equals("1714284856") || usuario.getLogin().equals("1713407490");
	}

	public Boolean getPermiso() {
		return permiso;
	}

	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}



}
