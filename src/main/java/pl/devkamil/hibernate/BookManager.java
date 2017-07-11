package pl.devkamil.hibernate;

import org.hibernate.Session;
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

	protected void create() {
		Book book = new Book();
		book.setTitle("Head First Java");
		book.setAuthor("Kathy Sierra, Bert Bates");
		book.setPrice(89.39f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void read() {
		Session session = sessionFactory.openSession();

		long bookId = 2;
		Book book = session.get(Book.class, bookId);

		System.out.println("Title: " + book.getTitle());
		System.out.println("Author: " + book.getAuthor());
		System.out.println("Price: " + book.getPrice());

		session.close();
	}

	protected void update() {
		Book book = new Book();
		book.setId(2);
		book.setTitle("Java EE 6");
		book.setAuthor("Author");
		book.setPrice(39.99f);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(book);
		
		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		Book book = new Book();
		book.setId(9);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(book);
		
		session.getTransaction().commit();
		session.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BookManager manager = new BookManager();

		manager.setup();
		manager.delete();
		manager.exit();
		
	}

}
