package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Role;

public interface RoleService {
    Role findOrCreate(String name);
}
