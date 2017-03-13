package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entities.UserRoles;
import entities.Users;

@ManagedBean
@RequestScoped
public class PromoteBean implements Serializable{

	private static final long serialVersionUID = -6371307947553281102L;
	
	private Users user;
	
	@PersistenceContext
	EntityManager em;
	
	@Resource
	UserTransaction utx;

	public PromoteBean() {
		
	}
	
	@PostConstruct
	public void init(){
		user = new Users();
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	/**
	 * Gives superUser permitions to the given user
	 * 
	 * 
	 * @param user - The user to be promoted
	 * @return
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	public String promoteUser(Users user) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		this.user = em.find(Users.class, user.getUserName());
		
		if(this.user == null) {
			this.user= new Users();
			return "userNotExists";
		}
		
		else {
			utx.begin();
			UserRoles role= em.find(UserRoles.class,2);
			this.user.setUserRole(role);
			em.merge(this.user);
//			em.createNativeQuery("UPDATE Users SET USERROLE_ROLEID = 2 WHERE USERNAME = '" + this.user.getUserName()+"'");
			utx.commit();
			
			return "success";
		}
	}
}
