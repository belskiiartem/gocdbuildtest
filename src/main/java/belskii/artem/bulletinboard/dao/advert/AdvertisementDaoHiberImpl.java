package belskii.artem.bulletinboard.dao.advert;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import antlr.collections.List;
import belskii.artem.bulletinboard.dao.category.Category;
import belskii.artem.bulletinboard.dao.category.CategoryDao;
import belskii.artem.bulletinboard.dao.category.CategoryDaoHiberImpl;
import belskii.artem.bulletinboard.dao.user.UserDao;
import belskii.artem.bulletinboard.dao.user.UserDaoImplHiber;

public class AdvertisementDaoHiberImpl implements AdvertisementDao{
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public Long addAdvertisement(Long userId, String title, String body, Long categoryId) {
		CategoryDao category = new CategoryDaoHiberImpl();
		Transaction transaction = null;
		Session session = null;
		Long id=-1L;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Advert advert = new Advert();
			advert.setCategoryId(categoryId);
			advert.setAdvertText(body);
			advert.setAdvertTitle(title);
			advert.setUserId(userId);
			id=(Long) session.save(advert);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return id;
	}

	public ArrayList<Advert> getAll() {
		Transaction transaction = null;
		Session session = null;
		ArrayList<Advert> advertList =new ArrayList<Advert>();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			advertList= (ArrayList<Advert>) session.createCriteria(Advert.class).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return advertList;

	}

	public boolean delete(Long id) {
		boolean result=true;
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Advert advert = session.get(Advert.class, id);
			session.delete(advert);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			result=false;
		} finally {
			session.close();
		}

		return result;
	}

	public ArrayList<Advert> getFilteredByEmail(String usermail) {
		UserDao user = new UserDaoImplHiber();
		Long userId=user.findUser(usermail).getId();
		Transaction transaction = null;
		Session session = null;
		ArrayList<Advert> filteredAdvertList =null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			filteredAdvertList = (ArrayList<Advert>) session.createCriteria(Advert.class).add(Restrictions.eq("userId", userId)).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return filteredAdvertList;
	}
	
	public ArrayList<Advert> getFilteredByCategory(long categoryId) {
		Transaction transaction = null;
		Session session = null;
		ArrayList<Advert> filteredAdvertList =null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			filteredAdvertList = (ArrayList<Advert>) session.createCriteria(Advert.class).add(Restrictions.eq("categoryId", categoryId)).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return filteredAdvertList;
	}
	
	public ArrayList<Advert> getFiltered(long categoryId, String email) {
		UserDao user = new UserDaoImplHiber();
		Long userId=user.findUser(email).getId();
		Transaction transaction = null;
		Session session = null;
		ArrayList<Advert> filteredAdvertList =null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			filteredAdvertList = (ArrayList<Advert>) session.createCriteria(Advert.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("categoryId", categoryId)).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return filteredAdvertList;
	}

	@Override
	public Advert getById(Long advertId) {
		Transaction transaction = null;
		Session session = null;
		Advert advert  =null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			advert= session.get(Advert.class, advertId);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return advert;
	}


}
