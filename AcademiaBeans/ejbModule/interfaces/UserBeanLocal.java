package interfaces;

import javax.ejb.Local;

import entities.Inqueries;
import entities.Users;

@Local
public interface UserBeanLocal {
	public String registerUser(Users user);
	public String login(Users user);
	public Users getUser();
	public void  setUser(Users user);
	
	
}
