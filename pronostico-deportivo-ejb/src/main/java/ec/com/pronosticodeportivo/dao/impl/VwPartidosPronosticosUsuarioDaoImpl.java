/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import java.util.Date;
import java.util.List;

import ec.com.persistencia.constante.CriteriaTypeEnum;
import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.persistencia.util.Criteria;
import ec.com.pronosticodeportivo.dao.VwPartidosPronosticosUsuarioDao;
import ec.com.pronosticodeportivo.modelo.VwPartidosPronosticosUsuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

/**
 * @author Marcelo
 *
 */
@Stateless(name="VwPartidosPronosticoUsuarioDao")
public class VwPartidosPronosticosUsuarioDaoImpl
		extends GenericDaoEjb<VwPartidosPronosticosUsuario, Integer>
		implements VwPartidosPronosticosUsuarioDao {

	public VwPartidosPronosticosUsuarioDaoImpl() {
		super(VwPartidosPronosticosUsuario.class);
	}

	@Override
	public List<VwPartidosPronosticosUsuario> buscarPronosticoPorUsuario(String cedula) {
		String[] criteriasAnd= {"login"};
		CriteriaTypeEnum[] criteriasTypeEnum= {CriteriaTypeEnum.STRING_EQUALS};
		Object[] valuesCriteriaAnd= {cedula};
		
		String[] criteriasOrderBy= {"fechaPartido"};
		boolean[] asc= {true};
		
		Criteria criteria= new Criteria(null, null, null, criteriasAnd, criteriasTypeEnum, valuesCriteriaAnd, criteriasOrderBy, asc);// Criteria(null,null,criteriasAnd, criteriasTypeEnum, valuesCriteriaAnd, criteriasAnd, criteriasOrderBy,asc,null,null);// Criteria(criteriasAnd, criteriasTypeEnum, valuesCriteriaAnd);
		return findByCriterias(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VwPartidosPronosticosUsuario> getPronosticos(Date fecha) {
		StringBuilder sql = new StringBuilder("select * from futbol.vw_partidos_pronosticos_usuarios v ");
		//sql.append("where '").append(fecha).append("' between v.fecha_partido and v.fecha_partido + interval '5' minute ");
		sql.append("where now() between v.fecha_partido and v.fecha_partido + interval '5' minute ");
		sql.append("order by v.nombre asc");
		Query query = em.createNativeQuery(sql.toString(), VwPartidosPronosticosUsuario.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VwPartidosPronosticosUsuario> getPronosticos(Integer partidoId) {
		Query query = em.createQuery("select v from VwPartidosPronosticosUsuario v "
				+ "where v.partidoId = :partidoId");
		query.setParameter("partidoId", partidoId);
		return query.getResultList();
	}

}
