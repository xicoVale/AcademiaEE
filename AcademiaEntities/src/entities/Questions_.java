package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-04T21:24:50.424+0000")
@StaticMetamodel(Questions.class)
public class Questions_ {
	public static volatile SingularAttribute<Questions, Integer> questionId;
	public static volatile CollectionAttribute<Questions, Inqueries> inqueryId;
	public static volatile SingularAttribute<Questions, String> questionText;
}
