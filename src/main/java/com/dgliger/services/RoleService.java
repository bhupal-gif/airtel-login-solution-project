package com.dgliger.services;

import com.dgliger.exceptions.BadRequestException;
import com.dgliger.exceptions.EntityNotFoundException;
import com.dgliger.mapper.RoleMapper;
import com.dgliger.model.Role;
import com.dgliger.model.request.RoleRequest;
import com.dgliger.model.request.UpdateRoleRequest;
import com.dgliger.model.response.RoleResponse;
import com.dgliger.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private static final String ROLE_NOT_FOUND = "Role_Not_Found";

    private final RoleRepository roleMasterRepository;

    public RoleService(
            RoleRepository roleMasterRepository) {
        this.roleMasterRepository = roleMasterRepository;
    }

    public RoleResponse createRole(RoleRequest roleRequest) {
        return RoleMapper.mapRoleToRoleResponse(
                roleMasterRepository.save(RoleMapper.mapRoleRequestToRole(roleRequest)));
    }

    public RoleResponse getRoleById(String id) {
        Role role =
                roleMasterRepository
                        .findByIdAndDeletedFlag(id, false)
                        .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
        return RoleMapper.mapRoleToRoleResponse(role);
    }

    public Page<RoleResponse> getAllRoles(Pageable pageable) {
        return roleMasterRepository.findAll(pageable).map(RoleMapper::mapRoleToRoleResponse);
    }

    public RoleResponse updateRoleById(UpdateRoleRequest updateRoleRequest) {
        roleMasterRepository.findById(updateRoleRequest.getId()).orElseThrow(() -> new BadRequestException(ROLE_NOT_FOUND));
        return RoleMapper.mapRoleToRoleResponse(roleMasterRepository.save(RoleMapper.mapUpdateRoleRequestToRole(updateRoleRequest)));
    }

    public void deleteRoleById(String id) {
        Role role =
                roleMasterRepository
                        .findByIdAndDeletedFlag(id, false)
                        .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
        role = role.toBuilder().deletedFlag(true).build();
        roleMasterRepository.save(role);
    }

}
