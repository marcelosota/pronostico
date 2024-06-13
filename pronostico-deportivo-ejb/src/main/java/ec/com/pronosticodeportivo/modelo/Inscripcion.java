package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
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
 * The persistent class for the inscripcion database table.
 * 
 */
@Entity
@Table(name="inscripcion",schema = "futbol")
@NamedQuery(name="Inscripcion.findAll", query="SELECT i FROM Inscripcion i")
public class Inscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INSCRIPCION_INSCRIPCIONID_GENERATOR", sequenceName="INSCRIPCION_INSCRIPCION_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSCRIPCION_INSCRIPCIONID_GENERATOR")
	@Column(name="inscripcion_id")
	private Integer inscripcionId;

	private Integer estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_partido")
	private Date fechaPartido;

	//bi-directional many-to-one association to Etapa
	@ManyToOne
	@JoinColumn(name="etapa_id")
	private Etapa etapa;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public Inscripcion() {
	}

	public Integer getInscripcionId() {
		return this.inscripcionId;
	}

	public void setInscripcionId(Integer inscripcionId) {
		this.inscripcionId = inscripcionId;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFechaPartido() {
		return this.fechaPartido;
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public Etapa getEtapa() {
		return this.etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}