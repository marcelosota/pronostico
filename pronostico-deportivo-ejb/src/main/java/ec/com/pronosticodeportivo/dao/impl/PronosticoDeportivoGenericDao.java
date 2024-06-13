/**
 * 
 */
package ec.com.pronosticodeportivo.dao.impl;

import java.io.Serializable;

import ec.com.persistencia.dao.ejb.GenericEmDaoEjb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author Marcelo
 *
 */
public class PronosticoDeportivoGenericDao<T, PK extends Serializable> extends GenericEmDaoEjb<T, PK> {

	public PronosticoDeportivoGenericDao(Class<T> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "pronosticoDeportivoPU")
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gob.dinardap.persistence.dao.ejb.GenericEmDaoEjb#getEm()
	 */
	@Override
	protected EntityManager getEm() {
		return this.em;
	}
}
