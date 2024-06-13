/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.TorneoDao;
import ec.com.pronosticodeportivo.modelo.Torneo;
import ec.com.pronosticodeportivo.servicios.TorneoServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="TorneServicio")
public class TorneoServicioImpl extends GenericServiceImpl<Torneo, Integer> implements TorneoServicio {

	@EJB
	private TorneoDao torneoDao;
	
	@Override
	public GenericDao<Torneo, Integer> getDao() {
		// TODO Auto-generated method stub
		return torneoDao;
	}

}
