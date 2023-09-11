package com.dgliger.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends com.dgliger.utility.AuditableEntity_ {

	public static volatile SingularAttribute<Role, Boolean> deletedFlag;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, String> id;

	public static final String DELETED_FLAG = "deletedFlag";
	public static final String NAME = "name";
	public static final String ID = "id";

}

