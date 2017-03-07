package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: UserAnswers
 *
 */
@Entity
@Table(name = "userAnswers")
public class UserAnswers implements Serializable {

	@EmbeddedId
	private UserAnswersPK id;
	@ManyToOne
	private Users user;
	@ManyToOne
	private Answers answer;
	
	private static final long serialVersionUID = 1L;	
	public UserAnswers() {
		super();
	}
	public UserAnswersPK getId() {
		return id;
	}
	public void setId(UserAnswersPK id) {
		this.id = id;
	}
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public Answers getAnswer() {
		return answer;
	}
	public void setAnswer(Answers answer) {
		this.answer = answer;
	}   
}