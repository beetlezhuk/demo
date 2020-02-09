package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static com.example.demo.domain.ApplicationRolePermissions.STUDENTS_READ;
import static com.example.demo.domain.ApplicationRolePermissions.STUDENTS_WRITE;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toSet;

public enum ApplicationUserRoles {

    STUDENT(newHashSet(STUDENTS_READ)),
    ADMIN(newHashSet(STUDENTS_READ, STUDENTS_WRITE));

    private final Set<ApplicationRolePermissions> permissions;

    ApplicationUserRoles(final Set<ApplicationRolePermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationRolePermissions> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = getPermissions()
                .stream()
                .map(rolePermissions -> new SimpleGrantedAuthority(rolePermissions.getPermission()))
                .collect(toSet());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return grantedAuthorities;
    }
}
