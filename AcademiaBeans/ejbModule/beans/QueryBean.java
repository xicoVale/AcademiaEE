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

	@Override
	public Boolean userExists(String userName) {
		Boolean exist;
		exist=!em.createQuery("SELECT userName FROM users"
				+ " WHERE USERNAME = "+ userName+";").getResultList().isEmpty();
	
		return exist;
	}

	@Override
	public void registerUser(String userName, String password) {
		
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		String saltString = salt.toString();
		String code = ((Integer)(password+saltString).hashCode()).toString();
		
		em.createQuery("INSERT INTO users (USERNAME,PASSWORD,SALT) VALUES ('"+userName+"','"+code+"','"+saltString+"'");		
		
		
	}

	@Override
	public Users logIn(String userName, String password) {
		em.createQuery("SELECT ")
		
		return null;
	}





}
