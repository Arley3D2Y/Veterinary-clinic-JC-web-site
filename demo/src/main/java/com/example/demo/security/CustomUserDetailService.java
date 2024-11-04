package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Administrador;
import com.example.demo.model.Cliente;
import com.example.demo.model.Role;
import com.example.demo.model.UserEntity;
import com.example.demo.model.Veterinario;
import com.example.demo.repositorio.RoleRepository;
import com.example.demo.repositorio.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // UserDetails es la interfaz
    // User que es la implemnetación de la interfaz
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userBD = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username)            
        );

        UserDetails userDetails = new User(userBD.getUsername(), userBD.getPassword(), mapToGrantedAuthorities(userBD.getRoles()));

        return userDetails;
    }

    // UserEntity - UserDetails(User estandar que usa spring security)
    // Role que tenemos en la BD a GrantedAuthority (permisos)

    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

    public UserEntity ClienteToUser(Cliente cliente) {
        UserEntity user = new UserEntity();
        user.setUsername(cliente.getCedula());
        user.setPassword(passwordEncoder.encode("1234"));

        Role role = roleRepository.findByName("CLIENTE").get();
        user.setRoles(List.of(role));

        return user;
    }

    public UserEntity VeterinarioToUser(Veterinario veterinario) {
        UserEntity user = new UserEntity();
        user.setUsername(veterinario.getCorreo());
        user.setPassword(passwordEncoder.encode(veterinario.getPassword()));

        Role role = roleRepository.findByName("VETERINARIO").get();
        user.setRoles(List.of(role));

        return user;
    }

    public UserEntity AdminToUser(Administrador admin) {
        UserEntity user = new UserEntity();
        user.setUsername(admin.getUsuario());
        user.setPassword(passwordEncoder.encode(admin.getPassword()));

        Role role = roleRepository.findByName("ADMINISTRADOR").get();
        user.setRoles(List.of(role));

        return user;
    }

}
