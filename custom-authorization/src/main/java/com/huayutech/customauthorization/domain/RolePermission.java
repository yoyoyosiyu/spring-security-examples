package com.huayutech.customauthorization.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RolePermission {

    @EmbeddedId
    protected RolePermissionId id;

    @ManyToOne
    @MapsId("permissionId")
    protected Permission permission;

    @ManyToOne
    @MapsId("roleId")
    protected Role role;


    @Embeddable
    public static class RolePermissionId implements Serializable {

        @Column(length=36)
        public String roleId;

        @Column(length = 36)
        public String permissionId;
    }
}
