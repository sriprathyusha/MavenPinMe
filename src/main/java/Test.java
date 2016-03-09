
import com.pinme.controllers.CategoryController;
import com.pinme.controllers.EventController;
import com.pinme.controllers.UserController;
import com.pinme.dao.UserDao;
import com.pinme.model.Address;
import com.pinme.model.Event;
import com.pinme.model.EventCategory;
import com.pinme.model.User;

/**
 * @author Shivanagesh Chandra Mar 8, 2016 1:29:57 PM
 * fileTest.java
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventCategory ec = new EventCategory();
		ec.setDescription("Food distribution");
		ec.setName("Food");
		
		EventCategory ec1 = new EventCategory();
		ec.setDescription("Clothes distribution");
		ec.setName("Clothes");
		
		EventCategory ec2 = new EventCategory();
		ec.setDescription("Entertainment");
		ec.setName("Entertainment");
		int categoryId = CategoryController.getInstance().addtEvent(ec);
		
		CategoryController.getInstance().addtEvent(ec1);
		CategoryController.getInstance().addtEvent(ec2);
		
		User us = new User("Shivangesh", "Chandra", "123456", "ch.shivanagesh@gmail.com", "848-333-1895");
		int userId = UserController.getInstance().addUser(us);
		
		Event ev = new Event("03-12-2016 11:15:00 AM", "03-12-2016 11:15:00 PM", "food distribution", -1, false, -1, "Free meal", userId, categoryId);
		Event ev1 = new Event("03-13-2016 11:15:00 AM", "03-13-2016 11:15:00 PM", "food distribution", 100, true, -1, "Free meal", userId, categoryId);
		Address ad = new Address("4106", "431 El camino real", "Santa Clara", "CA", "95050", "USA", " ", " ");
		
		int EventId = EventController.getInstance().createEvent(ad, categoryId, ev);

		System.out.println("*************");
		System.out.println(EventController.getInstance().getEvents());
		System.out.println("----------------------");
		EventController.getInstance().pinEvent(userId, EventId);
	
		
		
				
	}

}
