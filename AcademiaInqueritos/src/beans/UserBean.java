package beans;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entities.UserRoles;
import entities.Users;

/**
 * Session Bean implementation class QueryBean
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	private Users user;
	
  
	/**   
	 * Default constructor.  
	 **/   
    public UserBean() {
    	super();
    }

    @PostConstruct
    public void init() {
    	user = new Users();
    }
    
	public Users getUser() {
		return this.user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	private Boolean userExists(String userName) {
		Users user = em.find(Users.class, userName);
	
		if (user == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public String registerUser(Users user)  throws Exception{

		if (userExists(user.getUserName())) {
			return "UserExists";

		} 
		else {
			final Random r = new SecureRandom();
			byte[] salt = new byte[32];
			r.nextBytes(salt);
			String saltString = salt.toString();
			String code = ((Integer) (user.getPassword() + saltString).hashCode()).toString();
			user.setPassword(code);
			user.setSalt(saltString);
			
			UserRoles role = em.find(UserRoles.class, 3);
			user.setUserRole(role);
			
			utx.begin();
			em.persist(user);
			utx.commit();
	
			return "success";
		}
		
	}

	/**
	 * Logs a user into the site
	 * 
	 * @param user - The user to be added
	 * @return
	 */
	public String login(Users user){
		this.user = em.find(Users.class, user.getUserName());
		
		if(this.user == null){
			return "userNotExists";
			
		}else{	
			String salt = this.user.getSalt();
			String code = ((Integer) (user.getPassword() + salt).hashCode()).toString();
			if(code.equals(this.user.getPassword())){
				return "success";
			}
			else {
				this.user = new Users();
				return "passwordFail";
			}
		}
		
	}
}
