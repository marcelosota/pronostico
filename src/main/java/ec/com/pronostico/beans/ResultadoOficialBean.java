package ec.com.pronostico.beans;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

//import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import ec.com.pronostico.pojo.Partido;
import ec.com.pronostico.utils.ConexionPg;

@Named(value = "resultadoOficialBean")
@SessionScoped
public class ResultadoOficialBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1135969768534942208L;

	private String cedula;
	private List<Partido> objPartido;
	private ConexionPg bdd;
	private boolean verCedula;

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public ResultadoOficialBean() {
		setVerCedula(false);
		this.objPartido = new ArrayList<Partido>();
		getPartidosSP();
	}

	public void identificarse(ActionEvent event) {
		getPartidosSP();
		setVerCedula(false);
	}

	public void resultado(RowEditEvent event) {
		try {
			this.bdd = new ConexionPg();

			CallableStatement cst = this.bdd.conexion.prepareCall("{call futbol.actualizar_partido(?,?,?)}");
			cst.setInt(1, ((Partido) event.getObject()).getId());
			cst.setInt(2, Integer.parseInt(((Partido) event.getObject()).getMarcador1()));
			cst.setInt(3, Integer.parseInt(((Partido) event.getObject()).getMarcador2()));
			cst.execute();
			cst.close();
			getPartidosSP();
			//RequestContext.getCurrentInstance().update("dtbPolla");
		} catch (SQLException localSQLException) {
		}
	}

	public boolean getPartidosSP() {
		boolean flag = false;
		bdd = new ConexionPg();
		ResultSet rs = null;
		try {
			CallableStatement cst;
			cst = bdd.conexion.prepareCall("{call futbol.consultar_partidos()}");
			cst.execute();
			rs = cst.getResultSet();

			objPartido = new ArrayList<Partido>();
			while(rs.next()){
				Partido item = new Partido();
				item.setPais1(rs.getString("pais1"));
				item.setPais2(rs.getString("pais2"));
				item.setMarcador1(Integer.toString(rs.getInt("marcadorp1")));
				item.setMarcador2(Integer.toString(rs.getInt("marcadorp2")));
				item.setId(rs.getInt("id"));
				item.setFechaJuego(rs.getDate("fechaPartido")+" "+rs.getTime("fechaPartido"));
				objPartido.add(item);
			}
			rs.close();
			cst.close();
		} catch (SQLException e) {
			flag = false;
		}finally{
			try {
				bdd.conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
}
