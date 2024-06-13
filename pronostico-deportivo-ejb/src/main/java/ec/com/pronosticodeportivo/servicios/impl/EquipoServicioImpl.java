/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.EquipoDao;
import ec.com.pronosticodeportivo.modelo.Equipo;
import ec.com.pronosticodeportivo.servicios.EquipoServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="EquipoServicio")
public class EquipoServicioImpl extends GenericServiceImpl<Equipo, Integer> implements EquipoServicio {

	@EJB
	private EquipoDao equipoDao;
	
	@Override
	public GenericDao<Equipo, Integer> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
