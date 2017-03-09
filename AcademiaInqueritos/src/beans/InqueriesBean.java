package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entities.Answers;
import entities.Inqueries;
import entities.Questions;

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

	/**
	 * Default constructor.
	 */
	public InqueriesBean() {

	}

	private Boolean verifySameNameAndSameDate(Inqueries inquery) {
		ArrayList<Inqueries> inqueries = (ArrayList<Inqueries>) em.createNamedQuery(inquery.getTitle(), Inqueries.class)
				.getResultList();

		if (inqueries.isEmpty()) {
			return false;
		} else {
			for (Inqueries inq : inqueries) {

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

	public String registerInqueryNext(Inqueries inquery) throws Exception {
		if (verifySameNameAndSameDate(inquery)) {
			return "sameNameAndCoincidenceDates";

		} else {
			utx.begin();
			em.persist(inquery);
			utx.commit();
			this.inquery = (Inqueries) em.createNamedQuery(
					"SELECT * FROM (SELECT * FROM Inqueries WHEN USER_USERNAME= '" + inquery.getUser().getUserName()
							+ "' AND TITLE= '" + inquery.getTitle() + "') AS I WHEN INQUERYID= MAX(INQUERYID) ");
			registerQuestion(this.question);

			return "success";
		}
	}

	public String registerInquery(Inqueries inquery) throws Exception {
		if (verifySameNameAndSameDate(inquery)) {
			return "sameNameAndCoincidenceDates";

		} else {
			utx.begin();
			em.persist(inquery);
			utx.commit();
			this.inquery = (Inqueries) em.createNamedQuery(
					"SELECT * FROM (SELECT * FROM Inqueries WHEN USER_USERNAME= '" + inquery.getUser().getUserName()
							+ "' AND TITLE= '" + inquery.getTitle() + "') AS I WHEN INQUERYID= MAX(INQUERYID) ");
			registerQuestion(this.question);

			return "success";
		}
	}

	public String editInquery(Inqueries inquery) {
		this.inquery = em.find(Inqueries.class, inquery.getInqueryId());
		if (this.inquery == null) {
			return "inqueryNotExist";
		} else {

			this.questionArray = (ArrayList<Questions>) em.createNamedQuery("SELECT * FROM Questions WHEN INQUERY_INQUERYID= '" + inquery.getInqueryId() + "'").getResultList();

			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM Answers WHEN QUESTIONS_QUESTIONSID= ");

			for (Questions ques : questionArray) {
				if (questionArray.iterator().hasNext()) {
					query.append(ques.getQuestionId());
				} else {
					query.append(ques.getQuestionId() + "OR QUESTIONS_QUESTIONSID= ");
				}

			}

			this.answers = (ArrayList<Answers>) em.createNamedQuery(query.toString()).getResultList();

			return "success";
		}
	}

	public Inqueries getInquery() {
		return inquery;
	}

	public void setInquery(Inqueries inquery) {
		this.inquery = inquery;
	}

	// QUESTIONS

	public String registerQuestion(Questions question) throws Exception {
		question.setInquery(inquery);
		utx.begin();
		em.persist(question);

		this.question = (Questions) em
				.createNamedQuery("SELECT * FROM (SELECT * FROM QUESTIONS WHEN INQUERYID= '" + inquery.getInqueryId());

		registerAnswers(answers);
		utx.commit();
		return "success";

	}

	public String registerQuestionNext(Questions question) throws Exception {
		question.setQuestionId(inquery.getInqueryId());
		utx.begin();
		em.persist(question);
		this.question = (Questions) em
				.createNamedQuery("SELECT * FROM QUESTIONS WHEN INQUERYID= " + inquery.getInqueryId());
		registerAnswers(answers);
		utx.commit();
		return "success";

	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	// ANSWERS

	public String registerAnswers(ArrayList<Answers> answers) {
		int numberQuestion = question.getQuestionId();

		for (int i = 0; i > answers.size(); i++) {
			answers.get(i).setAnswerId(numberQuestion);
			em.persist(answers.get(i));

		}
		return "success";

	}

	public ArrayList<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answers> answers) {
		this.answers = answers;
	}

}
