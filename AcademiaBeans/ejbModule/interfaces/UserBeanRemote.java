package interfaces;

import javax.ejb.Remote;

import entities.Users;

@Remote
public interface UserBeanRemote {
	public String registerUser(Users user);
	public String login(Users user);
	public Users getUser();
	public void  setUser(Users user);
}
