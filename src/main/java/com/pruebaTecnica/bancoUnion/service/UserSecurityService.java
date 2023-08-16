package com.pruebaTecnica.bancoUnion.service;

import com.pruebaTecnica.bancoUnion.models.entity.UserEntity;
import com.pruebaTecnica.bancoUnion.models.entity.RoleEntity;
import com.pruebaTecnica.bancoUnion.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserSecurityService.class);
    private final IUserRepository iUserRepository;

    public UserSecurityService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = iUserRepository.findByUsername(username);

        String[] roles = userEntity.getRoles().stream().map(roleEntity -> roleEntity.getNombre()).toArray(String[]::new);
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles)
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }
}
