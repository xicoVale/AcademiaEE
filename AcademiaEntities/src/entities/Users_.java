package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-04T21:24:50.464+0000")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, String> userName;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, String> salt;
	public static volatile CollectionAttribute<Users, UserRoles> roleId;
}
