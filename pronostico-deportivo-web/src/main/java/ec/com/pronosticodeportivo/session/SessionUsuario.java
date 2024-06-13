package ec.com.pronosticodeportivo.session;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("sessionUsuario")
@SessionScoped
public class SessionUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2971581882948016756L;
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public String getCorreoElectronicoUsuario() {
		return correoElectronicoUsuario;
	}
	public void setCorreoElectronicoUsuario(String correoElectronicoUsuario) {
		this.correoElectronicoUsuario = correoElectronicoUsuario;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdTorneo() {
		return idTorneo;
	}
	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}
	private String cedulaUsuario;
	private String nombre;
	private String apellidoUsuario;
	private String correoElectronicoUsuario;
	private int idUsuario;
	private int idTorneo;
}
