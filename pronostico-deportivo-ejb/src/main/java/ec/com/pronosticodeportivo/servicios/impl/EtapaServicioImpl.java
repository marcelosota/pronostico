/**
 * 
 */
package ec.com.pronosticodeportivo.servicios.impl;

import java.math.BigDecimal;
import java.util.List;

import ec.com.persistencia.constante.CriteriaTypeEnum;
import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.servicio.impl.GenericServiceImpl;
import ec.com.persistencia.util.Criteria;
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
	
	@Override
	public BigDecimal getInscripcion() {
		String[] criteriasAnd = { "estado" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.BOOLEAN_POSTGRESQL };
		Object[] valuesCriteriaAnd = {  true };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
		List<Etapa> etapa = findByCriterias(criteria);
		
		if(etapa.get(0).getCostoParticipacion() != null)
			return etapa.get(0).getCostoParticipacion();
		else
			return BigDecimal.ZERO;
	}

}
