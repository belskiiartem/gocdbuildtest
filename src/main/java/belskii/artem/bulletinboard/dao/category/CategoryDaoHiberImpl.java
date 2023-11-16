package belskii.artem.bulletinboard.dao.category;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import antlr.collections.List;

public class CategoryDaoHiberImpl implements CategoryDao{
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public ArrayList<Category> getAllCategories() {
		Transaction transaction = null;
		Session session = null;
		ArrayList<Category> categoryList =new ArrayList<Category>();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			categoryList=(ArrayList<Category>) session.createCriteria(Category.class).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return categoryList;

	}

	public String removeCategories(Long id) {
		Transaction transaction = null;
		Session session = null;
		String removedCategory="";
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Category category = session.get(Category.class, id);
			session.delete(category);
			transaction.commit();
			removedCategory=category.getCategoryTitle();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return removedCategory;		
	}

	public Long addCategories(String title) {
		Transaction transaction = null;
		Session session = null;
		Category category = new Category();
		Long id=-1L;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			category.setCategoryTitle(title);
			id=(Long) session.save(category);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public Long findCategory(String categoryTitle) {
		Transaction transaction = null;
		Session session = null;
		Category category=null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			category=(Category) session.createCriteria(Category.class).add(Restrictions.eq("categoryTitle", categoryTitle)).uniqueResult(); 
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return category.getId();
	}

}
