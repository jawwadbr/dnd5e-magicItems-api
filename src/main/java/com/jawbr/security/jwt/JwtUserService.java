package com.jawbr.security.jwt;

import com.jawbr.dto.request.LoginRequest;
import com.jawbr.entity.AuthRole;
import com.jawbr.entity.User;
import com.jawbr.exception.InvalidPasswordException;
import com.jawbr.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserService implements UserDetailsService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public JwtUserService(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid username!");
        }

        Collection<SimpleGrantedAuthority> authorities = MapRolesToAuthorities(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void verifyUserCredentials(LoginRequest loginRequest) {
        UserDetails user = loadUserByUsername(loginRequest.username());

        boolean passwordIsEqual = passwordEncoder.matches(loginRequest.password(), user.getPassword());

        if(!passwordIsEqual) {
            throw new InvalidPasswordException("Invalid password!");
        }
    }

    private Collection<SimpleGrantedAuthority> MapRolesToAuthorities(Collection<AuthRole> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(AuthRole tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getRole());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}
