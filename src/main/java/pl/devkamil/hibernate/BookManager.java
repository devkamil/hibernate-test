package pl.devkamil.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {

	protected SessionFactory sessionFactory;

	protected void setup() {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void exit() {

		sessionFactory.close();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BookManager manager = new BookManager();

		manager.setup();

		manager.exit();
	}

}
