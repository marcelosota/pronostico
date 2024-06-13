/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

import ec.com.persistencia.constante.CriteriaTypeEnum;
import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.persistencia.util.Criteria;
import ec.com.pronosticodeportivo.dao.UsuarioDao;
import ec.com.pronosticodeportivo.modelo.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

/**
 * @author Marcelo
 *
 */
@Stateless(name = "UsuarioDao")
//@ApplicationScoped
public class UsuarioDaoImpl extends GenericDaoEjb<Usuario, Integer> implements UsuarioDao {

	/**
	 * @param type
	 */
	public UsuarioDaoImpl() {
		super(Usuario.class);

	}

	@Override
	public Usuario autenticarUsuario(String cedula, String password) {
		try {
			String contrasenaEnccriptada;
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();

			crypt.update(password.getBytes("UTF-8"));

			contrasenaEnccriptada = new BigInteger(1, crypt.digest()).toString(16);

			String[] criteriasAnd = { "login", "contrasena" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS, CriteriaTypeEnum.STRING_EQUALS };
			Object[] valuesCriteriaAnd = { cedula, contrasenaEnccriptada };

			Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
			return findByCriterias(criteria).get(0);
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public Usuario obtenerUsuarioPorLogin(String username) {
		try {
			String[] criteriasAnd = { "login" };
			CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
			Object[] valuesCriteriaAnd = { username };
			
			Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
			return findByCriterias(criteria).get(0);
		} catch (Exception e) {
			return null;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuarios(List<Integer> usuarioId) {
		Query query = em.createQuery("select u from Usuario u "
				+ "where u.usuarioId in :usuarioId "
				+ "and u.estado = :estado "
				+ "order by u.nombre, u.apellido asc");
		query.setParameter("usuarioId", usuarioId);
		query.setParameter("estado", "A");
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuarios() {
		Query query = em.createQuery("select u from Usuario u "
				+ "order by u.nombre, u.apellido asc");
		return query.getResultList();
	}

}
