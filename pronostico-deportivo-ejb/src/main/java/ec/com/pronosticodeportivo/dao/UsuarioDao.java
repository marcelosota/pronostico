/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Usuario;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface UsuarioDao extends GenericDao<Usuario, Integer> {

	Usuario autenticarUsuario(String cedula, String password);

	Usuario obtenerUsuarioPorLogin(String username);

	List<Usuario> getUsuarios(List<Integer> usuarioId);

	List<Usuario> getUsuarios();

}
