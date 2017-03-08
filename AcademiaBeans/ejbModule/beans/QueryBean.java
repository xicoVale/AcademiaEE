package beans;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.UserRoles;
import entities.Users;

/**
 * Session Bean implementation class QueryBean
 */
@SessionScoped
public class QueryBean implements QueryBeanRemote, QueryBeanLocal, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext (name="AcademiaEntities")
	private EntityManager em;
	
// Faz o mesmo que as duas linhas anteriores
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("AcademiaEntities");
//	EntityManager em = emf.createEntityManager();
	
	private Users user=new Users();
	
  
/**   Default constructor.   **/   
    public QueryBean() {
        
    	
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

	@Override
	public String registerUser(Users user) {

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
			
			em.persist(user);
			
			return "success";
		}
		
	}

	@Override
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
				this.user = null;
				return "passwordFail";
			}
		}
		
	}


	@Override
	public Users getUser() {
		
		return this.user;
	}


	@Override
	public void setUser(Users user) {
		this.user=user;
	}






}
