package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericRepoImpl implements GenericRepo {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override

	public <T> T save(Object obj) {
		return (T) entityManager.merge(obj);
	}

	@Override

	public <T> T fetchById(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public <T> void deleteById(Class<T> clazz, Object id) {
		T obj = this.fetchById(clazz, id);
		entityManager.remove(obj);
	}

}
