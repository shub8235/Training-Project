package com.lti.repository;

public interface GenericRepo {

	public <T> T save(Object obj);

	public <T> T fetchById(Class<T> clazz, Object id);

	public <T> void deleteById(Class<T> clazz, Object id);

}