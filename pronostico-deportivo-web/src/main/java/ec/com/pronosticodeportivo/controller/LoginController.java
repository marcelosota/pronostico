package ec.com.pronosticodeportivo.controller;

import java.io.Serializable;

import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.session.Sesion;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 815707501229190544L;
	@EJB
	private UsuarioServicio usuarioServicio;

	@Size(min = 4, max = 15)
	@NotEmpty
	private String username;

	@Size(min = 3, max = 15)
	@NotEmpty
	private String password;

	private String usuarioRecuperar;

	private final HttpServletRequest httpServletRequest;
	private final FacesContext faceContext;

	
	
	private Usuario usuario;
	
	public LoginController(){
		// TODO Auto-generated constructor stub
		Sesion objSesion = new Sesion();
		faceContext = FacesContext.getCurrentInstance();
		httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
		if(objSesion.getCedula() != null && objSesion.getNombre() != null){
			login();
		}
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuarioRecuperar() {
		return usuarioRecuperar;
	}

	public void setUsuarioRecuperar(String usuarioRecuperar) {
		this.usuarioRecuperar = usuarioRecuperar;
	}

	public String login() {
		usuario = usuarioServicio.autenticarUsuario(getUsername(), getPassword());
		if (usuario != null) {
			crearSesionUsuario();
			return "success";
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Datos de Usuario y/o contraseña incorrectos.");
			FacesContext.getCurrentInstance().addMessage("login:mensajeAlerta", msg);
			return "index";
		}
	}

	private void crearSesionUsuario(){
		httpServletRequest.getSession().setAttribute("nombre", usuario.getNombre() +" "+ usuario.getApellido());
        httpServletRequest.getSession().setAttribute("cedula", usuario.getLogin());
	}
	
	public void recuperarContrasenaCedula() {
		usuarioServicio.recuperarContrasena(getUsuarioRecuperar());
	}
	
	public String recuperarContrasena() {
		return "recuperarContrasena";
	}
	public String registrar() {
		
		return "registrar";
	}

}