package com.dgliger.model;


import com.dgliger.utility.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
public class PasswordHistory extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = 5494512865824553981L;
    @Id
    private String userId;
    private String firstPassword;
    private String secondPassword;
    private String thirdPassword;
    private String fourthPassword;
    private String fifthPassword;
}
