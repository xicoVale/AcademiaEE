package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

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
	@OneToMany(targetEntity = entities.Inqueries.class, mappedBy = "inqueryId")
	private Integer inqueryId; 
	@Basic(optional = false)
	private String questionText;
	@ManyToOne
	@JoinColumn(referencedColumnName = "answers", table = "answerId", nullable = false)
	private Integer answerId;
	private static final long serialVersionUID = 1L;	
	public Questions() {
		super();
	} 
	   
	public Integer getQuestionId() {
 		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	   
	public Integer getInqueryId() {
 		return this.inqueryId;
	}

	public void setInqueryId(Integer inqueryId) {
		this.inqueryId = inqueryId;
	}
	   
	public String getQuestionText() {
 		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
   
}
