package entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Questions
 *
 */
@Entity
@Table(name = "questions")
public class Questions implements Serializable {

	 
	@Id
	@GeneratedValue
	private Integer questionId;
	@Basic(optional = false)
	private String questionText;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Inqueries inquery;
	
	public Questions() {
		super();
	} 
	   
	public Integer getQuestionId() {
 		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	   
	   
	public String getQuestionText() {
 		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public Inqueries getInquery() {
		return inquery;
	}

	public void setInquery(Inqueries inquery) {
		this.inquery = inquery;
	}
	
   
}