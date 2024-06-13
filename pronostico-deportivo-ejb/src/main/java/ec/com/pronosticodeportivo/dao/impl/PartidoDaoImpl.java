/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ec.com.persistencia.constante.CriteriaTypeEnum;
import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.persistencia.util.Criteria;
import ec.com.persistencia.util.DateBetween;
import ec.com.pronosticodeportivo.dao.PartidoDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

/**
 * @author Marcelo
 *
 */
@Stateless(name="PartidoDao")
public class PartidoDaoImpl extends GenericDaoEjb<Partido, Integer> implements PartidoDao {

	/**
	 * @param type
	 */
	public PartidoDaoImpl() {
		super(Partido.class);
		
	}

	@Override
	public List<Partido> obtenerPartidosIngreso() {
		String[] criteriasAnd = { "fechaPublicacion","fechaMaximaRegistro" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.DATE_BETWEEN,CriteriaTypeEnum.DATE_BETWEEN };
		Date fechaActual=Calendar.getInstance().getTime();
		
		DateBetween fechaPub=new DateBetween(null, fechaActual);
		fechaPub.setTo(Calendar.getInstance().getTime());
		
		DateBetween fechaMaxReg=new DateBetween(fechaActual, null);
		fechaMaxReg.setFrom(fechaActual);
		
		Object[] valuesCriteriaAnd = { fechaPub,fechaMaxReg };
		
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
		return findByCriterias(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> getPartidosPendientes() {
		Query query = em.createQuery("select p from Partido p"
				+ " where p.etapa.estado = :estado "
				+ "order by partidoId asc");
		query.setParameter("estado", Boolean.TRUE);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> getPartidoNotificacion() {
		Calendar menor = Calendar.getInstance();
		Calendar mayor = Calendar.getInstance();
		//menor.set(2022, 10, 21, 8, 01, 03);
		//mayor.set(2022, 10, 21, 8, 01, 03);
		menor.add(Calendar.MINUTE, -5);
		mayor.add(Calendar.MINUTE, 5);
		
		Query query = em.createQuery("select p from Partido p where "
				+ "p.fechaPartido between :intervaloInferior and :intervaloSuperior "
				+ "order by p.fechaPartido, p.partidoId asc");
		query.setParameter("intervaloInferior", menor.getTime());
		query.setParameter("intervaloSuperior", mayor.getTime());
		return query.getResultList();
	}

}
