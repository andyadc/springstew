package com.andyadc.bms.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/me")
    public Object get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasAuthority('user:add')")
    @GetMapping("/user/add")
    public ResponseEntity<Object> add() {
        return ResponseEntity.ok("Add user success");
    }

    @PreAuthorize("hasAuthority('user:del')")
    @GetMapping("/user/del")
    public ResponseEntity<Object> del() {
        return ResponseEntity.ok("Del user success");
    }

    @PreAuthorize("hasAuthority('user:update')")
    @GetMapping("/user/update")
    public ResponseEntity<Object> update() {
        return ResponseEntity.ok("Update user success");
    }
}
