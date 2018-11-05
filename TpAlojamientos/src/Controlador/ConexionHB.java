package Controlador;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ConexionHB {
	private static SessionFactory sessionfactory;

	public static SessionFactory iniciarConexion() {
		try {
			Configuration conf = new Configuration();
			conf.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties())
					.buildServiceRegistry();
			sessionfactory = conf.buildSessionFactory(serviceRegistry);
			return sessionfactory;
		} catch (Exception e) {
			LOG.info("Error al iniciar la clase ConexionHB");
			return null;
		}
	}

	public static void cerrarConexion() {
		try {
			sessionfactory.close();
		} catch (Exception e) {
			LOG.info("Error al cerrar ConexionHB");
		}

	}
}
