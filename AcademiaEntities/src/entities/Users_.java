package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-07T17:03:22.783+0000")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, String> userName;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, String> salt;
	public static volatile SingularAttribute<Users, UserRoles> userRole;
}
