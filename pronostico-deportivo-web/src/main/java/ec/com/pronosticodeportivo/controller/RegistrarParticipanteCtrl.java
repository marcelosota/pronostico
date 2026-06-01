package ec.com.pronosticodeportivo.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.com.pronosticodeportivo.dao.UsuarioDao;
import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.util.CorreoElectronico;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "registrarParticipanteCtrl")
@ViewScoped
public class RegistrarParticipanteCtrl implements Serializable {

	/**
	 * 
	 */
	
	@EJB
	private UsuarioDao usuarioDao;
	
	@EJB
	private UsuarioServicio usuarioServicio;
	
	private static final long serialVersionUID = 7058602135192992714L;
	private List<Usuario> listaUsuario;
	private List<Usuario> filtrarUsuario;

	private Usuario usuarioSeleccionado;
	private boolean filtroGlobal;

	@PostConstruct
	protected void init() {
		filtroGlobal = false;
		establecerTablas();
		
		usuarioSeleccionado = new Usuario();

	}
	private void establecerTablas() {
		listaUsuario = new ArrayList<>();
		listaUsuario = usuarioDao.getUsuarios();
		
	}
	public void registroSeleccionado() {
		System.out.println(usuarioSeleccionado.getLogin());
		System.out.println(usuarioSeleccionado.getNombre()+" "+usuarioSeleccionado.getApellido());
	}
	
	public void guardarUsuario() {
		usuarioSeleccionado.setContrasena(usuarioServicio.encriptarContrasena(usuarioSeleccionado.getContrasena()));

		if(usuarioSeleccionado.getUsuarioId() != null) {
			usuarioServicio.update(usuarioSeleccionado);
	
		}else {
			usuarioSeleccionado.setFechaRegistro(Calendar.getInstance().getTime());
			usuarioServicio.create(usuarioSeleccionado);
		}
		
		String asunto = null;
		StringBuilder correo = new StringBuilder("Hola <b>").append(usuarioSeleccionado.getNombre()).append(". </b><br/>");
		
		if("A".equals(usuarioSeleccionado.getEstado())) {
			asunto = "Usuario habilitado";
			correo.append("Quienes conformamos pronostico deportivo te damos la mas cordial bienvenida a participar en este emocionante torneo, ").
			append("tu usuario se encuentra habilitado con los siguientes datos:<br/>").
			append("<b>Usuario: </b> ").append(usuarioSeleccionado.getLogin()).append("<br/>").
			append("<b>Correo electrónico: </b> ").append(usuarioSeleccionado.getEmail()).append("<br/><br/>").
			append("Para iniciar debes cambiar tu contraseña, utilizando la opción recuperar contraseña que está disponible en la pantalla principal, ").
			append("ahí deberás ingresar tu número de cédula y el correo electrónico que nos proporcionaste para el registro.<br/><br/>").
			append("Te deseamos el mayor de los éxitos en tus pronósticos. Nos vemos en el tablero de marcadores!!!");
			
		}else {
			asunto = "Participante creado";
			correo.append( "Su usuario ha sido creado en la plataforma de pronósticos, pero aún no está habilitado para poder "). 
			append("ingresar sus pronósticos, si usted ya envió su comprobante de inscripción por favor comúnicante a través del ").
			append("grupo de WhatsApp o directamente conmigo para ayudarte con la habilitación del usuario. <br/>");
		}
		
		correo.append("<br/><br/>Saludos.");
		CorreoElectronico.enviarConGMail(usuarioSeleccionado.getEmail(), asunto, correo.toString());
		usuarioSeleccionado = null;
	}
	
	public String encriptarConstrasena(String contrasena){
		String resp = "";

		try {
			// 3. Se crea el metodo de encriptacion SHA1
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(contrasena.getBytes("UTF-8"));

			// 4. Se pone la respuesta
			resp = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return resp;
	}
	
	public void toggleGlobalFilter() {
        setFiltroGlobal(!isFiltroGlobal());
    }
	
	public void nuevoParticipante() {
		usuarioSeleccionado = new Usuario();
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public List<Usuario> getFiltrarUsuario() {
		return filtrarUsuario;
	}
	public void setFiltrarUsuario(List<Usuario> filtrarUsuario) {
		this.filtrarUsuario = filtrarUsuario;
	}
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}
	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	public boolean isFiltroGlobal() {
		return filtroGlobal;
	}
	public void setFiltroGlobal(boolean filtroGlobal) {
		this.filtroGlobal = filtroGlobal;
	}
	
}
