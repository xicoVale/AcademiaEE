package beans;

import javax.ejb.Local;

import entities.Users;

@Local
public interface QueryBeanLocal {

	public Boolean userExists(String userName);
	public void registerUser(String userName, String password);
	
	public void registerUser(Users user);
	public Users logIn(String userName, String password);
	
}
