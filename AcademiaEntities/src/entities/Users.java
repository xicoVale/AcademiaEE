package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {

	@Id
	@Column(nullable = false, insertable = true)
	private String userName; 
	@Column(insertable = true, nullable = false)
	private String password; 
	@Column(nullable = false, insertable = true, unique = true)
	private String salt;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private UserRoles userRole;
	

	public Users() {
		super();
	} 
	   
	public String getUserName() {
 		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	   
	public String getPassword() {
 		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	   
	public String getSalt() {
 		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	   
	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
   
}
