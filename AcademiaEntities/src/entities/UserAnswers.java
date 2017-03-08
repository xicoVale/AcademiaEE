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
}