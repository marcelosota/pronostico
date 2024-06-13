package ec.com.pronostico.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

//import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import ec.com.pronostico.pojo.Partido;
import ec.com.pronostico.pojo.Resultado;
import ec.com.pronostico.pojo.TablaPosiciones;
import ec.com.pronostico.pojo.Usuario;
import ec.com.pronostico.utils.ConexionPg;
import ec.com.pronostico.utils.Sesion;

@Named(value = "pollaBean")
@javax.enterprise.context.SessionScoped
public class PollaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6478965544759727526L;
	private String cedula;
	private String nombreParticipante;
	private String contrasenaActual;
	private String nuevaContrasena;
	private String repetirContrasena;
	private List<Partido> objPartido;
	private ConexionPg bdd;
	private boolean verCedula;
	private boolean verTabla;
	private List<TablaPosiciones> objPosiciones;
	private List<Resultado> objResultado;
	private String alfanumerico;
	private Sesion objSesion = new Sesion();

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreParticipante() {
		return this.nombreParticipante;
	}

	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante;
	}

	public String getContrasenaActual() {
		return this.contrasenaActual;
	}

	public void setContrasenaActual(String contrasenaActual) {
		this.contrasenaActual = contrasenaActual;
	}

	public String getNuevaContrasena() {
		return this.nuevaContrasena;
	}

	public void setNuevaContrasena(String nuevaContrasena) {
		this.nuevaContrasena = nuevaContrasena;
	}

	public String getRepetirContrasena() {
		return this.repetirContrasena;
	}

	public void setRepetirContrasena(String repetirContrasena) {
		this.repetirContrasena = repetirContrasena;
	}

	public List<Partido> getObjPartido() {
		return this.objPartido;
	}

	public void setObjPartido(List<Partido> objPartido) {
		this.objPartido = objPartido;
	}

	public boolean isVerCedula() {
		return this.verCedula;
	}

	public void setVerCedula(boolean verCedula) {
		this.verCedula = verCedula;
	}

	public boolean isVerTabla() {
		return this.verTabla;
	}

	public void setVerTabla(boolean verTabla) {
		this.verTabla = verTabla;
	}

	public List<TablaPosiciones> getObjPosiciones() {
		return this.objPosiciones;
	}

	public void setObjPosiciones(List<TablaPosiciones> objPosiciones) {
		this.objPosiciones = objPosiciones;
	}

	public List<Resultado> getObjResultado() {
		return this.objResultado;
	}

	public void setObjResultado(List<Resultado> objResultado) {
		this.objResultado = objResultado;
	}

	public String getAlfanumerico() {
		return this.alfanumerico;
	}

	public void setAlfanumerico(String alfanumerico) {
		this.alfanumerico = alfanumerico;
	}

	public PollaBean() {
		if ((this.objSesion.getCedula() != null) && (this.objSesion.getNombre() != null)) {
			setVerCedula(false);
			setVerTabla(true);
			setAlfanumerico("[A-Za-z0-9 ]*$");
			setNombreParticipante(this.objSesion.getNombre());
			cargarDatos();
		} else {
			cerrarSesion();
		}
	}

	public void cargarDatos() {
		this.objPartido = new ArrayList<Partido>();
		this.objPosiciones = new ArrayList<TablaPosiciones>();
		getPartidosSP();
		getTablaPosicionesSP();
		getResultadosSP();
		//RequestContext.getCurrentInstance().update("dtbPolla");
		//RequestContext.getCurrentInstance().update("dtbResultado");
		//RequestContext.getCurrentInstance().update("dtbPosiciones");
	}

	public void resultado(RowEditEvent event) {
		try {
			this.bdd = new ConexionPg();

			if ((((Partido) event.getObject()).getMarcador1() != null)
					&& (!((Partido) event.getObject()).getMarcador1().trim().equals(""))
					&& (((Partido) event.getObject()).getMarcador2() != null)
					&& (!((Partido) event.getObject()).getMarcador2().trim().equals(""))) {
				CallableStatement cst = this.bdd.conexion.prepareCall("{call futbol.insert_resultado(?,?,?,?)}");
				cst.setInt(1, Integer.parseInt(((Partido) event.getObject()).getMarcador1()));
				cst.setInt(2, Integer.parseInt(((Partido) event.getObject()).getMarcador2()));
				cst.setInt(3, ((Partido) event.getObject()).getId());
				cst.setString(4, this.objSesion.getCedula());
				cst.execute();
				cst.close();
				getPartidosSP();
				getResultadosSP();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Error */
	public boolean getPartidosSP() {
		boolean flag = false;
		bdd = new ConexionPg();
		ResultSet rs = null;
		try {
			//PreparedStatement pst = bdd.conexion.prepareStatement("{call consultar_partidos_publicables ?}");
			//pst.setString(1, objSesion.getCedula());
			//rs = pst.executeQuery();
			CallableStatement cst;
			cst = bdd.conexion.prepareCall("{call futbol.consultar_partidos_publicables(?)}");
			cst.setString(1, objSesion.getCedula());
			cst.execute();
			rs = cst.getResultSet();
			objPartido = new ArrayList<Partido>();
			while (rs.next()) {
				Partido item = new Partido();
				item.setPais1(rs.getString("pais1"));
				item.setPais2(rs.getString("pais2"));
				item.setId(rs.getInt("id"));
				item.setFechaJuego(rs.getDate("fechaPartido")+" "+rs.getTime("fechaPartido"));
				objPartido.add(item);
			}
			rs.close();
			cst.close();
			//pst.close();
		} catch (SQLException e) {
			flag = false;
		} finally {
			try {
				bdd.conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	/* Error */
	public boolean getTablaPosicionesSP() {
		boolean flag = false;
		int cont = 0;
		bdd = new ConexionPg();
		ResultSet rs = null;
		
		try {
			//PreparedStatement pst = bdd.conexion.prepareStatement("{call consultar_tabla_posiciones}");
			CallableStatement cst;
			cst = bdd.conexion.prepareCall("{call futbol.consultar_tabla_posiciones()}");
			cst.execute();
			rs = cst.getResultSet();
			objPosiciones = new ArrayList<TablaPosiciones>();
			while (rs.next()) {
				TablaPosiciones item = new TablaPosiciones();
				cont++;
				item.setOrdinal(cont);
				item.setNombre(rs.getString("nombre"));
				item.setTotal(rs.getInt("puntos"));
				objPosiciones.add(item);
			}
			rs.close();
			cst.close();
		} catch (SQLException e) {
			flag = false;
		} finally {
			try {
				bdd.conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	/* Error */
	public boolean getResultadosSP() {
		boolean flag = false;
		bdd = new ConexionPg();
		ResultSet rs = null;
		try {
			//PreparedStatement pst = bdd.conexion.prepareStatement("{call consultar_resultados_cedula ?}");
			CallableStatement cst;
			cst = bdd.conexion.prepareCall("{call futbol.consultar_resultados_cedula(?)}");
			cst.setString(1, objSesion.getCedula());
			cst.execute();
			rs = cst.getResultSet();
			objResultado = new ArrayList<Resultado>();
			while (rs.next()) {
				Resultado item = new Resultado();
				item.setEtapa(rs.getString("etapa"));
				item.setPais1(rs.getString("pais1"));
				item.setPais2(rs.getString("pais2"));
				item.setMarcador1(rs.getInt("marcadorp1"));
				item.setMarcador2(rs.getInt("marcadorp2"));
				item.setPronostico1(Integer.toString(rs
						.getInt("marcadorRespuesta1")));
				item.setPronostico2(Integer.toString(rs
						.getInt("marcadorRespuesta2")));
				item.setPuntos(rs.getInt("puntos"));
				item.setFechaPartido(rs.getDate("fechaPartido"));
				objResultado.add(item);
			}
			rs.close();
			cst.close();
		} catch (SQLException e) {
			flag = false;
		} finally {
			try {
				bdd.conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	private void cerrarSesion() {
		this.objSesion.cerrarSession();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void salir() {
		cerrarSesion();
	}

	public void cambiarContrasena(ActionEvent actionEvent) {
		if ((!getContrasenaActual().isEmpty()) && (!getNuevaContrasena().isEmpty())) {
			Usuario objUsuario = new Usuario();
			if (objUsuario.cambiarContrasena(this.objSesion.getCedula(), getContrasenaActual(), getNuevaContrasena(),
					"C")) {
				mensaje("La contraseña se cambió satisfactoriamente");
			} else {
				mensaje("No fue posible cambiar la cantraseña, favor verificar su información");
			}
		}

		setContrasenaActual("");
		setNuevaContrasena("");
		setRepetirContrasena("");
	}

	private void mensaje(String texto) {
		FacesMessage msg = new FacesMessage("Info", texto);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
