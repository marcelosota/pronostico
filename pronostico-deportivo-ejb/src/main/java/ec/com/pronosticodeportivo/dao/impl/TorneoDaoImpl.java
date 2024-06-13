/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import ec.com.pronosticodeportivo.dao.TorneoDao;
import ec.com.pronosticodeportivo.modelo.Torneo;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="TorneoDao")
public class TorneoDaoImpl extends PronosticoDeportivoGenericDao<Torneo, Integer> implements TorneoDao {

	/**
	 * @param type
	 */
	public TorneoDaoImpl() {
		super(Torneo.class);
	}

}
