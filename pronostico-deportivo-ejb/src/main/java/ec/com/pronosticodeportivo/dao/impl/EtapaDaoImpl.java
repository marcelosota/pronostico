/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.pronosticodeportivo.dao.EtapaDao;
import ec.com.pronosticodeportivo.modelo.Etapa;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="EtapaDao")
public class EtapaDaoImpl extends GenericDaoEjb<Etapa, Integer> implements EtapaDao {

	/**
	 * @param type
	 */
	public EtapaDaoImpl() {
		super(Etapa.class);
	}

}
