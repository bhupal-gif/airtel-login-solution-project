package com.dgliger.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoginSetting.class)
public abstract class LoginSetting_ extends com.dgliger.utility.AuditableEntity_ {

	public static volatile SingularAttribute<LoginSetting, String> description;
	public static volatile SingularAttribute<LoginSetting, String> id;
	public static volatile SingularAttribute<LoginSetting, String> parameterName;
	public static volatile SingularAttribute<LoginSetting, String> parameterValue;

	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PARAMETER_NAME = "parameterName";
	public static final String PARAMETER_VALUE = "parameterValue";

}

