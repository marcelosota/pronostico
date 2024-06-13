/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.pronosticodeportivo.dao.VwTablaPosicionesDao;
import ec.com.pronosticodeportivo.modelo.VwTablaPosiciones;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless
public class VwTablaPosicionesDaoImpl extends GenericDaoEjb<VwTablaPosiciones, Integer>
		implements VwTablaPosicionesDao {

	/**
	 * @param type
	 */
	public VwTablaPosicionesDaoImpl() {
		super(VwTablaPosiciones.class);
	}

}
