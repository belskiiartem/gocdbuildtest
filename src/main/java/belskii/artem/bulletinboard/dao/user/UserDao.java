package belskii.artem.bulletinboard.dao.user;

public interface UserDao {
	public boolean checkUser(String email);
	public String getFirstName(String email);
	public String getLastName(String email);
	public long addUser(String firstName, String lastname, String email);
	public User findUser(String email);
}
