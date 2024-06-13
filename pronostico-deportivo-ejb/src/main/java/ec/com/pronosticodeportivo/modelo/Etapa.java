package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the etapa database table.
 * 
 */
@Entity
@Table(name="etapa",schema = "futbol")
@NamedQuery(name="Etapa.findAll", query="SELECT e FROM Etapa e")
public class Etapa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ETAPA_ETAPAID_GENERATOR", sequenceName="ETAPA_ETAPA_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ETAPA_ETAPAID_GENERATOR")
	@Column(name="etapa_id")
	private Integer etapaId;

	@Column(name="costo_participacion")
	private BigDecimal costoParticipacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	private String nombre;

	private Boolean estado;

	private Boolean penales;
	//bi-directional many-to-one association to Torneo
	@ManyToOne
	@JoinColumn(name="torneo_id")
	private Torneo torneo;

	//bi-directional many-to-one association to Inscripcion
	@OneToMany(mappedBy="etapa")
	private List<Inscripcion> inscripcions;

	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="etapa")
	private List<Partido> partidos;

	public Etapa() {
	}

	public Integer getEtapaId() {
		return this.etapaId;
	}

	public void setEtapaId(Integer etapaId) {
		this.etapaId = etapaId;
	}

	public BigDecimal getCostoParticipacion() {
		return this.costoParticipacion;
	}

	public void setCostoParticipacion(BigDecimal costoParticipacion) {
		this.costoParticipacion = costoParticipacion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Torneo getTorneo() {
		return this.torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public List<Inscripcion> getInscripcions() {
		return this.inscripcions;
	}

	public void setInscripcions(List<Inscripcion> inscripcions) {
		this.inscripcions = inscripcions;
	}

	public Inscripcion addInscripcion(Inscripcion inscripcion) {
		getInscripcions().add(inscripcion);
		inscripcion.setEtapa(this);

		return inscripcion;
	}

	public Inscripcion removeInscripcion(Inscripcion inscripcion) {
		getInscripcions().remove(inscripcion);
		inscripcion.setEtapa(null);

		return inscripcion;
	}

	public List<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setEtapa(this);

		return partido;
	}

	public Partido removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setEtapa(null);

		return partido;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Boolean getPenales() {
		return penales;
	}

	public void setPenales(Boolean penales) {
		this.penales = penales;
	}

}