/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.PartidoDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.servicios.PartidoServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="PartidoServicio")
public class PartidoServicioImpl extends GenericServiceImpl<Partido, Integer> implements PartidoServicio {

	@EJB
	private PartidoDao partidoDao;
	
	@Override
	public GenericDao<Partido, Integer> getDao() {
		return partidoDao;
	}

	@Override
	public List<Partido> obtenerPartidosIngreso() {
		return partidoDao.obtenerPartidosIngreso();
	}

}
