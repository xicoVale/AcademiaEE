package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	/**
	 * Retrieves the inquiries questions and answers from the database and processes them.

	 * @param inquery
	 * @return
	 */
	public String showStats(Inqueries inquery) {
		this.inquery = em.find(Inqueries.class, inquery.getInqueryId());
		
		if(this.inquery == null) {
			return "inqueryDoesNotExist";
		}
		else {
			// Get inquiry questions from the Database
			List<Object[]> quests = em.createNativeQuery("SELECT * FROM Questions WHERE INQUERY_INQUERYID = " + getInquery().getInqueryId()).getResultList();
			
			for(Object[] object: quests) {
				questions.add(Questions.parseQuestion(object, em));
			}
			
			if(getQuestions().isEmpty()) {
				return "inqueryHasNoQuestions";
			}
			
			else {
				processAnswers();
			}
			return "success";
		}
	}
	/**
	 * Retrieves the question's answers from the database and processes them
	 */
	@SuppressWarnings("unchecked")
	private void processAnswers() {
		for (Questions question: getQuestions()) {
			List<Object[]> answersDB = em.createNativeQuery("SELECT * FROM Answers WHERE QUESTIONS_QUESTIONID = " + question.getQuestionId()).getResultList();
			
			for(Object[] object : answersDB) {
				ArrayList<Answers> answer = new ArrayList<Answers>();
				ArrayList<UserAnswers> userAnswer = new ArrayList<UserAnswers>();
				Answers ans = Answers.parseAnswer(object, em);
				answer.add(ans);
				
				List<Object[]> userA = em.createNativeQuery("SELECT * FROM UserAnswers WHERE ANSWERID = " + ans.getAnswerId()).getResultList();
				
				for(Object[] obj : userA) {
					userAnswer.add(UserAnswers.parseUserAnser(obj, em));
				}
				
				answers.add(answer);
				userAnswers.add(userAnswer);
			}
		}
	}
}