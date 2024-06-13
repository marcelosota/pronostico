package ec.com.pronostico.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ec.com.pronostico.pojo.Usuario;
import ec.com.pronostico.utils.Sesion;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7685152379675000333L;

	private String usuario;
	private String contrasena;
	private String usuarioRecuperar;
	private boolean autenticado;
	private Usuario objUsuario = new Usuario();
	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUsuarioRecuperar() {
		return this.usuarioRecuperar;
	}

	public void setUsuarioRecuperar(String usuarioRecuperar) {
		this.usuarioRecuperar = usuarioRecuperar;
	}

	public boolean isAutenticado() {
		return this.autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public LoginBean() {
		Sesion objSesion = new Sesion();
		this.faceContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = ((HttpServletRequest) this.faceContext.getExternalContext().getRequest());
		if ((objSesion.getCedula() != null) && (objSesion.getNombre() != null)) {
			verificarUsuario();
		}
	}

	public void autenticarUsuario() {
		try {
			if ((!getUsuario().isEmpty()) && (!getContrasena().isEmpty())) {
				setAutenticado(this.objUsuario.autenticar(getUsuario(), getContrasena()));
			} else {
				FacesMessage msg = new FacesMessage("Error", "Sus credenciales de autenticación son incorrectos.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String verificarUsuario() {
		String retorno = "";
		try {
			if (isAutenticado()) {
				crearSesionUsuario();

				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				try {
					context.redirect(context.getRequestContextPath() + "/paginas/marcador.jsf");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				retorno = "index";
			}
		} catch (Exception e) {
			logout();
			retorno = "index";
		}
		return retorno;
	}

	private void crearSesionUsuario() {
		this.httpServletRequest.getSession().setAttribute("nombre", this.objUsuario.getObjParticipante().getNombre());
		this.httpServletRequest.getSession().setAttribute("cedula", this.objUsuario.getObjParticipante().getCedula());
	}

	public void logout() {
		if (isAutenticado()) {
			this.httpServletRequest.getSession().removeAttribute("nombre");
			this.httpServletRequest.getSession().removeAttribute("cedula");
			setAutenticado(false);
		} else {
			setAutenticado(false);
		}
	}

	public void recuperarContrasena() {
		String texto = "";
		if (this.objUsuario.recuperarContrasena(getUsuarioRecuperar())) {
			texto = "Se ha enviado una nueva contraseña a su cuenta de correo electrónico";
		} else
			texto = "No se pudo generar una nueva contraseña. Favor contáctese con el administrador del sistema";
		FacesMessage msg = new FacesMessage("Info", texto);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
