package entities;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;

/**
 * Entity implementation class for Entity: Inqueries
 *
 */
@Entity
@Table(name = "inqueries")
public class Inqueries implements Serializable {

	@Id
	@Column(updatable = false)
	@GeneratedValue
	private Integer inqueryId; 
	@OneToMany(targetEntity = entities.Users.class)
	@JoinColumn(referencedColumnName = "userName")
	private String userName; 
	private String title;
	@Basic
	@Temporal(DATE)
	private Date startDate; 
	@Basic
	@Temporal(DATE)
	private Date endDate;
	private static final long serialVersionUID = 1L;	
	public Inqueries() {
		super();
	} 
	   
	public Integer getInqueryId() {
 		return this.inqueryId;
	}

	public void setInqueryId(Integer inqueryId) {
		this.inqueryId = inqueryId;
	}
	   
	public String getUserName() {
 		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	   
	public String getTitle() {
 		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	   
	public Date getStartDate() {
 		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	   
	public Date getEndDate() {
 		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
   
}
