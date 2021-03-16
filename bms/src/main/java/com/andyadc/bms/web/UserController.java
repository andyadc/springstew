package com.andyadc.bms.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/me")
    public Object get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasAuthority('user:add')")
    @GetMapping("/api/user/add")
    public Object add() {
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('user:del')")
    @GetMapping("/api/user/del")
    public ResponseEntity<Object> del() {
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('user:update')")
    @GetMapping("/api/user/update")
    public Object update() {
        return ResponseEntity.ok().build();
    }
}
