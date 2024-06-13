/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.PronosticoDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.modelo.Pronostico;
import ec.com.pronosticodeportivo.servicios.PronosticoServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="PronosticoServicio")
public class PronosticoServicioImpl extends GenericServiceImpl<Pronostico, Integer> implements PronosticoServicio {

	@EJB
	private PronosticoDao pronosticoDao;
	
	@Override
	public GenericDao<Pronostico, Integer> getDao() {
		// TODO Auto-generated method stub
		return pronosticoDao;
	}

	@Override
	public List<Pronostico> obtenerPronosticosIngreso(String cedula) {
		return pronosticoDao.obtenerPronosticosIngreso(cedula);
	}

	@Override
	public Pronostico buscarPronosticoPorPartidoUsuario(Integer partidoId, String cedula) {
		return pronosticoDao.buscarPronosticoPorPartidoUsuario(partidoId, cedula);
	}

	@Override
	public void actualizarPuntajesPronosticos(Partido partidoSeleccionado) {
		pronosticoDao.actualizarPuntajesPronosticos(partidoSeleccionado);
		
	}

}
