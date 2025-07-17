package com.dgliger.mapper;

import com.dgliger.model.Role;
import com.dgliger.model.request.RoleRequest;
import com.dgliger.model.request.UpdateRoleRequest;
import com.dgliger.model.response.RoleResponse;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RoleMapper {

    public Role mapRoleRequestToRole(RoleRequest roleRequest) {
        return Role.builder()
                .id(UUID.randomUUID().toString())
                .name(roleRequest.getName())
                .deletedFlag(false)
                .build();
    }

    public RoleResponse mapRoleToRoleResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .deletedFlag(role.isDeletedFlag())
                .createdBy(role.getCreatedBy())
                .createdDate(role.getCreatedDate())
                .lastModifiedBy(role.getLastModifiedBy())
                .lastModifiedDate(role.getLastModifiedDate())
                .build();
    }

    public Role mapUpdateRoleRequestToRole(UpdateRoleRequest updateRoleRequest) {
        return Role.builder()
                .id(updateRoleRequest.getId())
                .name(updateRoleRequest.getName())
                .build();
    }
}
