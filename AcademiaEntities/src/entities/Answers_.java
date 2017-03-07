package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-07T17:04:14.328+0000")
@StaticMetamodel(Answers.class)
public class Answers_ {
	public static volatile SingularAttribute<Answers, Integer> answerId;
	public static volatile SingularAttribute<Answers, String> answerText;
	public static volatile SingularAttribute<Answers, Questions> questions;
}
