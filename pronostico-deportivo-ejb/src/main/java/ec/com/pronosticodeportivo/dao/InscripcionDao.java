/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Inscripcion;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface InscripcionDao extends GenericDao<Inscripcion, Integer> {

}
