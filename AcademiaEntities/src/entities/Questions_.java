package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-07T15:24:56.766+0000")
@StaticMetamodel(Questions.class)
public class Questions_ {
	public static volatile SingularAttribute<Questions, Integer> questionId;
	public static volatile SingularAttribute<Questions, Integer> inqueryId;
	public static volatile SingularAttribute<Questions, String> questionText;
	public static volatile SingularAttribute<Questions, Inqueries> inquery;
	public static volatile SingularAttribute<Questions, Inqueries> inquery;
}
