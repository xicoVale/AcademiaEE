package beans;

import javax.ejb.Remote;

import entities.Users;

@Remote
public interface QueryBeanRemote {
	public String registerUser(Users user);
	public String logIn(Users user);
	public Users getUser();
	public void  setUser(Users user);
}