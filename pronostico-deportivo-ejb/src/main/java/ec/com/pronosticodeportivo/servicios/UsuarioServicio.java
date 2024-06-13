/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Usuario;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface UsuarioServicio extends GenericService<Usuario, Integer> {

	public Usuario autenticarUsuario(String cedula, String password);
	
	public String encriptarContrasena(String contrasena);

	public Usuario buscarUsuarioPorLogin( String username);

	public void recuperarContrasena(String usuarioRecuperar);
}
