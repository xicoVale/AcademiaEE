package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Answers;
import entities.Inqueries;
import entities.Questions;

/**
 * Session Bean implementation class InqueriesBean
 */
@ManagedBean
@SessionScoped
public class InqueriesBean implements Serializable{

	private static final long serialVersionUID = -7676636099454676162L;


	@PersistenceContext (name="AcademiaEntities")
	private EntityManager em;
	
	
	private Inqueries inquery = new Inqueries();
	private Questions question = new Questions();
	private ArrayList<Answers> answers= new ArrayList<Answers>();
	
    /**
     * Default constructor. 
     */
    public InqueriesBean() {
        
    }

	private Boolean verifySomeNameAndSomeDate(Inqueries inquery) {
		this.inquery = em.find(Inqueries.class, inquery);

		if (this.inquery.getTitle().equals(inquery.getTitle())) {

			Date dateEndA = inquery.getEndDate();
			Date dateStartA = inquery.getStartDate();
			Date dateEndB = this.inquery.getEndDate();
			Date dateStartB = this.inquery.getStartDate();

			if ((dateEndB.compareTo(dateEndA) <= 0 && dateEndB.compareTo(dateStartA) > 0)) {
				return true;

			} else if (dateStartB.compareTo(dateStartA) >= 0 && dateStartB.compareTo(dateEndA) < 0) {
				return true;

			} else if (dateStartB.compareTo(dateStartA) <= 0 && dateEndB.compareTo(dateEndA) >= 0) {
				return true;

			} else if (dateStartA.compareTo(dateStartB) <= 0 && dateEndA.compareTo(dateEndB) >= 0) {
				return true;

			} else {
				return false;
			}

		} else {
			return false;
		}

	}
    
	public String registerInquerie(Inqueries inquery) {
		if(verifySomeNameAndSomeDate(inquery)){
			
			return "someNameAndCoincidenceDates";
			
			
		}else{
			em.persist(inquery);
			return "success";
		}
	}
	
		
	public Inqueries getInquery() {
		return inquery;
	}

	public void setInquery(Inqueries inquery) {
		this.inquery = inquery;
	}

//	QUESTIONS
	
	public String registerQuestion(Questions question){
		em.persist(question);
		return "success";
		
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}
	
// ANSWERS
	
	
	
	
	
    
}
