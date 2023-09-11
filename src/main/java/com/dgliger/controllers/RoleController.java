package com.dgliger.controllers;

import com.dgliger.model.request.RoleRequest;
import com.dgliger.model.request.UpdateRoleRequest;
import com.dgliger.model.response.RoleResponse;
import com.dgliger.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
@Api(value = "Role Master Controller")
@SecurityRequirement(name = "ALS")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleResponse createRole(@Valid @RequestBody RoleRequest roleRequest) {
        return roleService.createRole(roleRequest);
    }

    @GetMapping
    public Page<RoleResponse> getAllRoles(Pageable pageable) {
        return roleService.getAllRoles(pageable);
    }

    @GetMapping("/id/{id}")
    public RoleResponse getRoleById(@PathVariable("id") String id) {
        return roleService.getRoleById(id);
    }

    @PutMapping
    public RoleResponse updateRoleById(@Valid @RequestBody UpdateRoleRequest roleRequest) {
        return roleService.updateRoleById(roleRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable("id") String id) {
        roleService.deleteRoleById(id);
    }
}
