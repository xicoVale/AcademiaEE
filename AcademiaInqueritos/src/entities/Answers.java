package entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Entity implementation class for Entity: Answers
 *
 */
@Entity
@Table(name="Answers")
@TableGenerator(name="seq", table="SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=100)
public class Answers implements Serializable {

	 
	@Id
	@Column(updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerId == null) ? 0 : answerId.hashCode());
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
		Answers other = (Answers) obj;
		if (answerId == null) {
			if (other.answerId != null)
				return false;
		} else if (!answerId.equals(other.answerId))
			return false;
		return true;
	}
	/**
	 * Parses an answers obtained from the database
	 * @param object
	 * @param em
	 * @return
	 */
   public static Answers parseAnswer(Object[] object, EntityManager em) {
	   Answers answer = new Answers();
	   
	   answer.setAnswerId((Integer) object[0]);
	   answer.setAnswerText((String) object[1]);
	   answer.setQuestions(em.find(Questions.class, (Integer) object[2]));
	   
	   return answer;
   }
}
