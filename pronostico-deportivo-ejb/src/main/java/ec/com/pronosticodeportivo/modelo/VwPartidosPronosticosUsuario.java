package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="vw_partidos_pronosticos_usuarios",schema = "futbol")
@NamedQuery(name="VwPartidosPronosticosUsuario.findAll", query="SELECT v FROM VwPartidosPronosticosUsuario v")
public class VwPartidosPronosticosUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "contador_registro_id")
	private Long contadorRegistrosId;
	
	private String etapa;

	@Column(name="fecha_partido")
	private Timestamp fechaPartido;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	private String grupo;

	private String local;

	private String login;

	@Column(name="marcador_local")
	private Short marcadorLocal;

	@Column(name="marcador_visitante")
	private Short marcadorVisitante;

	private String nombre;

	private Boolean notificado;

	@Column(name="partido_id")
	private Integer partidoId;

	@Column(name="penales_local")
	private Short penalesLocal;

	@Column(name="penales_visitante")
	private Short penalesVisitante;

	@Column(name="pronostico_local")
	private Short pronosticoLocal;

	@Column(name="pronostico_visitante")
	private Short pronosticoVisitante;

	@Column(name="bandera_local")
	private String banderaLocal;

	@Column(name="bandera_visitante")
	private String banderaVisitante;

	@Column(name="pronostico_penales_visitante")
	private Short pronosticoPenalesVisitante;
	
	@Column(name="pronostico_penales_local")
	private Short pronosticoPenalesLocal;
	
	@Column(name="usuario_id")
	private Integer usuarioId;

	private String visitante;

	
	@Column(name="puntaje")
	private Short puntaje;
	
	@Column(name="posibles_penales")
	private Boolean posiblesPenales;

	
	public VwPartidosPronosticosUsuario() {
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

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Short getPronosticoLocal() {
		return this.pronosticoLocal;
	}

	public void setPronosticoLocal(Short pronosticoLocal) {
		this.pronosticoLocal = pronosticoLocal;
	}

	public Short getPronosticoVisitante() {
		return this.pronosticoVisitante;
	}

	public void setPronosticoVisitante(Short pronosticoVisitante) {
		this.pronosticoVisitante = pronosticoVisitante;
	}

	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getVisitante() {
		return this.visitante;
	}

	public void setVisitante(String visitante) {
		this.visitante = visitante;
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

	public Short getPronosticoPenalesVisitante() {
		return pronosticoPenalesVisitante;
	}

	public void setPronosticoPenalesVisitante(Short pronosticoPenalesVisitante) {
		this.pronosticoPenalesVisitante = pronosticoPenalesVisitante;
	}

	public Short getPronosticoPenalesLocal() {
		return pronosticoPenalesLocal;
	}

	public void setPronosticoPenalesLocal(Short pronosticoPenalesLocal) {
		this.pronosticoPenalesLocal = pronosticoPenalesLocal;
	}

	public Short getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Short puntaje) {
		this.puntaje = puntaje;
	}

	public Boolean getPosiblesPenales() {
		return posiblesPenales;
	}

	public void setPosiblesPenales(Boolean posiblesPenales) {
		this.posiblesPenales = posiblesPenales;
	}

}