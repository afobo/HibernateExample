package com.github.afobo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}