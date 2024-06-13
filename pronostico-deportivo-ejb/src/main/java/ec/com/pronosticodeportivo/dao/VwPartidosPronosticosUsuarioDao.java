/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import java.util.Date;
import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.VwPartidosPronosticosUsuario;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface VwPartidosPronosticosUsuarioDao extends GenericDao<VwPartidosPronosticosUsuario, Integer> {

	List<VwPartidosPronosticosUsuario> buscarPronosticoPorUsuario(String cedula);

	List<VwPartidosPronosticosUsuario> getPronosticos(Date fecha);

	List<VwPartidosPronosticosUsuario> getPronosticos(Integer partidoId);

}
