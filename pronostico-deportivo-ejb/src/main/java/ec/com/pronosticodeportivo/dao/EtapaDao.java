/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Etapa;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface EtapaDao extends GenericDao<Etapa, Integer> {

}
