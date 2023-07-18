package com.jawbr.security.jwt;

import com.jawbr.dto.request.UserRequest;
import com.jawbr.entity.AuthRole;
import com.jawbr.entity.User;
import com.jawbr.exception.InvalidPasswordException;
import com.jawbr.exception.UserAccountDeactivatedException;
import com.jawbr.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class JwtUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public JwtUserService(UserRepository userService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        user.orElseThrow(() -> new UsernameNotFoundException("Invalid username!"));
        user.filter(User::isActive).orElseThrow(() ->
                new UserAccountDeactivatedException(String.format(
                        "The account '%s' is currently deactivated. Please reactivate your account to regain access.",
                        user.get().getUsername())));

        Collection<SimpleGrantedAuthority> authorities = MapRolesToAuthorities(user.get().getRoles());

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(),
                authorities);
    }

    public void verifyUserCredentials(UserRequest loginRequest) {
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
