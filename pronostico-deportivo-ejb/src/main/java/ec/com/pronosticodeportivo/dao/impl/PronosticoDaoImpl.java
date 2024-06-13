/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import java.util.List;

import ec.com.persistencia.constante.CriteriaTypeEnum;
import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.persistencia.util.Criteria;
import ec.com.pronosticodeportivo.dao.PronosticoDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.modelo.Pronostico;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

/**
 * @author Marcelo
 *
 */
@Stateless(name="PronosticoDao")
public class PronosticoDaoImpl extends GenericDaoEjb<Pronostico, Integer> implements PronosticoDao {

	/**
	 * @param type
	 */
	public PronosticoDaoImpl() {
		super(Pronostico.class);
		
	}

	@Override
	public List<Pronostico> obtenerPronosticosIngreso(String cedula) {
		/*String[] criteriasAnd = { "partido.fechaPublicacion","partido.fechaMaximaRegistro" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.DATE_BETWEEN,CriteriaTypeEnum.DATE_BETWEEN };
		
		DateBetween fechaPub=new DateBetween(null, Calendar.getInstance().getTime());
		DateBetween fechaMaxReg=new DateBetween(Calendar.getInstance().getTime(), null);
		
		Object[] valuesCriteriaAnd = { fechaPub,fechaMaxReg };
		
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
		return findByCriterias(criteria);*/
		Query query=
		em.createQuery(
		        "SELECT p,p.usuario,p.partido FROM Pronostico p "
		        + " RIGHT JOIN p.usuario ON p.usuario.login=:cedula "
		        + " RIGHT JOIN p.partido d "
		        + " where now() between p.partido.fechaPublicacion and p.partido.fechaMaximaRegistro");
		query.setParameter("cedula", cedula);
		
		@SuppressWarnings("unchecked")
		List <Pronostico> resultado=query.getResultList();
			return resultado;
	}

	@Override
	public Pronostico buscarPronosticoPorPartidoUsuario(Integer partidoId, String cedula) {
		String[] criteriasAnd = { "partido.partidoId","usuario.login" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS,CriteriaTypeEnum.STRING_EQUALS};
		
		Object[] valuesCriteriaAnd = { partidoId,cedula};
		
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
		List<Pronostico> res= findByCriterias(criteria);
		if (res!=null &&!res.isEmpty()) {
			return res.get(0);
		}else {
			return new Pronostico();
		}
	}

	@Override
	public void actualizarPuntajesPronosticos(Partido partidoSeleccionado) {
		Query query=em.createNativeQuery("update futbol.pronostico pro_act "
				+ " set puntaje =puntaje_obt.puntaje_obtenido"
				+ " from (select pr.pronostico_id, CASE"
				+ " WHEN p.marcador_local = pr.local AND p.marcador_visitante = pr.visitante THEN 5"
				+ " WHEN p.marcador_local > p.marcador_visitante AND pr.local > pr.visitante THEN 3"
				+ " WHEN p.marcador_local < p.marcador_visitante AND pr.local < pr.visitante THEN 3"
				+ " WHEN p.marcador_local = p.marcador_visitante AND pr.local = pr.visitante THEN 3"
				+ " ELSE 0"
				+ " end + CASE"
				+ " WHEN p.marcador_local = p.marcador_visitante and p.posibles_penales =true THEN"
				+ " CASE"
				+ " WHEN p.penales_local > p.penales_visitante AND pr.penales_local > pr.penales_visitante THEN 1"
				+ " WHEN p.penales_local < p.penales_visitante AND pr.penales_local < pr.penales_visitante THEN 1"
				+ " ELSE 0"
				+ " END"
				+ " ELSE 0"
				+ " END puntaje_obtenido            "
				+ " from futbol.partido p join futbol.pronostico pr on p.partido_id=pr.partido_id"
				+ " where p.partido_id ="+partidoSeleccionado.getPartidoId()+") puntaje_obt"
				+ " where puntaje_obt.pronostico_id=pro_act.pronostico_id ");
		
		query.executeUpdate();
	}

}
