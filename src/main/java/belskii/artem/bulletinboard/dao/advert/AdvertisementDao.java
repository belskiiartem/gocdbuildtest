package belskii.artem.bulletinboard.dao.advert;

import java.util.ArrayList;

public interface AdvertisementDao {
	public Long addAdvertisement(Long userId, String title, String body, Long categoryId);
	public ArrayList<Advert> getAll();
	public boolean delete(Long id);
	public Advert getById(Long advertId);
	public ArrayList<Advert> getFiltered(long categoryId, String email);
	public ArrayList<Advert> getFilteredByEmail(String email);
	public ArrayList<Advert> getFilteredByCategory(long categoryId);
}
