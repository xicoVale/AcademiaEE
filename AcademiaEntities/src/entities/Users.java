package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

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
	@Column(nullable = false, table = "users", insertable = true, unique = true)
	private String salt;
	@Column(nullable = false)
	@OneToMany(targetEntity = entities.UserRoles.class, mappedBy = "roleId")
	private Integer roleId;
	@ManyToOne
	@JoinColumn(referencedColumnName = "inqueryId", table = "inqueries", nullable = false)
	private Integer inqueryId;
	private static final long serialVersionUID = 1L;	
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
	   
	public Integer getRoleId() {
 		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
   
}
