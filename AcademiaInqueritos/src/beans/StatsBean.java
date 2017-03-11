package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Answers;
import entities.Inqueries;
import entities.Questions;
import entities.UserAnswers;

@ManagedBean
@RequestScoped
public class StatsBean implements Serializable {
	
	private static final long serialVersionUID = -8534995753434191562L;

	@PersistenceContext
	EntityManager em;

	private Inqueries inquery = new Inqueries();
	private ArrayList<Questions> questions = new ArrayList<Questions>();
	private ArrayList<ArrayList<Answers>> answers = new ArrayList<ArrayList<Answers>>();
	private ArrayList<ArrayList<UserAnswers>> userAnswers = new ArrayList<ArrayList<UserAnswers>>();
	
	public StatsBean() {
	}

	public Inqueries getInquery() {
		return inquery;
	}

	public void setInquery(Inqueries inquery) {
		this.inquery = inquery;
	}

	public ArrayList<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Questions> questions) {
		this.questions = questions;
	}

	public ArrayList<ArrayList<Answers>> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<ArrayList<Answers>> answers) {
		this.answers = answers;
	}

	public ArrayList<ArrayList<UserAnswers>> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(ArrayList<ArrayList<UserAnswers>> userAnswers) {
		this.userAnswers = userAnswers;
	}

	public String showStats(Inqueries inquery) {
		this.inquery = em.find(Inqueries.class, inquery.getInqueryId());
		
		if(this.inquery == null) {
			return "inqueryDoesNotExist";
		}
		else {
			// Get inquery questions from the Database
			setQuestions((ArrayList<Questions>)em.createNamedQuery("SELECT * FROM Questions WHERE INQUERY_INQUERYID = " + this.inquery.getInqueryId()).getResultList());
			
			if(getQuestions() == null) {
				return "inqueryHasNoQuestions";
			}
			else {
				for (Questions question: getQuestions()) {
					ArrayList<Answers> answer = (ArrayList<Answers>) em.createNamedQuery("SELECT * FROM Answer WHERE QUESTIONS_QUESTIONID = " + question.getQuestionId()).getResultList();
					
					for(Answers userAnswer : answer) {
						userAnswers.add((ArrayList<UserAnswers>) em.createNamedQuery("SELECT * FROM UserAnswers WHERE ANSWERID = " + userAnswer.getAnswerId()).getResultList());
					}
					
					this.answers.add(answer);
				}
				return "success";
			}
		}
	}
}
