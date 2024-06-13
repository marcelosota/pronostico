/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.pronosticodeportivo.dao.InscripcionDao;
import ec.com.pronosticodeportivo.modelo.Inscripcion;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="InscripcionDao")
public class InscripcionDaoImpl extends GenericDaoEjb<Inscripcion, Integer> implements InscripcionDao {

	/**
	 * @param type
	 */
	public InscripcionDaoImpl() {
		super(Inscripcion.class);
		
	}

}
