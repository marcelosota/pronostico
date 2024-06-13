/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.UsuarioDao;
import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.util.Correo;
import ec.com.pronosticodeportivo.util.Mensaje;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="UsuarioServicio")
//@SessionScoped
public class UsuarioServicioImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioServicio {

	@EJB
	private UsuarioDao usuarioDao;
	
	@Override
	public GenericDao<Usuario, Integer> getDao() {
		return usuarioDao;
	}

	@Override
	public Usuario autenticarUsuario(String cedula, String password) {
		return usuarioDao.autenticarUsuario(cedula,password);
	}

	@Override
	public String encriptarContrasena(String contrasena) {
			String resp = "";

			try {
				// 3. Se crea el metodo de encriptacion SHA1
				MessageDigest crypt = MessageDigest.getInstance("SHA-1");
				crypt.reset();
				crypt.update(contrasena.getBytes("UTF-8"));

				// 4. Se pone la respuesta
				resp = new BigInteger(1, crypt.digest()).toString(16);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return resp;
	}

	@Override
	public Usuario buscarUsuarioPorLogin(String username) {
		return usuarioDao.obtenerUsuarioPorLogin(username);
	}

	@Override
	public void recuperarContrasena(String usuarioRecuperar) {
		String nuevaClave=generarContrasena();
		Usuario cambioClave=buscarUsuarioPorLogin(usuarioRecuperar);
		cambioClave.setContrasena(encriptarContrasena(nuevaClave));
		
		String asunto = "Recuperación contraseña";
		String textoMensaje = "Se ha generado automáticamente un nueva contraseña. Los nuevos datos de acceso son:<br/>Usuario:"+usuarioRecuperar+" <br/>Contraseña:"+nuevaClave;
		
		List<String> to=new ArrayList<String>();
		
		to.add(cambioClave.getEmail());
		
		Mensaje mensaje=new Mensaje();
		mensaje.setAsunto(asunto);
		mensaje.setTextoMensaje(textoMensaje);
		mensaje.setDestinatarios(to);
		mensaje.setRemitente("pronosticos@pronosticos.com");
		mensaje.setTieneAdjunto(false);
		update(cambioClave);
		Correo correo= new Correo();
		correo.enviarCorreo(mensaje);
		
	}
	
	public String generarContrasena(){
		return RandomStringUtils.randomAlphanumeric(6);
	}

}
