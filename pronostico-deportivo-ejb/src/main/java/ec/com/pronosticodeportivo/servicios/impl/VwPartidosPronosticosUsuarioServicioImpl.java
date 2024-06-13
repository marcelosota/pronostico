/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.VwPartidosPronosticosUsuarioDao;
import ec.com.pronosticodeportivo.modelo.VwPartidosPronosticosUsuario;
import ec.com.pronosticodeportivo.servicios.VwPartidosPronosticosUsuarioServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="VwPartidosPronosticosServicio")
public class VwPartidosPronosticosUsuarioServicioImpl extends GenericServiceImpl<VwPartidosPronosticosUsuario, Integer>
		implements VwPartidosPronosticosUsuarioServicio {

	@EJB
	private VwPartidosPronosticosUsuarioDao vwPartidosPronosticosUsuarioDao;
	
	@Override
	public GenericDao<VwPartidosPronosticosUsuario, Integer> getDao() {
		// TODO Auto-generated method stub
		return vwPartidosPronosticosUsuarioDao;
	}

	@Override
	public List<VwPartidosPronosticosUsuario> buscarPronosticoPorUsuario(String cedula) {
		return vwPartidosPronosticosUsuarioDao.buscarPronosticoPorUsuario(cedula);
	}

}
