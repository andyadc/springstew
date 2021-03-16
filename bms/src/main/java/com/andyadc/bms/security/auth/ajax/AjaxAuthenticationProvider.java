package com.andyadc.bms.security.auth.ajax;

import com.andyadc.bms.entity.User;
import com.andyadc.bms.security.UserService;
import com.andyadc.bms.security.data.DatabaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AjaxAuthenticationProvider(BCryptPasswordEncoder passwordEncoder, DatabaseUserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz));
    }
}
