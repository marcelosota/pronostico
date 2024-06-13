/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.VwListaPartidoDao;
import ec.com.pronosticodeportivo.modelo.VwListaPartido;
import ec.com.pronosticodeportivo.servicios.VwListaPartidoServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="VwListaPartidoServicio")
public class VwListaPartidoServicioImpl extends GenericServiceImpl<VwListaPartido, Integer>
		implements VwListaPartidoServicio {

	@EJB
	private VwListaPartidoDao vwListaPartidoDao;
	@Override
	public GenericDao<VwListaPartido, Integer> getDao() {
		// TODO Auto-generated method stub
		return vwListaPartidoDao;
	}

}
