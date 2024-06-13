package ec.com.pronostico.pojo;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.RandomStringUtils;

import ec.com.pronostico.utils.ConexionPg;
import ec.com.pronostico.utils.CorreoElectronico;

public class Usuario {
	private ConexionPg bdd;
	private Participante objParticipante;

	public Participante getObjParticipante() {
		return this.objParticipante;
	}

	public void setObjParticipante(Participante objParticipante) {
		this.objParticipante = objParticipante;
	}

	public String generarContrasena() {
		return RandomStringUtils.randomAlphanumeric(6);
	}

	public String encriptarConstrasena(String contrasena) {
		String resp = "";

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(contrasena.getBytes("UTF-8"));

			resp = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return resp;
	}

	/* Error */
	public boolean autenticar(String usuario, String contrasena) {
		boolean flag = false;
		objParticipante = new Participante();
		bdd = new ConexionPg();
		ResultSet rs = null;
		try {
			// CallableStatement cStmt = bdd.conexion.prepareCall("exec sp_ingreso ?,?");
			CallableStatement cStmt = bdd.conexion.prepareCall("{call futbol.sp_ingreso(?, ?)}");
			cStmt.setString(1, usuario);
			cStmt.setString(2, contrasena);
			cStmt.execute();
			rs = cStmt.getResultSet();
			while (rs.next()) {
				objParticipante.setCedula(rs.getString("cedula"));
				objParticipante.setNombre(rs.getString("nombre"));
				flag = true;
			}
			rs.close();
			cStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public boolean cambiarContrasena(String cedula, String actualContrasena, String nuevaContrasena,
			String tipoCambio) {
		boolean flag = false;
		this.bdd = new ConexionPg();
		ResultSet rs = null;
		try {
			CallableStatement cStmt = this.bdd.conexion.prepareCall("{call futbol.sp_cambio_password(?,?,?,?)}");
			cStmt.setString(1, cedula);
			cStmt.setString(2, actualContrasena);
			cStmt.setString(3, nuevaContrasena);
			cStmt.setString(4, tipoCambio);
			cStmt.execute();
			rs = cStmt.getResultSet();
			while (rs.next()) {
				if (rs.getString(1).equals("PASSWORD CAMBIADO"))
					flag = true;
			}
			rs.close();
			cStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean recuperarContrasena(String cedula) {
		boolean flag = false;
		String asunto = "Recuperación contraseña";
		String contrasena = generarContrasena();
		String mensaje = "Se ha generado autom&aacute;ticamente un nueva contrase&ntilde;a: " + contrasena
				+ " para el usuario: " + cedula;
		this.bdd = new ConexionPg();
		System.out.println(contrasena);
		if (cambiarContrasena(cedula, " ", contrasena, "R")) {
			flag = getInformacionParticipante(cedula);
			if(flag)
				CorreoElectronico.enviarConGMail(objParticipante.getEmail(), asunto, mensaje);
		}
		return flag;
	}
	
	private boolean getInformacionParticipante(String cedula) {
		boolean flag = false;
		ResultSet rs = null;
		objParticipante = new Participante();
		try {
			CallableStatement cStmt = this.bdd.conexion.prepareCall("{call futbol.sp_informacion_participante(?)}");
			cStmt.setString(1, cedula);
			cStmt.execute();
			rs = cStmt.getResultSet();
			while (rs.next()) {
				objParticipante.setCedula(rs.getString("cedula"));
				objParticipante.setNombre(rs.getString("nombre"));
				objParticipante.setEmail(rs.getString("email"));
				flag = true;
			}
			rs.close();
			cStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
