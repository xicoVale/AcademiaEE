package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: UserAnswers
 *
 */
@Entity
@Table(name = "userAnswers")
@IdClass(UserAnswersPK.class)
public class UserAnswers implements Serializable {

	@Id
	private Users userName;
	@Id
	private Answers answerId;
	
	private static final long serialVersionUID = 1L;	
	public UserAnswers() {
		super();
	}
	
	public Users getUserName() {
		return userName;
	}
	public void setUserName(Users userName) {
		this.userName = userName;
	}
	
	public Answers getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Answers answerId) {
		this.answerId = answerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerId == null) ? 0 : answerId.hashCode());
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
		UserAnswers other = (UserAnswers) obj;
		if (answerId == null) {
			if (other.answerId != null)
				return false;
		} else if (!answerId.equals(other.answerId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}   
}