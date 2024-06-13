/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.VwTablaPosicionesDao;
import ec.com.pronosticodeportivo.modelo.VwTablaPosiciones;
import ec.com.pronosticodeportivo.servicios.VwTablaPosicionesServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="VwTablaPosicionesServicio")
public class VwTablaPosicionesServicioImpl extends GenericServiceImpl<VwTablaPosiciones, Integer>
		implements VwTablaPosicionesServicio {

	@EJB
	private VwTablaPosicionesDao vwTablaPosicionesDao;
	@Override
	public GenericDao<VwTablaPosiciones, Integer> getDao() {
		// TODO Auto-generated method stub
		return vwTablaPosicionesDao;
	}

}
