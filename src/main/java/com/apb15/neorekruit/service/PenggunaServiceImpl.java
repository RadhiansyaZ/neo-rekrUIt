package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Pengguna;
import com.apb15.neorekruit.model.Role;
import com.apb15.neorekruit.repository.PenggunaRepository;
import com.apb15.neorekruit.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PenggunaServiceImpl implements PenggunaService, UserDetailsService {
    private final PenggunaRepository penggunaRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pengguna pengguna = penggunaRepository.findByEmail(email);
        if(pengguna == null) {
            throw new UsernameNotFoundException("Pengguna tidak ditemukan!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        pengguna.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(
                pengguna.getEmail(),
                pengguna.getPassword(),
                authorities
                );
    }

    @Override
    public Pengguna save(Pengguna pengguna) {
        var checkUser = penggunaRepository.findByEmail(pengguna.getEmail());
        if(checkUser != null) {
            throw new IllegalStateException("Pengguna already exist");
        }
        pengguna.setPassword(passwordEncoder.encode(pengguna.getPassword()));
        return penggunaRepository.save(pengguna);
    }

    @Override
    public Pengguna getPengguna(String email) {
        return penggunaRepository.findByEmail(email);
    }

    @Override
    public List<Pengguna> getAllPengguna() {
        return penggunaRepository.findAll();
    }

}
