package ec.com.pronosticodeportivo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.util.Correo;
import ec.com.pronosticodeportivo.util.Mensaje;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Named("registroController")
@RequestScoped
public class RegistroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 815707501229190544L;
	@EJB
	private UsuarioServicio usuarioServicio;

	private Usuario usuarioRegistro;
	@Size(min = 4, max = 10)
	@NotEmpty
	private String username;

	@Size(min = 4, max = 10)
	@NotEmpty
	private String password;

	@Size(min = 4, max = 10)
	@NotEmpty
	private String confirmPassword;

	@Size(min = 2, max = 128)
	@NotEmpty
	private String nombre;

	@Size(min = 2, max = 128)
	@NotEmpty
	private String apellido;

	@Size(min = 2, max = 10)
	@NotEmpty
	private String telefono;

	@Size(min = 2, max = 256)
	@NotEmpty
	private String email;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsername(String name) {
		this.username = name;
		this.usuario.setLogin(name);
	}

	public String getUsername() {
		return username;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.usuario.setContrasena(usuarioServicio.encriptarContrasena(password));

	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.usuario.setNombre(nombre);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
		this.usuario.setApellido(apellido);

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
		this.usuario.setTelefono(telefono);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.usuario.setEmail(email);
	}

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String registrar() {
		if (usuario != null && password.equals(confirmPassword)) {
			Usuario usuarioExistente = usuarioServicio.buscarUsuarioPorLogin(username);

			if (usuarioExistente != null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Registrado", "Usuario ya se encuentra registrado.");
				FacesContext.getCurrentInstance().addMessage("loginForm:growl", msg);
				return "existente";
			} else {
				usuario.setFechaRegistro(Calendar.getInstance().getTime());
				usuario.setEstado("A");
				usuarioServicio.create(usuario);
				enviarCorreo();
				return "regresar";
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Por favor revisar la información");
			FacesContext.getCurrentInstance().addMessage("loginForm:mensajeAlerta", msg);
			return "failure";
		}
	}

	private void enviarCorreo() {
		Mensaje mensajeCorreo = new Mensaje();
		mensajeCorreo.setAsunto("Registro");
		mensajeCorreo.setRemitente("christian19810414@gmail.com");
//		mensajeCorreo.setUsuarioCorreo("christian19810414");
//		mensajeCorreo.setPasswordCorreo("sqhqynfsugzmkedu");
		mensajeCorreo.setTextoMensaje("<h1>Hola a todos</h1>");
		List<String> destinatarios = new ArrayList<String>();
		destinatarios.add("christian19810414@gmail.com");
		destinatarios.add("christian19810414@hotmail.com");
		mensajeCorreo.setDestinatarios(destinatarios);
		mensajeCorreo.setTieneAdjunto(false);

		Correo envioCorreo = new Correo();
		envioCorreo.enviarCorreo(mensajeCorreo);

	}

}