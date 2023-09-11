package com.dgliger.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {
    @NotBlank
    @Size(max = 20)
    private String id;
    @NotBlank
    @Size(max = 20)
    private String name;
}