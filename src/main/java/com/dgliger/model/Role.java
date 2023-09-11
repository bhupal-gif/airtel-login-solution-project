package com.dgliger.model;

import com.dgliger.utility.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Role extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = 5494512865824553981L;
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    private boolean deletedFlag;
}