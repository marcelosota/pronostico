/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Equipo;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface EquipoDao extends GenericDao<Equipo, Integer> {

}
