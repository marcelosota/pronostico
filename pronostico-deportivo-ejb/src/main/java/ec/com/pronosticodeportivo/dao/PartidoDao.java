/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface PartidoDao extends GenericDao<Partido, Integer> {

	List<Partido> obtenerPartidosIngreso();

	List<Partido> getPartidosPendientes();

	List<Partido> getPartidoNotificacion();

}
