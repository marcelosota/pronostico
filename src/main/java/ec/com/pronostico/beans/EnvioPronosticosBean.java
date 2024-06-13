package ec.com.pronostico.beans;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.com.pronostico.utils.ConexionPg;
import ec.com.pronostico.utils.CorreoElectronico;

@Named(value = "envioPronosticosBean")
@ViewScoped
public class EnvioPronosticosBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8422593239401749713L;
	private String valor;

	@PostConstruct
	public void init() {
		ConexionPg bdd = new ConexionPg();

		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select distinct partido_id, local, visitante ");
		sql.append("from futbol.vw_partidos_pronosticos_usuarios ");
		sql.append("where fecha_partido <= now() ");
		sql.append("and marcador_local is NULL ");
		sql.append("and marcador_visitante is NULL ");
		sql.append("and notificado = false");
		try {
			System.out.println(sql);
			Statement st = bdd.conexion.createStatement();
			rs = st.executeQuery(sql.toString());
			while (rs.next()) {
				ResultSet rs1 = null;

				StringBuilder sql1 = new StringBuilder(
						"select distinct partido_id, vw.nombre, local, visitante, pronostico_local, ");
				sql1.append("pronostico_visitante, u.email, vw.fecha_registro ");
				sql1.append("from futbol.vw_partidos_pronosticos_usuarios vw ");
				sql1.append("inner join futbol.usuario u on vw.login = u.login ");
				sql1.append("where partido_id = ").append(rs.getInt("partido_id"));
				sql1.append(" order by 2");
				System.out.println(sql1.toString());
				Statement st1 = bdd.conexion.createStatement();
				rs1 = st1.executeQuery(sql1.toString());
				String destinatarios = "";
				String asunto = "Pronóstico " + rs.getString("local") + " vs " + rs.getString("visitante");
				StringBuilder cuerpo = new StringBuilder("<html><body>");
				cuerpo.append("<table style=\"border:2px solid black;border-collapse:collapse\">");
				cuerpo.append(
						"<tr style=\"background-color: #045FB4; color: white;border:2px solid black;border-collapse:collapse\">");
				cuerpo.append("<th style=\"border:2px solid black;border-collapse:collapse\">Participante</th>");
				cuerpo.append("<th style=\"border:2px solid black;border-collapse:collapse\">Fecha registro</th>");
				cuerpo.append("<th style=\"border:2px solid black;border-collapse:collapse\">Local</th>");
				cuerpo.append("<th style=\"border:2px solid black;border-collapse:collapse\">Pronóstico Local</th>");
				cuerpo.append(
						"<th style=\"border:2px solid black;border-collapse:collapse\">Pronóstico Visitante</th>");
				cuerpo.append("<th style=\"border:2px solid black;border-collapse:collapse\">Visitante</th></tr>");
				while (rs1.next()) {
					cuerpo.append("<tr>");
					cuerpo.append(
							"<td style=\"background-color:#F6D8CE;border:2px solid black;border-collapse:collapse\">")
							.append(rs1.getString("nombre")).append("</td>");
					cuerpo.append(
							"<td style=\"background-color:#8BC1AC;border:2px solid black;border-collapse:collapse;text-align:center\">")
							.append(rs1.getString("fecha_registro")).append("</td>");
					cuerpo.append(
							"<td style=\"background-color:#A9BCF5;border:2px solid black;border-collapse:collapse\">")
							.append(rs1.getString("local")).append("</td>");
					cuerpo.append(
							"<td style=\"background-color:#F5F6CE;border:2px solid black;border-collapse:collapse;text-align:center\">")
							.append(rs1.getInt("pronostico_local")).append("</td>");
					cuerpo.append(
							"<td style=\"background-color:#E3F6CE;border:2px solid black;border-collapse:collapse;text-align:center\">")
							.append(rs1.getInt("pronostico_visitante")).append("</td>");
					cuerpo.append(
							"<td style=\"background-color:#CECEF6;border:2px solid black;border-collapse:collapse\">")
							.append(rs1.getString("visitante")).append("</td>");
					cuerpo.append("</tr>");
					destinatarios = destinatarios + rs1.getString("email") + ",";
					System.out.println(destinatarios);
				}
				rs1.close();
				cuerpo.append("</body></html>");
				CorreoElectronico.enviarConGMail(destinatarios, asunto, cuerpo.toString());
				actualizarPartido(rs.getInt("partido_id"));
			}

			rs.close();
			bdd.conexion.close();
		} catch (SQLException localSQLException) {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void actualizarPartido(int partidoId) {
		ConexionPg bdd = new ConexionPg();
		try {
			PreparedStatement st = bdd.conexion
					.prepareStatement("update futbol.partido set notificado = true where partido_id = " + partidoId);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
