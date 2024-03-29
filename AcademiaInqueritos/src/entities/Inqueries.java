package entities;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.persistence.Temporal;

/**
 * Entity implementation class for Entity: Inqueries
 *
 */
@Entity
@Table(name="Inqueries")
@TableGenerator(name="seq", table="SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=100)
public class Inqueries implements Serializable {

	
	@Id
	@Column(updatable = false)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq")
	private Integer inqueryId;
	@Basic(optional = false)
	private String title;
	@Basic
	@Temporal(DATE)
	private Date startDate; 
	@Basic
	@Temporal(DATE)
	private Date endDate;
	@ManyToOne
	private Users user;
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
	
	public void setStartDate(String startDate) throws ParseException {
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		this.startDate = format.parse(startDate);
	}
	   
	public Date getEndDate() {
 		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String endDate) throws ParseException {
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		this.endDate = format.parse(endDate);
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inqueryId == null) ? 0 : inqueryId.hashCode());
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
		Inqueries other = (Inqueries) obj;
		if (inqueryId == null) {
			if (other.inqueryId != null)
				return false;
		} else if (!inqueryId.equals(other.inqueryId))
			return false;
		return true;
	}

	/**
	 * Creates an Inquery from an object array
	 * 
	 * @param object
	 * @return
	 */
   public static Inqueries parseInquery(Object[] object, EntityManager em){
	   Inqueries inquery = new Inqueries();
	   
	   inquery.setInqueryId((Integer)object[0]);
	   inquery.setEndDate((Date) object[1]);
	   inquery.setStartDate((Date) object[2]);
	   inquery.setTitle((String) object[3]);
	   inquery.setUser(em.find(Users.class, (String)object[4]));
	   
	   return inquery;
   }
}
