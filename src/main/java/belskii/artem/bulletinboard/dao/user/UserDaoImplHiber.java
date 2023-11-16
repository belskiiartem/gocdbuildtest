package belskii.artem.bulletinboard.dao.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import belskii.artem.bulletinboard.dao.category.CategoryDao;
import belskii.artem.bulletinboard.dao.category.CategoryDaoHiberImpl;

public class UserDaoImplHiber implements UserDao{
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@Override
	public boolean checkUser(String email) {
		boolean answer=true;
		try {
			this.findUser(email).getId();
		} catch (Exception e) {
			answer=false;
		}
		return answer;
	}

	@Override
	public String getFirstName(String email) {
		return this.findUser(email).getFirstName();
	}

	@Override
	public String getLastName(String email) {
		return this.findUser(email).getLastNamer();
	}

	@Override
	public long addUser(String firstName, String lastname, String email) {
		CategoryDao category = new CategoryDaoHiberImpl();
		Transaction transaction = null;
		Session session = null;
		Long id=-1L;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			User user = new User();
			user.setFirstName(firstName);
			user.setLastNamer(lastname);
			user.setEmail(email);
			id=(Long) session.save(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return id;

	}

	@Override
	public User findUser(String email) {
		Transaction transaction = null;
		Session session = null;
		User user=new User();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			user=(User) session.createCriteria(User.class).add(Restrictions.eq("email", email).ignoreCase()).uniqueResult(); 
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return user;
	}

}
