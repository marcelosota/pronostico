/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.pronosticodeportivo.dao.EtapaDao;
import ec.com.pronosticodeportivo.modelo.Etapa;
import ec.com.pronosticodeportivo.servicios.EtapaServicio;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="EtapaServicio")
public class EtapaServicioImpl extends GenericServiceImpl<Etapa, Integer> implements EtapaServicio {

	@EJB
	private EtapaDao etapaDao;

	@Override
	public GenericDao<Etapa, Integer> getDao() {
		// TODO Auto-generated method stub
		return etapaDao;
	}

}
