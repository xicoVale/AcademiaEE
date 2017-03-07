package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-06T14:41:48.671+0000")
@StaticMetamodel(Inqueries.class)
public class Inqueries_ {
	public static volatile SingularAttribute<Inqueries, Integer> inqueryId;
	public static volatile CollectionAttribute<Inqueries, Users> userName;
	public static volatile SingularAttribute<Inqueries, String> title;
	public static volatile SingularAttribute<Inqueries, Date> startDate;
	public static volatile SingularAttribute<Inqueries, Date> endDate;
}
