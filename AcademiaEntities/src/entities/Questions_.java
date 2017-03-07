package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-07T17:04:47.081+0000")
@StaticMetamodel(Questions.class)
public class Questions_ {
	public static volatile SingularAttribute<Questions, Integer> questionId;
	public static volatile SingularAttribute<Questions, String> questionText;
	public static volatile SingularAttribute<Questions, Inqueries> inquery;
}
