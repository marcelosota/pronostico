/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import ec.com.persistencia.dao.ejb.GenericDaoEjb;
import ec.com.pronosticodeportivo.dao.EquipoDao;
import ec.com.pronosticodeportivo.modelo.Equipo;
import jakarta.ejb.Stateless;

/**
 * @author Marcelo
 *
 */
@Stateless(name="EquipoDao")
public class EquipoDaoImpl extends GenericDaoEjb<Equipo, Integer> implements EquipoDao {

	public EquipoDaoImpl() {
		super(Equipo.class);
	}

}
