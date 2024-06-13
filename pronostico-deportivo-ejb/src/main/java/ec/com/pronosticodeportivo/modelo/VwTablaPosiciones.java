package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the vw_partidos_pronosticos_usuarios database table.
 * 
 */
@Entity
@Table(name="vw_tabla_posiciones",schema = "futbol")
@NamedQuery(name="VwTablaPosiciones.findAll", query="SELECT v FROM VwTablaPosiciones v")
public class VwTablaPosiciones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "posicion_id")
	private Long posicionId;
	
	private String login;

	@Column(name="nombre")
	private String nombre;

	@Column(name="etapa")
	private String etapa;

	@Column(name="puntos_partido")
	private Long puntosPartido;

	@Column(name="aciertos_5")
	private Long aciertosCinco;

	@Column(name="aciertos_3")
	private Long aciertosTres;

	public Long getPosicionId() {
		return posicionId;
	}

	public void setPosicionId(Long posicionId) {
		this.posicionId = posicionId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public Long getPuntosPartido() {
		return puntosPartido;
	}

	public void setPuntosPartido(Long puntosPartido) {
		this.puntosPartido = puntosPartido;
	}

	public Long getAciertosCinco() {
		return aciertosCinco;
	}

	public void setAciertosCinco(Long aciertosCinco) {
		this.aciertosCinco = aciertosCinco;
	}

	public Long getAciertosTres() {
		return aciertosTres;
	}

	public void setAciertosTres(Long aciertosTres) {
		this.aciertosTres = aciertosTres;
	}

}