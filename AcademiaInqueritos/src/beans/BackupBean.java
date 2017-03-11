package beans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.ejb.Timeout;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Answers;
import entities.Inqueries;
import entities.Questions;
import entities.UserAnswers;
import entities.UserRoles;
import entities.Users;

@ManagedBean
@RequestScoped
public class BackupBean implements Serializable {

	private static final long serialVersionUID = -7833608034425721917L;
	@PersistenceContext
	private EntityManager em;
	
	private class Backup implements Serializable{
		private static final long serialVersionUID = -4351092081590712889L;
		private ArrayList<UserRoles> userRoles = new ArrayList<UserRoles>();
		private ArrayList<Users> users = new ArrayList<Users>();
		private ArrayList<Inqueries> inqueries = new ArrayList<Inqueries>();
		private ArrayList<Questions> questions = new ArrayList<Questions>();
		private ArrayList<Answers> answers = new ArrayList<Answers>();
		private ArrayList<UserAnswers> userAnswers = new ArrayList<UserAnswers>();
		
		protected Backup() {}

		protected ArrayList<UserRoles> getUserRoles() {
			return userRoles;
		}

		protected void setUserRoles(ArrayList<UserRoles> userRoles) {
			this.userRoles = userRoles;
		}

		protected ArrayList<Users> getUsers() {
			return users;
		}

		protected void setUsers(ArrayList<Users> users) {
			this.users = users;
		}

		protected ArrayList<Inqueries> getInqueries() {
			return inqueries;
		}

		protected void setInqueries(ArrayList<Inqueries> inqueries) {
			this.inqueries = inqueries;
		}

		protected ArrayList<Questions> getQuestions() {
			return questions;
		}

		protected void setQuestions(ArrayList<Questions> questions) {
			this.questions = questions;
		}

		protected ArrayList<Answers> getAnswers() {
			return answers;
		}

		protected void setAnswers(ArrayList<Answers> answers) {
			this.answers = answers;
		}

		protected ArrayList<UserAnswers> getUserAnswers() {
			return userAnswers;
		}

		protected void setUserAnswers(ArrayList<UserAnswers> userAnswers) {
			this.userAnswers = userAnswers;
		}
		
		protected void setData(EntityManager em) {
			setUserRoles((ArrayList<UserRoles>) em.createNamedQuery("SELECT * FROM UserRoles").getResultList());
			setUsers((ArrayList<Users>) em.createNamedQuery("SELECT * FROM Users").getResultList());
			setInqueries((ArrayList<Inqueries>) em.createNamedQuery("SELECT * FROM Inqueries").getResultList());
			setQuestions((ArrayList<Questions>) em.createNamedQuery("SELECT * FROM Questions").getResultList());
			setAnswers((ArrayList<Answers>) em.createNamedQuery("SELECT * FROM Answers").getResultList());
			setUserAnswers((ArrayList<UserAnswers>) em.createNamedQuery("SELECT * FROM UserAnswers").getResultList());
		}
	}
	
	public BackupBean() {}
	
	/**
	 * Method that stores the contents of the database and saves it to a file
	 * 
	 * @return - A String "success" when the backup is completed
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Timeout
	public String backup() throws FileNotFoundException, IOException {
		Backup backup = new Backup();
		
		backup.setData(em);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C://dump.bin"));
		oos.writeObject(backup);
		oos.close();
		return "success";
	}
	
	public String scheduleBackup(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");
		Date date = formatter.parse(date);
	}
}