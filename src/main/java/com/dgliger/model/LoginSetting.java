package com.dgliger.model;

import com.dgliger.utility.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class LoginSetting extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 5494512865824553981L;
    @Id
    private String id;
    private String parameterName;
    private String parameterValue;
    private String description;
}
