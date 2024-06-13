/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.VwListaPartido;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface VwListaPartidoDao extends GenericDao<VwListaPartido, Integer> {

}
