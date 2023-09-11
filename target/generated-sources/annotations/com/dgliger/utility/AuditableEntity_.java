package com.dgliger.utility;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditableEntity.class)
public abstract class AuditableEntity_ {

	public static volatile SingularAttribute<AuditableEntity, LocalDateTime> createdDate;
	public static volatile SingularAttribute<AuditableEntity, String> createdBy;
	public static volatile SingularAttribute<AuditableEntity, LocalDateTime> lastModifiedDate;
	public static volatile SingularAttribute<AuditableEntity, String> lastModifiedBy;

	public static final String CREATED_DATE = "createdDate";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

}

