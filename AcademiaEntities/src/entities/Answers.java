package entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Basic(optional = false)
	private String answerText;
	@ManyToOne
    private Questions questions;
	
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
	   
	public String getAnswerText() {
 		return this.answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	
	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	
   
}
