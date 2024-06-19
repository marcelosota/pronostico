package ec.com.persistencia.dao.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ec.com.persistencia.constante.CriteriaTypeEnum;
import ec.com.persistencia.dao.GenericDao;
import ec.com.persistencia.util.Criteria;
import ec.com.persistencia.util.CriteriaInnerJoin;
import ec.com.persistencia.util.GenericDaoUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public abstract class GenericDaoEjb<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> type;

	@Override
	public String getType() {
		return EJB;
	}

	public GenericDaoEjb(Class<T> type) {
		this.type = type;
	}

	public void update(T o) {
		em.merge(o);
	}

	public void save(T o) {
		em.persist(o);
	}

	@Deprecated
	public void saveOrUpdate(T o) {
	}

	public T load(PK id) {
		return (T) em.find(type, id);
	}

	@Deprecated
	public T get(PK id) {
		return (T) em.find(type, id);
	}

	public void delete(T o) {
		em.remove(o);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query query = em.createQuery(" from " + type.getName());
		return query.getResultList();
	}

	public void flush() {
		em.flush();
	}

	public void clear() {
		em.clear();
	}

	public void refresh(T o) {
		em.refresh(o);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriterias(Criteria criteria) {

		if (criteria instanceof CriteriaInnerJoin) {
			CriteriaInnerJoin ci = (CriteriaInnerJoin) criteria;
			return this.findByCriterias(criteria.getCriteriasOr(),
					criteria.getTypesOr(), criteria.getValuesCriteriaOr(),
					criteria.getCriteriasAnd(), criteria.getTypesAnd(),
					criteria.getValuesCriteriaAnd(),
					criteria.getCriteriasOrderBy(), criteria.getAsc(),
					ci.getInnerJoinObject(), ci.getInnerJoinProperty(),
					ci.getInnerJoinValue(), ci.getInnerJoinOperator());

		} else {

			if (criteria.getQuery() == null) {
				return this.findByCriterias(criteria.getCriteriasOr(),
						criteria.getTypesOr(), criteria.getValuesCriteriaOr(),
						criteria.getCriteriasAnd(), criteria.getTypesAnd(),
						criteria.getValuesCriteriaAnd(),
						criteria.getCriteriasOrderBy(), criteria.getAsc(),
						null, null, null);

			} else {
				// si envia su propia query
				StringBuffer ql = new StringBuffer("select obj ")
						.append(criteria.getQuery());
				if (criteria.getOrderBy() != null) {
					ql.append(" " + criteria.getOrderBy());
				}

				Query query = em.createQuery(ql.toString());
				int positionParameter = 1;
				if (criteria.getParameters() != null) {
					for (Object object : criteria.getParameters()) {
						query.setParameter(positionParameter, object);
						positionParameter++;
					}
				}

				List<T> list = query.getResultList();
				return list;
			}

		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriterias(Criteria criteria, int firstRows,
			int totalRows) {

		if (criteria instanceof CriteriaInnerJoin) {
			CriteriaInnerJoin ci = (CriteriaInnerJoin) criteria;

			return this.findByCriterias(criteria.getCriteriasOr(),
					criteria.getTypesOr(), criteria.getValuesCriteriaOr(),
					criteria.getCriteriasAnd(), criteria.getTypesAnd(),
					criteria.getValuesCriteriaAnd(),
					criteria.getCriteriasOrderBy(), criteria.getAsc(),
					ci.getInnerJoinObject(), ci.getInnerJoinProperty(),
					ci.getInnerJoinValue(), ci.getInnerJoinOperator(),
					firstRows, totalRows);
		} else {

			if (criteria.getQuery() == null) {
				return this.findByCriterias(criteria.getCriteriasOr(),
						criteria.getTypesOr(), criteria.getValuesCriteriaOr(),
						criteria.getCriteriasAnd(), criteria.getTypesAnd(),
						criteria.getValuesCriteriaAnd(),
						criteria.getCriteriasOrderBy(), criteria.getAsc(),
						null, null, null, firstRows, totalRows);
			} else {
				// si envia su propia query
				StringBuffer ql = new StringBuffer("select obj ")
						.append(criteria.getQuery());
				if (criteria.getOrderBy() != null) {
					ql.append(" " + criteria.getOrderBy());
				}

				Query query = em.createQuery(ql.toString());

				int positionParameter = 1;
				if (criteria.getParameters() != null) {
					for (Object object : criteria.getParameters()) {
						query.setParameter(positionParameter, object);
						positionParameter++;
					}
				}

				if (firstRows >= 0) {
					query.setFirstResult(firstRows);
					query.setMaxResults(totalRows);
				}
				List<T> list = query.getResultList();
				return list;

			}

		}
	}

	public Long totalFindByCriterias(Criteria criteria) {

		if (criteria instanceof CriteriaInnerJoin) {

			CriteriaInnerJoin ci = (CriteriaInnerJoin) criteria;

			return this.totalFindByCriterias(criteria.getCriteriasOr(),
					criteria.getTypesOr(), criteria.getValuesCriteriaOr(),
					criteria.getCriteriasAnd(), criteria.getTypesAnd(),
					criteria.getValuesCriteriaAnd(), ci.getInnerJoinObject(),
					ci.getInnerJoinProperty(), ci.getInnerJoinValue(),
					ci.getInnerJoinOperator());
		} else {

			if (criteria.getQuery() == null) {
				return this.totalFindByCriterias(criteria.getCriteriasOr(),
						criteria.getTypesOr(), criteria.getValuesCriteriaOr(),
						criteria.getCriteriasAnd(), criteria.getTypesAnd(),
						criteria.getValuesCriteriaAnd(), null, null, null);
			} else {

				// si envia su propia query
				StringBuffer ql = new StringBuffer("select count(*) ")
						.append(criteria.getQuery());
				// no se pone order by
				Query query = em.createQuery(ql.toString());

				int positionParameter = 1;
				if (criteria.getParameters() != null) {
					for (Object object : criteria.getParameters()) {
						query.setParameter(positionParameter, object);
						positionParameter++;
					}
				}

				Long total = (Long) query.getSingleResult();
				return total;
			}

		}
	}

	public List<T> findByCriterias(String[] criteriasOr,
			CriteriaTypeEnum[] typesOr, Object[] valuesCriteriaOr,
			String[] criteriasAnd, CriteriaTypeEnum[] typesAnd,
			Object[] valuesCriteriaAnd, String[] criteriasOrderBy,
			boolean[] asc, String innerJoinObject, String innerJoinProperty,
			Object innerJoinValue) {

		List<T> list = findByCriterias(criteriasOr, typesOr, valuesCriteriaOr,
				criteriasAnd, typesAnd, valuesCriteriaAnd, criteriasOrderBy,
				asc, innerJoinObject, innerJoinProperty, innerJoinValue, -1, -1);
		return list;
	}

	public List<T> findByCriterias(String[] criteriasOr,
			CriteriaTypeEnum[] typesOr, Object[] valuesCriteriaOr,
			String[] criteriasAnd, CriteriaTypeEnum[] typesAnd,
			Object[] valuesCriteriaAnd, String[] criteriasOrderBy,
			boolean[] asc, String innerJoinObject, String innerJoinProperty,
			Object innerJoinValue, int firstRow, int totalRows) {

		return findByCriterias(criteriasOr, typesOr, valuesCriteriaOr,
				criteriasAnd, typesAnd, valuesCriteriaAnd, criteriasOrderBy,
				asc, innerJoinObject, innerJoinProperty, innerJoinValue, null,
				firstRow, totalRows);
	}

	public Long totalFindByCriterias(String[] criteriasOr,
			CriteriaTypeEnum[] typesOr, Object[] valuesCriteriaOr,
			String[] criteriasAnd, CriteriaTypeEnum[] typesAnd,
			Object[] valuesCriteriaAnd, String innerJoinObject,
			String innerJoinProperty, Object innerJoinValue) {
		return totalFindByCriterias(criteriasOr, typesOr, valuesCriteriaOr,
				criteriasAnd, typesAnd, valuesCriteriaAnd, innerJoinObject,
				innerJoinProperty, innerJoinValue, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.saviasoft.persistence.util.dao.GenericDao#findByCriterias(java.lang
	 * .String[], com.saviasoft.persistence.util.constant.CriteriaTypeEnum[],
	 * java.lang.Object[], java.lang.String[],
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum[],
	 * java.lang.Object[], java.lang.String[], boolean[], java.lang.String,
	 * java.lang.String, java.lang.Object,
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum)
	 */
	@Override
	public List<T> findByCriterias(String[] criteriasOr,
			CriteriaTypeEnum[] typesOr, Object[] valuesCriteriaOr,
			String[] criteriasAnd, CriteriaTypeEnum[] typesAnd,
			Object[] valuesCriteriaAnd, String[] criteriasOrderBy,
			boolean[] asc, String innerJoinObject, String innerJoinProperty,
			Object innerJoinValue, CriteriaTypeEnum innerJoinOperator) {
		List<T> list = findByCriterias(criteriasOr, typesOr, valuesCriteriaOr,
				criteriasAnd, typesAnd, valuesCriteriaAnd, criteriasOrderBy,
				asc, innerJoinObject, innerJoinProperty, innerJoinValue,
				innerJoinOperator, -1, -1);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.saviasoft.persistence.util.dao.GenericDao#findByCriterias(java.lang
	 * .String[], com.saviasoft.persistence.util.constant.CriteriaTypeEnum[],
	 * java.lang.Object[], java.lang.String[],
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum[],
	 * java.lang.Object[], java.lang.String[], boolean[], java.lang.String,
	 * java.lang.String, java.lang.Object,
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriterias(String[] criteriasOr,
			CriteriaTypeEnum[] typesOr, Object[] valuesCriteriaOr,
			String[] criteriasAnd, CriteriaTypeEnum[] typesAnd,
			Object[] valuesCriteriaAnd, String[] criteriasOrderBy,
			boolean[] asc, String innerJoinObject, String innerJoinProperty,
			Object innerJoinValue, CriteriaTypeEnum innerJoinOperator,
			int firstRow, int totalRows) {
		List<Object> parameters = new ArrayList<Object>();

		String inner = (innerJoinObject != null) ? ("inner join obj."
				+ innerJoinObject + " " + innerJoinObject) : "";

		StringBuffer hql = new StringBuffer("select obj from " + type.getName()
				+ " obj " + inner + " where (");

		GenericDaoUtil.buildQueryCriteria(hql, parameters, criteriasOr,
				typesOr, valuesCriteriaOr, criteriasAnd, typesAnd,
				valuesCriteriaAnd, criteriasOrderBy, asc, innerJoinObject,
				innerJoinProperty, innerJoinValue, innerJoinOperator);

		Query query = em.createQuery(hql.toString());

		int positionParameter = 1;
		for (Object object : parameters) {
			query.setParameter(positionParameter, object);
			positionParameter++;
		}

		if (firstRow >= 0) {
			query.setFirstResult(firstRow);
			query.setMaxResults(totalRows);
		}
		List<T> list = query.getResultList();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.saviasoft.persistence.util.dao.GenericDao#totalFindByCriterias(java
	 * .lang.String[],
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum[],
	 * java.lang.Object[], java.lang.String[],
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum[],
	 * java.lang.Object[], java.lang.String, java.lang.String, java.lang.Object,
	 * com.saviasoft.persistence.util.constant.CriteriaTypeEnum)
	 */
	@Override
	public Long totalFindByCriterias(String[] criteriasOr,
			CriteriaTypeEnum[] typesOr, Object[] valuesCriteriaOr,
			String[] criteriasAnd, CriteriaTypeEnum[] typesAnd,
			Object[] valuesCriteriaAnd, String innerJoinObject,
			String innerJoinProperty, Object innerJoinValue,
			CriteriaTypeEnum innerJoinOperator) {
		List<Object> parameters = new ArrayList<Object>();

		String inner = (innerJoinObject != null) ? ("inner join obj."
				+ innerJoinObject + " " + innerJoinObject) : "";

		StringBuffer hql = new StringBuffer("select count(*) from "
				+ type.getName() + " obj " + inner + " where (");

		GenericDaoUtil.buildQueryCriteria(hql, parameters, criteriasOr,
				typesOr, valuesCriteriaOr, criteriasAnd, typesAnd,
				valuesCriteriaAnd, null, null, innerJoinObject,
				innerJoinProperty, innerJoinValue, null);

		Query query = em.createQuery(hql.toString());

		int positionParameter = 1;
		for (Object object : parameters) {
			query.setParameter(positionParameter, object);
			positionParameter++;
		}

		Long total = (Long) query.getSingleResult();
		return total;
	}
}