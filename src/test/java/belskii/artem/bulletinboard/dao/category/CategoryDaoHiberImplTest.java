package belskii.artem.bulletinboard.dao.category;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CategoryDaoHiberImplTest {

	@Test
	public void testGetAllCategories() {
		CategoryDaoHiberImpl category = new CategoryDaoHiberImpl();
		assertTrue(category.getAllCategories().size()>0);
		assertTrue(!category.getAllCategories().get(0).getCategoryTitle().equals(""));
	}

	@Test
	public void testAddCategories() {
		CategoryDaoHiberImpl category = new CategoryDaoHiberImpl();
		Random random = new Random();
		assertTrue(category.addCategories("category"+random.nextInt())>0);
		
	}

	@Test
	public void testRemoveCategories() {
		CategoryDaoHiberImpl category = new CategoryDaoHiberImpl();
		category.addCategories("categoryForRemoving");
		long categoryId=category.findCategory("categoryForRemoving");
		assertEquals("categoryForRemoving",category.removeCategories(categoryId));
	}
	
	@Test
	public void testFindCategory(){
		CategoryDaoHiberImpl category = new CategoryDaoHiberImpl();
		assertTrue(category.findCategory("one")>0);
	}

}
