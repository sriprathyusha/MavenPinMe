package com.pinme.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.pinme.dao.AddressDao;
import com.pinme.dao.EventCategoryDao;
import com.pinme.dao.EventDao;
import com.pinme.dao.TokenDao;
import com.pinme.dao.UserDao;
import com.pinme.mail.Mail;
import com.pinme.model.Address;
import com.pinme.model.Event;
import com.pinme.model.Token;
import com.pinme.model.User;

/**
 * @author Shivanagesh Chandra Mar 8, 2016 1:14:53 AM fileEventController.java
 */
public class EventController {

	private static EventController instance = null;
	private EventDao eventDao;
	private EventCategoryDao eventCategory;
	private AddressDao address;
	private TokenDao tokendao;
	private UserDao userDao;

	/**
	 * 
	 */
	public EventController() {
		eventDao = new EventDao();
		eventCategory = new EventCategoryDao();
		address = new AddressDao();
		tokendao = new TokenDao();
		userDao = new UserDao();
	}

	public static EventController getInstance() {
		if (instance == null) {
			instance = new EventController();
		}
		return instance;
	}
	
	
	public List<Event> getEvents(){
		return eventDao.getEvents();
	}

	public int createEvent(Address ad, int categoryId, Event event) {
		try {
			int addressId = address.addAddress(ad);
			event.setAddressId(addressId);
			event.setCategoryId(categoryId);
			return eventDao.addEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void updateEvent(Address ad, int categoryId, Event event) {
		try {
			address.updateAddress(ad.getId(), ad);
			event.setCategoryId(categoryId);
			eventDao.addEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pinEvent(int userid,int eventId) {
		try {
//			if(eventDao.getEvent(eventId).isTokenized()){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String timeStamp = dateFormat.format(date);
			Token t = new Token(eventId,userid,timeStamp);
			int tokenId = tokendao.generateToken(t);
//			System.out.println(userid);
//			User u = userDao.getUser(userid);
//			eventDao.getEvent(eventId).setTokenLimit(eventDao.getEvent(eventId).getTokenLimit()-1);
			//System.out.println(u);
			Mail.sendMail("ch.shivanagesh@gmail.com", "Hey there, \n Your token number is "+tokenId+" \n Thanks,\n Pinme", "Your token");
		//}
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void removeEvent(int id) {
		try {
			 if(eventDao.getEvent(id) != null){
				  Event ev = eventDao.getEvent(id);
				  address.removeAddress(ev.getAddressId());
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
