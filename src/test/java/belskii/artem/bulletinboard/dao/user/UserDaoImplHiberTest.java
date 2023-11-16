package belskii.artem.bulletinboard.dao.user;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserDaoImplHiberTest {

	@Test
	public void testCheckUser() {
		UserDao user = new UserDaoImplHiber();
		assertTrue(user.checkUser("belskiiartem@gmail.com"));
		assertTrue(!user.checkUser("UnExist@gmail.com"));
	}

	@Test
	public void testGetfirstname() {
		UserDao user = new UserDaoImplHiber();
		assertEquals("Belskii",user.getFirstName("belskiiartem@gmail.com"));
	}

	@Test
	public void testGetlastname() {
		UserDao user = new UserDaoImplHiber();
		assertEquals("Artem",user.getLastName("belskiiartem@gmail.com"));
	}

	@Test
	public void testAddUser() {
		UserDao user = new UserDaoImplHiber();
		assertTrue(user.addUser("Belskii", "Artem", "belskiiartem@gmail.com")>0);
	}

	@Test
	public void testFindUser() {
		UserDao user = new UserDaoImplHiber();
		assertTrue(!user.findUser("belskiiartem@gmail.com").getFirstName().equals(""));
	}

}
