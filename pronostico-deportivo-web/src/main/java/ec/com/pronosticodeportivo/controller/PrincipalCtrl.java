package ec.com.pronosticodeportivo.controller;

import java.io.IOException;
import java.io.Serializable;

import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.PartidoServicio;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.servicios.VwListaPartidoServicio;
import ec.com.pronosticodeportivo.servicios.VwPartidosPronosticosUsuarioServicio;
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

	@EJB
	private VwListaPartidoServicio vwListaPartidoServicio;

	@EJB
	private PartidoServicio partidoServicio;

	@EJB
	private UsuarioServicio usuarioServicio;

	@EJB
	private VwPartidosPronosticosUsuarioServicio vwPronosticoServicio;

	private static final long serialVersionUID = 7058602135192992714L;

	@SuppressWarnings("unused")
	private Usuario usuario;
	private Sesion objSesion = new Sesion();

	/*
				<p:menuitem value="Ingresar Resultados Oficiales" action="#{principalCtrl.resultadosOficiales()}" 
				rendered="#{principalCtrl.presentarRegistroResultadosOficiales}" />
				<p:menuitem value="Salir" action="#{principalCtrl.cerrarSesion()}" />
*/
	@PostConstruct
	public void init() {
		System.out.println("Principal");
	}

	public String cerrarSesion() {
		objSesion.cerrarSession();
		return "salir";
	}

	public void cambiarPassword() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/cambiarPassword.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "salir";
	}
	public void resultadosOficiales() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/resultados.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "salir";
	}
	public void principal() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/paginas/marcador.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "salir";
	}

	public Boolean getPresentarRegistroResultadosOficiales() {
		if (objSesion.getCedula() != null && objSesion.getNombre() != null) {
			usuario=usuarioServicio.buscarUsuarioPorLogin(objSesion.getCedula());
		}
		return usuario.getLogin().equals("1714284856") || usuario.getLogin().equals("1713407490");
	}



}
