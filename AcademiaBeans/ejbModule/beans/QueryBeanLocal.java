package beans;

import javax.ejb.Local;

import entities.Users;

@Local
public interface QueryBeanLocal {


	
	public void registerUser(Users user);
	public Users logIn(String userName, String password);
	
}
