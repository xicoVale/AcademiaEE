package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-06T14:41:48.652+0000")
@StaticMetamodel(Answers.class)
public class Answers_ {
	public static volatile SingularAttribute<Answers, Integer> answerId;
	public static volatile CollectionAttribute<Answers, Questions> questionId;
	public static volatile SingularAttribute<Answers, String> answerText;
}
