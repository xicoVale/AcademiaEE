package entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: Answers
 *
 */
@Entity
@Table(name = "answers")
public class Answers implements Serializable {

	 
	@Id
	@Column(updatable = false, nullable = false)
	@GeneratedValue
	private Integer answerId; 
	@OneToMany(targetEntity = entities.Questions.class)
	private Integer questionId;
	@Basic(optional = false)
	private String answerText;
	private static final long serialVersionUID = 1L;	
	public Answers() {
		super();
	} 
	   
	public Integer getAnswerId() {
 		return this.answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	   
	public Integer getQuestionId() {
 		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	   
	public String getAnswerText() {
 		return this.answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
   
}
