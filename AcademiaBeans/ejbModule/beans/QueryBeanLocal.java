package beans;

import javax.ejb.Local;

@Local
public interface QueryBeanLocal {


	public Boolean userExists(String userName);
	public void registerUser(String userName, String password);
	public Boolean
	
}
