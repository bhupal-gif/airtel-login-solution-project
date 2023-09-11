package com.dgliger.model.request;

import com.dgliger.model.ERole;
import com.dgliger.utility.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    @NotNull
    @EnumValidator(enumClass = ERole.class)
    @Enumerated(EnumType.ORDINAL)
    private String name;
}