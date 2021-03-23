package com.andyadc.bms.auth.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table("auth_user_role")
public class AuthUserRole {

    private Long id;
    private Long userId;
    private Long roleId;

    public AuthUserRole() {
    }

    public AuthUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
