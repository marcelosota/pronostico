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

	private Usuario usuarioSeleccionado;

	@PostConstruct
	protected void init() {
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
		System.out.println("Usuario guardado");
		if(usuarioSeleccionado.getUsuarioId() != null) {
			usuarioServicio.update(usuarioSeleccionado);
		}else {
			usuarioSeleccionado.setFechaRegistro(Calendar.getInstance().getTime());
			usuarioServicio.create(usuarioSeleccionado);
		}

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
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}
	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
}
