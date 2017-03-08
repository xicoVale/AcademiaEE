package beans;

import javax.ejb.Local;

import entities.Users;

@Local
public interface QueryBeanLocal {
	public String registerUser(Users user);
	public String login(Users user);
	public Users getUser();
	public void  setUser(Users user);
	
}
