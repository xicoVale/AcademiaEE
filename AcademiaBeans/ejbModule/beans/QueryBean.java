package beans;

import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import entities.Users;

/**
 * Session Bean implementation class QueryBean
 */
@Stateless
public class QueryBean implements QueryBeanRemote, QueryBeanLocal,Serializable {
	
//	static Statement stmt = null;
	@Inject EntityManager em;
	
	
  
//    Default constructor.      
    public QueryBean() {
        // TODO Auto-generated constructor stub
    	
    }


	private Boolean userExists(String userName) {
		Boolean exist;
		exist=!em.createQuery("SELECT userName FROM users"
				+ " WHERE USERNAME = "+ userName+";").getResultList().isEmpty();
	
		return exist;
	}

	@Override
	public void registerUser(Users user) throws {
		
		if(userExists(userName)){
			throws
			
		}else{	
		
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		String saltString = salt.toString();
		String code = ((Integer)(user.getPassword()+saltString).hashCode()).toString();
		
		em.createQuery("INSERT INTO users (USERNAME,PASSWORD,SALT,USERROLE_ROLEID) VALUES ('"+user.getUserName()+"','"+code+"','"+saltString+"',"+3);		
		}
		
	}

	@Override
	public Users logIn(String userName, String password) {
		Users x =  (Users) em.createQuery("SELECT * FROM users WHERE USERNAME= "+userName).getResultList();
		
		return x;
	}





}
