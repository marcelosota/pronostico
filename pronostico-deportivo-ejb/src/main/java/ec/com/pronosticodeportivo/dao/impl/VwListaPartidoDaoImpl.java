/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.pronosticodeportivo.dao.VwListaPartidoDao;
import ec.com.pronosticodeportivo.modelo.VwListaPartido;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless
public class VwListaPartidoDaoImpl extends GenericDaoEjb<VwListaPartido, Integer> implements VwListaPartidoDao {

	/**
	 * @param type
	 */
	public VwListaPartidoDaoImpl() {
		super(VwListaPartido.class);
	}

}
