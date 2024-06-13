package ec.com.pronosticodeportivo.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Mensaje {

	private List<String> destinatarios;
	private String textoMensaje;
	private String asunto;
	private String remitente;
//	private String usuarioCorreo;
//	private String passwordCorreo;
	private boolean tieneAdjunto;
	private HashMap<String, File> listaAdjuntos;
	
	public List<String> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getTextoMensaje() {
		return textoMensaje;
	}
	public void setTextoMensaje(String textoMensaje) {
		this.textoMensaje = textoMensaje;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
//	public String getUsuarioCorreo() {
//		return usuarioCorreo;
//	}
//	public void setUsuarioCorreo(String usuarioCorreo) {
//		this.usuarioCorreo = usuarioCorreo;
//	}
//	public String getPasswordCorreo() {
//		return passwordCorreo;
//	}
//	public void setPasswordCorreo(String passwordCorreo) {
//		this.passwordCorreo = passwordCorreo;
//	}
	public boolean isTieneAdjunto() {
		return tieneAdjunto;
	}
	public void setTieneAdjunto(boolean tieneAdjunto) {
		this.tieneAdjunto = tieneAdjunto;
	}
	public HashMap<String, File> getListaAdjuntos() {
		return listaAdjuntos;
	}
	public void setListaAdjuntos(HashMap<String, File> listaAdjuntos) {
		this.listaAdjuntos = listaAdjuntos;
	}
	
}
