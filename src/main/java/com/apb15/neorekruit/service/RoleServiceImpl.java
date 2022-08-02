package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Role;
import com.apb15.neorekruit.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findOrCreate(String name) {
        var checkPengguna = roleRepository.findByName(name);
        if(checkPengguna == null) {
            return roleRepository.save(new Role(null, name));
        }

        return checkPengguna;
    }
}
