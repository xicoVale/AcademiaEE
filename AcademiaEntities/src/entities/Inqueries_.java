package entities;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-04T21:24:50.410+0000")
@StaticMetamodel(Inqueries.class)
public class Inqueries_ {
	public static volatile SingularAttribute<Inqueries, Integer> inqueryId;
	public static volatile CollectionAttribute<Inqueries, Users> userName;
	public static volatile SingularAttribute<Inqueries, String> title;
	public static volatile SingularAttribute<Inqueries, LocalDateTime> startDate;
	public static volatile SingularAttribute<Inqueries, LocalDateTime> endDate;
}
