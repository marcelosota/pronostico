package ec.com.pronosticodeportivo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ec.com.pronosticodeportivo.dao.UsuarioDao;
import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.session.Sesion;
import ec.com.pronosticodeportivo.util.CorreoElectronico;
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
	@EJB
	private UsuarioDao usuarioDao;

	@Size(min = 4, max = 15)
	@NotEmpty
	private String username;

	@Size(min = 3, max = 15)
	@NotEmpty
	private String password;

	private String usuarioRecuperar;
	private String correoRecuperar;

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

	public String getCorreoRecuperar() {
		return correoRecuperar;
	}


	public void setCorreoRecuperar(String correoRecuperar) {
		this.correoRecuperar = correoRecuperar;
	}


	public String login() {
		System.out.println(getPassword());
		usuario = usuarioServicio.autenticarUsuario(getUsername(), getPassword());
		if (usuario != null && "A".equals(usuario.getEstado())) {
			crearSesionUsuario();
			return "success";
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Datos de Usuario y/o contraseña incorrectos.");
			FacesContext.getCurrentInstance().addMessage("login:mensajeAlerta", msg);
			return "index";
		}
	}

	private void crearSesionUsuario(){
		httpServletRequest.getSession().setAttribute("nombre", usuario.getNombre() +" "+ usuario.getApellido());
        httpServletRequest.getSession().setAttribute("cedula", usuario.getLogin());
	}
	
	public void recuperarContrasenaCedula() {
		Usuario cambioClave=usuarioServicio.buscarUsuarioPorLogin(getUsuarioRecuperar());
		if(cambioClave != null && cambioClave.getEmail().equals(getCorreoRecuperar())) {
			String nuevaClave=usuarioServicio.generarContrasena();
			cambioClave.setContrasena(usuarioServicio.encriptarContrasena(nuevaClave));
			usuarioServicio.update(cambioClave);
		
			String asunto = "Recuperación contraseña";
			StringBuilder textoMensaje = new StringBuilder("Hola ").append(cambioClave.getNombre()) 
				.append("<br/>Se ha generado automáticamente un nueva contraseña. Los nuevos datos de acceso son:<br/>")
				.append("<b>Usuario: </b>").append(usuarioRecuperar)
				.append("<br/><b>Contraseña: </b>").append(nuevaClave)
				.append("<br/><br/>Con esta información inicia sesión y una vez hayas ingresado a la aplicaicón podrás cambiar la contraseña.");
			List<String> to=new ArrayList<String>();
			to.add(cambioClave.getEmail());
			CorreoElectronico.enviarConGMail(cambioClave.getEmail(), asunto, textoMensaje.toString());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",	"Se ha enviado una nueva contraseña a su correo electrónico.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "No se ha encontrado un usuario con los parámetros de búsqueda proporcionados");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public String recuperarContrasena() {
		return "recuperarContrasena";
	}
	public String registrar() {
		
		return "registrar";
	}

}