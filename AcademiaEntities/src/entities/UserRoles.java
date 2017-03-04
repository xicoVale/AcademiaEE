package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * Entity implementation class for Entity: UserRoles
 *
 */
@Entity
@Table(name = "userRoles")
public class UserRoles implements Serializable {

	@Id
	private Integer roleId; 
	@Column(nullable = false)
	private String role;
	@ManyToOne
	@JoinColumn(referencedColumnName = "userName", table = "userName", nullable = false)
	private String userName;
	private static final long serialVersionUID = 1L;	
	public UserRoles() {
		super();
	} 
	   
	public Integer getRoleId() {
 		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	   
	public String getRole() {
 		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
   
}
