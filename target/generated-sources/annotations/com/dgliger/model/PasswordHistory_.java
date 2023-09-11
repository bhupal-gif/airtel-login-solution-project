package com.dgliger.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PasswordHistory.class)
public abstract class PasswordHistory_ extends com.dgliger.utility.AuditableEntity_ {

	public static volatile SingularAttribute<PasswordHistory, String> secondPassword;
	public static volatile SingularAttribute<PasswordHistory, String> firstPassword;
	public static volatile SingularAttribute<PasswordHistory, String> thirdPassword;
	public static volatile SingularAttribute<PasswordHistory, String> fourthPassword;
	public static volatile SingularAttribute<PasswordHistory, String> fifthPassword;
	public static volatile SingularAttribute<PasswordHistory, String> userId;

	public static final String SECOND_PASSWORD = "secondPassword";
	public static final String FIRST_PASSWORD = "firstPassword";
	public static final String THIRD_PASSWORD = "thirdPassword";
	public static final String FOURTH_PASSWORD = "fourthPassword";
	public static final String FIFTH_PASSWORD = "fifthPassword";
	public static final String USER_ID = "userId";

}

