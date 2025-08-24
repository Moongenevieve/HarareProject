package com.example.HarareProject.Entity;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.TIK_READ,Permissions.TIK_DELETE,Permissions.TIK_WRITE)),
    USER(Set.of(Permissions.TIK_READ));


    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

}
