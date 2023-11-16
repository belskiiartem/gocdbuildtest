package belskii.artem.bulletinboard.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import belskii.artem.bulletinboard.dao.advert.AdvertisementDao;
import belskii.artem.bulletinboard.dao.advert.AdvertisementDaoHiberImpl;
import belskii.artem.bulletinboard.dao.category.CategoryDao;
import belskii.artem.bulletinboard.dao.category.CategoryDaoHiberImpl;
import belskii.artem.bulletinboard.dao.user.UserDao;
import belskii.artem.bulletinboard.dao.user.UserDaoImplHiber;

@Controller
public class MainController {
	private UserDao user = new UserDaoImplHiber();
	private AdvertisementDao advertisement = new AdvertisementDaoHiberImpl();
	private CategoryDao category = new CategoryDaoHiberImpl();
	@RequestMapping(path="/", method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("categoryList", category.getAllCategories());
		modelAndView.addObject("advertisements", advertisement.getAll());
		return modelAndView;
	}
	
	@RequestMapping(path="/filter", method=RequestMethod.POST)
	public ModelAndView getFiltered(@ModelAttribute("email") String email, @ModelAttribute("categoryId") long categoryId){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("index");
		modelAndView.addObject("categoryList", category.getAllCategories());
		
		System.out.println("email " + email);
		System.out.println(categoryId);
		
		if (email.length()>0 & categoryId >=0){
			modelAndView.addObject("advertisements", advertisement.getFiltered(categoryId, email));
		} else if (email.length()>0){
			modelAndView.addObject("advertisements", advertisement.getFilteredByEmail(email));
		} else if (categoryId >=0){
			modelAndView.addObject("advertisements", advertisement.getFilteredByCategory(categoryId));
		} else {
			modelAndView.addObject("advertisements", advertisement.getAll());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(path="/userHome", method=RequestMethod.POST)
	public ModelAndView authorize(@ModelAttribute("email") String email, HttpServletResponse response){
		response.addCookie(new Cookie("email", email));
		String userZone="userHome";
		ModelAndView modelAndView = new ModelAndView();
		
		if(!user.checkUser(email)){
			userZone="newUser";
		} else{
			modelAndView.addObject("firstName", user.getFirstName(email));
			modelAndView.addObject("lastName", user.getLastName(email));
			modelAndView.addObject("myAdvertisement", advertisement.getFilteredByEmail(email));
		}
		modelAndView.addObject("email", email);
		modelAndView.setViewName(userZone);
		return modelAndView;
	}

	
	@RequestMapping(path="/userHome", method=RequestMethod.GET)
	public ModelAndView userHome(HttpServletRequest request){
		String email=this.getEmail(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userHome");
		modelAndView.addObject("firstName", user.getFirstName(email));
		modelAndView.addObject("lastName", user.getLastName(email));
		modelAndView.addObject("myAdvertisement", advertisement.getFilteredByEmail(email));
		return modelAndView;
	}

	@RequestMapping(path="/createNew", method=RequestMethod.GET)
	public ModelAndView prepareNew(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createNew");
		modelAndView.addObject("categoryList", category.getAllCategories());
		return modelAndView;		
	}

	@RequestMapping(path="/createNew", method=RequestMethod.POST)
	public String createNew(HttpServletRequest request, @ModelAttribute("categoryId") String categoryId, 
			@ModelAttribute("title") String title, @ModelAttribute("body") String body,
			@ModelAttribute("categoryTitle") String categoryTitle) {
		
		if ( categoryId.length()==0 & categoryTitle.length()>0){
			categoryId=String.valueOf(
					category.addCategories(categoryTitle)
					);
		}
		advertisement.addAdvertisement(user.findUser(this.getEmail(request)).getId(), title, body, Long.valueOf(categoryId));
		return "index";		
	
	}
	
	@RequestMapping(path="/delete", method=RequestMethod.POST)
	public ModelAndView confirmTheDeletion(HttpServletRequest request, @ModelAttribute("deleteId") Long deleteId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("confirmTheDeletion");
		modelAndView.addObject("deleteId", deleteId);
		modelAndView.addObject("title", advertisement.getById(deleteId).getAdvertTitle());
		modelAndView.addObject("body", advertisement.getById(deleteId).getAdvertText());
		modelAndView.addObject("category", advertisement.getById(deleteId).getCategoryId());
		return modelAndView;
		
	}

	@RequestMapping(path="/confirm", method=RequestMethod.POST)
	public ModelAndView confirm(HttpServletRequest request, @ModelAttribute("confirm") String confirm, @ModelAttribute("deleteId") Long deleteId,
			@ModelAttribute("category") String category){
		String resultMessage="This advertisment will be save...";
		if (confirm.equals("delete") &  
			user.findUser(this.getEmail(request)).getId() == advertisement.getById(deleteId).getUserId())
		{
			advertisement.delete(deleteId);
			resultMessage="Your advertisment removed";
		}
		ModelAndView modewlAndView = new ModelAndView();
		modewlAndView.setViewName("resultPage");
		modewlAndView.addObject("resultMessage", resultMessage);
		return modewlAndView;
	}
	
	@RequestMapping(path="/newUser", method=RequestMethod.POST)
	public String createNewUser(@ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName, @ModelAttribute("email") String email){
		user.addUser(firstName, lastName, email);
		return "index";
	}
	
	private String getEmail(HttpServletRequest request){
		String email="";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
	        for (int i = 0; i < cookies.length; i++) {
	        	if (cookies[i].getName().equals("email")){
	        		email=cookies[i].getValue().toString();
	        	}
	        }
	    }
		return email;
	}
	
	@RequestMapping(path="/manageCategory", method=RequestMethod.GET)
	public ModelAndView manageCategory(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manageCategory");
		modelAndView.addObject("categoryList", category.getAllCategories());
		return modelAndView;
	}

	@RequestMapping(path="/createCategory", method=RequestMethod.POST)
	public String createCategory(@ModelAttribute("categoryTitle") String manageCategory){
		category.addCategories(manageCategory);
		return "index";
	}
	
	@RequestMapping(path="/deleteCategory", method=RequestMethod.POST)
	public String deleteCategory(@ModelAttribute("categoryId") String categoryId ){
		category.removeCategories(Long.valueOf(categoryId));
		return "index";
	}


}
