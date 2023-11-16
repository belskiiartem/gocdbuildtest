package belskii.artem.bulletinboard.dao.advert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import belskii.artem.bulletinboard.dao.category.Category;

public class AdvertisementDaoHiberImplTest {
	private Category category  = new Category();
	@Before
	public void setUp(){
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.addAdvertisement(Long.valueOf(1), "pink elephant", "Buy new pink elephant, with low price!", 1L)>0);
	}
	
	@Test
	public void testAddAdvertisement() {
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.addAdvertisement(Long.valueOf(1), "pink elephant", "Buy new pink elephant, with low price!", 1L)>0);
	}
	
	@Test(expected=java.lang.NoSuchMethodError.class)
	public void testaddTooSmoolValue() {
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		advertisement.addAdvertisement(Long.valueOf(1), "smool", "too smool!", 1L);
	}

	@Test
	public void testGetAll() {
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.getAll().size()>0);
	}
	
	@Ignore
	@Test 
	public void testDelete() {
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.delete(1L));	
	}

	@Test
	public void testGetFiltered() {
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.getFiltered(1L, "belskiiartem@gmail.com").size()>0);
	}
	
	@Test
	public void testGetById(){
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.getById(25L).getAdvertText().length()>0);
	}
	
	@Test
	public void testGetFilteredByEmail(){
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.getFilteredByEmail("belskiiartem@gmail.com").size()>0);
	}
	
	@Test
	public void testGetFilteredByCategory(){
		AdvertisementDaoHiberImpl advertisement = new AdvertisementDaoHiberImpl ();
		assertTrue(advertisement.getFilteredByCategory(1L).size()>0);
	}
}
