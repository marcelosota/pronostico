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
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario",schema = "futbol")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_USUARIOID_GENERATOR", sequenceName="USUARIO_USUARIO_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_USUARIOID_GENERATOR")
	@Column(name="usuario_id")
	private Integer usuarioId;

	private String apellido;

	private String banco;

	private String contrasena;

	private String email;

	private String estado;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="forma_pago")
	private String formaPago;

	private String login;

	private String nombre;

	@Column(name="numero_cuenta")
	private String numeroCuenta;

	private String telefono;

	@Column(name="tipo_cuenta")
	private String tipoCuenta;

	@Column(name="tipo_logueo")
	private String tipoLogueo;

	//bi-directional many-to-one association to Inscripcion
	@OneToMany(mappedBy="usuario")
	private List<Inscripcion> inscripcions;

	//bi-directional many-to-one association to Pronostico
	@OneToMany(mappedBy="usuario")
	private List<Pronostico> pronosticos;

	public Usuario() {
	}

	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getTipoLogueo() {
		return this.tipoLogueo;
	}

	public void setTipoLogueo(String tipoLogueo) {
		this.tipoLogueo = tipoLogueo;
	}

	public List<Inscripcion> getInscripcions() {
		return this.inscripcions;
	}

	public void setInscripcions(List<Inscripcion> inscripcions) {
		this.inscripcions = inscripcions;
	}

	public Inscripcion addInscripcion(Inscripcion inscripcion) {
		getInscripcions().add(inscripcion);
		inscripcion.setUsuario(this);

		return inscripcion;
	}

	public Inscripcion removeInscripcion(Inscripcion inscripcion) {
		getInscripcions().remove(inscripcion);
		inscripcion.setUsuario(null);

		return inscripcion;
	}

	public List<Pronostico> getPronosticos() {
		return this.pronosticos;
	}

	public void setPronosticos(List<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

	public Pronostico addPronostico(Pronostico pronostico) {
		getPronosticos().add(pronostico);
		pronostico.setUsuario(this);

		return pronostico;
	}

	public Pronostico removePronostico(Pronostico pronostico) {
		getPronosticos().remove(pronostico);
		pronostico.setUsuario(null);

		return pronostico;
	}

}