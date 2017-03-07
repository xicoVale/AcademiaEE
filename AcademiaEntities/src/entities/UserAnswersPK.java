package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: UserAnswers
 *
 */
@Embeddable
public class UserAnswersPK  implements Serializable {   
   
	private String userName;
	private Integer answerId;
	
	private static final long serialVersionUID = 1L;

	public UserAnswersPK() {}


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public Integer getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof UserAnswersPK)) {
			return false;
		}
		UserAnswersPK other = (UserAnswersPK) o;
		return true
			&& (getUserName() == null ? other.getUserName() == null : getUserName().equals(other.getUserName()))
			&& (getAnswerId() == null ? other.getAnswerId() == null : getAnswerId().equals(other.getAnswerId()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getUserName() == null ? 0 : getUserName().hashCode());
		result = prime * result + (getAnswerId() == null ? 0 : getAnswerId().hashCode());
		return result;
	}
   
   
}
