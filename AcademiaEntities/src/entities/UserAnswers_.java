package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-07T17:37:59.304+0000")
@StaticMetamodel(UserAnswers.class)
public class UserAnswers_ {
	public static volatile SingularAttribute<UserAnswers, UserAnswersPK> id;
	public static volatile SingularAttribute<UserAnswers, Users> user;
	public static volatile SingularAttribute<UserAnswers, Answers> answer;
}