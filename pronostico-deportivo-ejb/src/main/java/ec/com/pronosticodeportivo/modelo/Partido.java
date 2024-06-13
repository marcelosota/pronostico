package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the partido database table.
 * 
 */
@Entity
@Table(name="partido",schema = "futbol")
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PARTIDO_PARTIDOID_GENERATOR", sequenceName="PARTIDO_PARTIDO_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARTIDO_PARTIDOID_GENERATOR")
	@Column(name="partido_id")
	private Integer partidoId;

	private String ciudad;

	private String estadio;

	@Column(name="fecha_maxima_registro")
	private Timestamp fechaMaximaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_partido")
	private Date fechaPartido;

	@Column(name="fecha_publicacion")
	private Timestamp fechaPublicacion;

	private String grupo;

	@Column(name="marcador_local")
	private Short marcadorLocal;

	@Column(name="marcador_visitante")
	private Short marcadorVisitante;

	private Boolean notificado;

	@Column(name="posibles_penales")
	private Boolean posiblesPenales;
	
	private String pais;

	@Column(name="penales_local")
	private Short penalesLocal;

	@Column(name="penales_visitante")
	private Short penalesVisitante;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo_local")
	private Equipo equipo1;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo_visitante")
	private Equipo equipo2;

	//bi-directional many-to-one association to Etapa
	@ManyToOne
	@JoinColumn(name="etapa_id")
	private Etapa etapa;

	//bi-directional many-to-one association to Pronostico
	@OneToMany(mappedBy="partido")
	private List<Pronostico> pronosticos;

	public Partido() {
	}

	public Integer getPartidoId() {
		return this.partidoId;
	}

	public void setPartidoId(Integer partidoId) {
		this.partidoId = partidoId;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstadio() {
		return this.estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public Timestamp getFechaMaximaRegistro() {
		return this.fechaMaximaRegistro;
	}

	public void setFechaMaximaRegistro(Timestamp fechaMaximaRegistro) {
		this.fechaMaximaRegistro = fechaMaximaRegistro;
	}

	public Date getFechaPartido() {
		return this.fechaPartido;
	}

	public void setFechaPartido(Date fechaPartido) {
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

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	public Equipo getEquipo1() {
		return this.equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return this.equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public Etapa getEtapa() {
		return this.etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public List<Pronostico> getPronosticos() {
		return this.pronosticos;
	}

	public void setPronosticos(List<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

	public Pronostico addPronostico(Pronostico pronostico) {
		getPronosticos().add(pronostico);
		pronostico.setPartido(this);

		return pronostico;
	}

	public Pronostico removePronostico(Pronostico pronostico) {
		getPronosticos().remove(pronostico);
		pronostico.setPartido(null);

		return pronostico;
	}

	public Boolean getPosiblesPenales() {
		return posiblesPenales;
	}

	public void setPosiblesPenales(Boolean posiblesPenales) {
		this.posiblesPenales = posiblesPenales;
	}

}