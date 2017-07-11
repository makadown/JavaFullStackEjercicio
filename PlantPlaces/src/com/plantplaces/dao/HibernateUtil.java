package com.plantplaces.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
// Crear el SessionFactory desde hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) 
		{
			System.err.println("Creacion de SessionFactory Inicial ha fallado " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public static void shutdown()
	{
		//cierra cache y pools de conexión
		getSessionFactory().close();
	}
}
