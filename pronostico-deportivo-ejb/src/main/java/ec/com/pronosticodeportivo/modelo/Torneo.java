package ec.com.pronosticodeportivo.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the torneo database table.
 * 
 */
@Entity
@Table(name="torneo",schema = "futbol")
@NamedQuery(name="Torneo.findAll", query="SELECT t FROM Torneo t")
public class Torneo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TORNEO_TORNEOID_GENERATOR", sequenceName="TORNEO_TORNEO_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TORNEO_TORNEOID_GENERATOR")
	@Column(name="torneo_id")
	private Integer torneoId;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_finalizacion")
	private Date fechaFinalizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	private String logo;

	private String mascota;

	private String nombre;

	private String pais;

	private String url;

	//bi-directional many-to-one association to Etapa
	@OneToMany(mappedBy="torneo")
	private List<Etapa> etapas;

	public Torneo() {
	}

	public Integer getTorneoId() {
		return this.torneoId;
	}

	public void setTorneoId(Integer torneoId) {
		this.torneoId = torneoId;
	}

	public Date getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMascota() {
		return this.mascota;
	}

	public void setMascota(String mascota) {
		this.mascota = mascota;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Etapa> getEtapas() {
		return this.etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}

	public Etapa addEtapa(Etapa etapa) {
		getEtapas().add(etapa);
		etapa.setTorneo(this);

		return etapa;
	}

	public Etapa removeEtapa(Etapa etapa) {
		getEtapas().remove(etapa);
		etapa.setTorneo(null);

		return etapa;
	}

}