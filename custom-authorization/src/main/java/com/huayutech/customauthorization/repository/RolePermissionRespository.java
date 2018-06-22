package com.huayutech.customauthorization.repository;

import com.huayutech.customauthorization.domain.Permission;
import com.huayutech.customauthorization.domain.Role;
import com.huayutech.customauthorization.domain.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RolePermissionRespository extends JpaRepository<RolePermission, RolePermission.RolePermissionId> {

    public Collection<RolePermission> findAllByRoleInAndPermissionIn(Collection<Role> roles, Collection<Permission> permissions);


}
