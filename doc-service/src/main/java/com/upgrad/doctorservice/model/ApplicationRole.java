package com.upgrad.doctorservice.model;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {
    USER(Sets.newHashSet(ApplicationPermission.READ, ApplicationPermission.WRITE)),
    DOCTOR(Sets.newHashSet(ApplicationPermission.READ, ApplicationPermission.WRITE)),
    ADMIN(Sets.newHashSet(ApplicationPermission.READ, ApplicationPermission.WRITE));

    private Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions(){
        return this.getPermissions();
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> authorities = permissions.stream().map(
                        p-> new SimpleGrantedAuthority(p.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
