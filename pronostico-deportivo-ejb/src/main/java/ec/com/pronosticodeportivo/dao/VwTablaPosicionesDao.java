/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.VwTablaPosiciones;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface VwTablaPosicionesDao extends GenericDao<VwTablaPosiciones, Integer> {

}
