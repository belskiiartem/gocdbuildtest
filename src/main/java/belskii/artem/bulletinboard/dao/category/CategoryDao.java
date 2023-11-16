package belskii.artem.bulletinboard.dao.category;

import java.util.ArrayList;

public interface CategoryDao {
	public ArrayList<Category> getAllCategories();
	public Long addCategories(String title);
	public String removeCategories(Long id);
	public Long findCategory(String categoryTitle);
}
