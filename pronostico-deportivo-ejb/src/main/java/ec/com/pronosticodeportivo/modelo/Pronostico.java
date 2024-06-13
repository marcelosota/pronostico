package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the pronostico database table.
 * 
 */
@Entity
@Table(name="pronostico",schema = "futbol")
@NamedQuery(name="Pronostico.findAll", query="SELECT p FROM Pronostico p")
public class Pronostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRONOSTICO_PRONOSTICOID_GENERATOR", sequenceName="PRONOSTICO_PRONOSTICO_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRONOSTICO_PRONOSTICOID_GENERATOR")
	@Column(name="pronostico_id")
	private Integer pronosticoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	private Short local;

	private Short puntaje;

	private Short visitante;

	@Column(name="penales_local")
	private Short penalesLocal;

	@Column(name="penales_visitante")
	private Short penalesVisitante;

	//bi-directional many-to-one association to Partido
	@ManyToOne
	@JoinColumn(name="partido_id")
	private Partido partido;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public Pronostico() {
	}

	public Integer getPronosticoId() {
		return this.pronosticoId;
	}

	public void setPronosticoId(Integer pronosticoId) {
		this.pronosticoId = pronosticoId;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Short getLocal() {
		return this.local;
	}

	public void setLocal(Short local) {
		this.local = local;
	}

	public Short getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(Short puntaje) {
		this.puntaje = puntaje;
	}

	public Short getVisitante() {
		return this.visitante;
	}

	public void setVisitante(Short visitante) {
		this.visitante = visitante;
	}

	public Partido getPartido() {
		return this.partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Short getPenalesLocal() {
		return penalesLocal;
	}

	public void setPenalesLocal(Short penalesLocal) {
		this.penalesLocal = penalesLocal;
	}

	public Short getPenalesVisitante() {
		return penalesVisitante;
	}

	public void setPenalesVisitante(Short penalesVisitante) {
		this.penalesVisitante = penalesVisitante;
	}

}