package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.AttributeOverrides;

/**
 * Entity implementation class for Entity: UserAnswers
 *
 */
@Entity
@Table(name = "userAnswers")
public class UserAnswers implements Serializable {

	@EmbeddedId
	private UserAnswersPK id;
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
}