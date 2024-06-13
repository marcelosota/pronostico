package ec.com.pronosticodeportivo.servicios.impl;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.InscripcionDao;
import ec.com.pronosticodeportivo.modelo.Inscripcion;
import ec.com.pronosticodeportivo.servicios.InscripcionServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless(name="InscripcionServicio")
public class InscripcionServicioImpl extends GenericServiceImpl<Inscripcion, Integer> implements InscripcionServicio {

	@EJB
	private InscripcionDao inscripcionDao;
	
	@Override
	public GenericDao<Inscripcion, Integer> getDao() {
		// TODO Auto-generated method stub
		return inscripcionDao;
	}

}
