package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entities.Answers;
import entities.Inqueries;
import entities.Questions;
import entities.Users;

/**
 * Session Bean implementation class InqueriesBean
 */
@ManagedBean
@SessionScoped
public class InqueriesBean implements Serializable {

	private static final long serialVersionUID = -7676636099454676162L;

	@PersistenceContext
	private EntityManager em;
	@Resource
	private UserTransaction utx;
	
	private Inqueries inquery = new Inqueries();
	private Questions question = new Questions();
	private ArrayList<Answers> answers = new ArrayList<Answers>();
	private ArrayList<Questions> questionArray = new ArrayList<Questions>();
	private ArrayList<Inqueries> inqueries = new ArrayList<Inqueries>();
	
	
	/**
	 * Default constructor.
	 */
	public InqueriesBean() {
		for(int i = 0; i < 4; i++) {
			answers.add(new Answers());
		}
	}
	
	/**
	 * Searches the database for another inquiry with the same title and that is active at the same time.
	 * 
	 * @param inquery
	 * @return - false if no other inquiries exist or true otherwise
	 */
	private Boolean verifySameNameAndSameDate(Inqueries inquery) {
		List<Object[]> inqueries = em.createNativeQuery("SELECT * FROM Inqueries WHERE TITLE LIKE '" + inquery.getTitle() + "'").getResultList();
		if (inqueries.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < inqueries.size(); i++) {
				Inqueries inq = Inqueries.parseInquery(inqueries.get(i), em);
				
				if (this.inquery.getTitle().equals(inq.getTitle())) {

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
					}

				} else {
					continue;
				}
			}
			return false;
		}
	}

	/**
	 * Starts the process of adding an inquiry to the database
	 * 
	 * @param inquery
	 * @return
	 * @throws Exception
	 */
	public String registerInqueryNext(Inqueries inquery) throws Exception {
		if (verifySameNameAndSameDate(inquery)) {
			return "sameNameAndCoincidenceDates";

		} else {
			utx.begin();
			em.persist(inquery);
			em.flush();
			
			registerQuestion(this.question);

			return "success";
		}
	}

	/** Starts the process of adding an inquiry to the database
	 * 
	 * @param inquery
	 * @return
	 * @throws Exception
	 */
	public String registerInquery(Inqueries inquery) throws Exception {
		if (verifySameNameAndSameDate(inquery)) {
			return "sameNameAndCoincidenceDates";

		} else {
			utx.begin();
			em.persist(inquery);
			em.flush();
			registerQuestion(this.question);

			return "success";
		}
	}

	public String editInquery(Inqueries inquery) {
		this.inquery = em.find(Inqueries.class, inquery.getInqueryId());
		if (this.inquery == null) {
			return "inqueryNotExist";
		} else {
			
			List<Object[]> questions = em.createNativeQuery("SELECT * FROM Questions WHERE INQUERY_INQUERYID= '" + inquery.getInqueryId() + "'").getResultList();
			
			for(Object[] question: questions) {
				questionArray.add(Questions.parseQuestion(question, em));
			}			

			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM Answers WHERE QUESTIONS_QUESTIONSID = ");

			for (Questions ques : questionArray) {
				if (questionArray.iterator().hasNext()) {
					query.append(ques.getQuestionId());
				} else {
					query.append(ques.getQuestionId() + "OR QUESTIONS_QUESTIONSID = ");
				}

			}

			List<Object[]> answers = em.createNativeQuery(query.toString()).getResultList();
			for(Object[] answer: answers) {
				this.answers.add(Answers.parseAnswer(answer, em));
			}

			return "success";
		}
	}

	public Inqueries getInquery() {
		return this.inquery;
	}

	public void setInquery(Inqueries inquery) {
		this.inquery = inquery;
	}
	
	public ArrayList<Inqueries> getInqueries() {
		return this.inqueries;
	}

	public void setInqueries(ArrayList<Inqueries> inqueries) {
		this.inqueries = inqueries;
	}

	// QUESTIONS
	/**
	 * Adds a question to the database
	 * 
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public String registerQuestion(Questions question) throws Exception {
		question.setInquery(this.inquery);
		em.persist(question);
		em.flush();	
		registerAnswers(this.answers);
		return "success";

	}
	/**
	 * Adds a question to the database
	 * 
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public String registerQuestionNext(Questions question) throws Exception {
		question.setQuestionId(this.inquery.getInqueryId());;
		em.persist(question);
		em.flush();
		registerAnswers(this.answers);
		return "success";
	}
	
	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	// ANSWERS
	/**
	 * Adds a question's answers to the database
	 * 
	 * @param answers
	 * @return
	 * @throws Exception
	 */
	public String registerAnswers(ArrayList<Answers> answers) throws Exception {
		for(Answers answer: answers) {
			answer.setQuestions(question);
			em.persist(answer);
		}
		utx.commit();
		
		return "success";
	}

	public ArrayList<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answers> answers) {
		this.answers = answers;
	}

}
