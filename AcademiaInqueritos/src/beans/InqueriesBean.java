package beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Inqueries;

/**
 * Session Bean implementation class InqueriesBean
 */
@Stateless
public class InqueriesBean implements Serializable{

	private static final long serialVersionUID = -7676636099454676162L;


	@PersistenceContext (name="AcademiaEntities")
	private EntityManager em;
	
	
	Inqueries inquery = new Inqueries();
	
    /**
     * Default constructor. 
     */
    public InqueriesBean() {
        
    }

    private Boolean verifySomeNameAndSomeDate(Inqueries inquery){
    	this.inquery = em.find(Inqueries.class, inquery);
		
		if(this.inquery.getTitle().equals(inquery.getTitle())){
		
		Date dateEndA = inquery.getEndDate();
		Date dateStartA= inquery.getStartDate();
		Date dateEndB= this.inquery.getEndDate();
		Date dateStartB= this.inquery.getStartDate();
		
		if((dateEndB.compareTo(dateEndA)<=0 && dateEndB.compareTo(dateStartA)>0)){
			return false;
			
		}else if(dateStartB.compareTo(dateStartA)>=0 && dateStartB.compareTo(dateEndA)<0){
			return false;
			
		}else if(dateStartB.compareTo(dateStartA)<=0 && dateEndB.compareTo(dateEndA)>=0){
			return false;
			
		}else if(dateStartA.compareTo(dateStartB)<=0 && dateEndA.compareTo(dateEndB)>=0){
			return false;
			
		}else{
			return true;
		}
				
	
		}
		else{
			return true;
		}
		
				
    }
    
	public String registerInquerie(Inqueries inquery) {
		if(verifySomeNameAndSomeDate(inquery)){
			
			em.persist(inquery);
			return "success";
			
		}else{
			return "someNameAndCoincidenceDates";
		}
	}
    
}
