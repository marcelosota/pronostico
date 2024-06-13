/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Torneo;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface TorneoDao extends GenericDao<Torneo, Integer> {

}
