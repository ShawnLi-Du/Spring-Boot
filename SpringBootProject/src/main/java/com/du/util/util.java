package com.du.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class util {
	private static final SessionFactory factory = crateSessionFactory();

	private static SessionFactory crateSessionFactory() {

		StandardServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(serviceRegistryBuilder).buildMetadata().buildSessionFactory();

		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}

	public static void closegetSessionFactory() {
		if(factory != null) {
			factory.close();
		}
	}
}
