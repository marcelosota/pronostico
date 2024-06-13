package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the vw_lista_partidos database table.
 * 
 */
@Entity
@Table(name="vw_lista_partidos",schema = "futbol")
@NamedQuery(name="VwListaPartido.findAll", query="SELECT v FROM VwListaPartido v")
public class VwListaPartido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "contador_registro_id")
	private Long contadorRegistrosId;

	@Column(name="equipo_local")
	private String equipoLocal;

	@Column(name="equipo_visitante")
	private String equipoVisitante;

	private String etapa;

	@Column(name="fecha_partido")
	private Timestamp fechaPartido;

	@Column(name="fecha_publicacion")
	private Timestamp fechaPublicacion;

	private String grupo;

	@Column(name="marcador_local")
	private Short marcadorLocal;

	@Column(name="marcador_visitante")
	private Short marcadorVisitante;

	private Boolean notificado;

	@Column(name="partido_id")
	private Integer partidoId;

	@Column(name="penales_local")
	private Short penalesLocal;

	@Column(name="penales_visitante")
	private Short penalesVisitante;
	
	@Column(name="bandera_local")
	private String banderaLocal;

	@Column(name="bandera_visitante")
	private String banderaVisitante;

	public VwListaPartido() {
	}

	public String getEquipoLocal() {
		return this.equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return this.equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public String getEtapa() {
		return this.etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public Timestamp getFechaPartido() {
		return this.fechaPartido;
	}

	public void setFechaPartido(Timestamp fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public Timestamp getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Timestamp fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Short getMarcadorLocal() {
		return this.marcadorLocal;
	}

	public void setMarcadorLocal(Short marcadorLocal) {
		this.marcadorLocal = marcadorLocal;
	}

	public Short getMarcadorVisitante() {
		return this.marcadorVisitante;
	}

	public void setMarcadorVisitante(Short marcadorVisitante) {
		this.marcadorVisitante = marcadorVisitante;
	}

	public Boolean getNotificado() {
		return this.notificado;
	}

	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}

	public Integer getPartidoId() {
		return this.partidoId;
	}

	public void setPartidoId(Integer partidoId) {
		this.partidoId = partidoId;
	}

	public Short getPenalesLocal() {
		return this.penalesLocal;
	}

	public void setPenalesLocal(Short penalesLocal) {
		this.penalesLocal = penalesLocal;
	}

	public Short getPenalesVisitante() {
		return this.penalesVisitante;
	}

	public void setPenalesVisitante(Short penalesVisitante) {
		this.penalesVisitante = penalesVisitante;
	}

	public Long getContadorRegistrosId() {
		return contadorRegistrosId;
	}

	public void setContadorRegistrosId(Long contadorRegistrosId) {
		this.contadorRegistrosId = contadorRegistrosId;
	}

	public String getBanderaLocal() {
		return banderaLocal;
	}

	public void setBanderaLocal(String banderaLocal) {
		this.banderaLocal = banderaLocal;
	}

	public String getBanderaVisitante() {
		return banderaVisitante;
	}

	public void setBanderaVisitante(String banderaVisitante) {
		this.banderaVisitante = banderaVisitante;
	}


}