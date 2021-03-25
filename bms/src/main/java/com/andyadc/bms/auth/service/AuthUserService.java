package com.andyadc.bms.auth.service;

import com.andyadc.bms.auth.dto.AuthUserDTO;
import com.andyadc.bms.auth.entity.AuthMenu;
import com.andyadc.bms.auth.entity.AuthUser;
import com.andyadc.bms.auth.mapper.AuthMapper;
import com.andyadc.bms.auth.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthUserService {

    private AuthMapper authMapper;
    private AuthUserMapper userMapper;

    public AuthUserDTO findByUsername(String username) {
        AuthUser authUser = userMapper.findByUsername(username);
        if (authUser == null) {
            return null;
        }

        AuthUserDTO dto = new AuthUserDTO();
        List<AuthMenu> menus = authMapper.selectMenuByUserId(authUser.getId());
        List<String> permissionList = menus.stream().map(AuthMenu::getPermission).collect(Collectors.toList());
        dto.setId(authUser.getId());
        dto.setUsername(username);
        dto.setPassword(authUser.getPassword());
        dto.setAuthorities(permissionList);
        return dto;
    }

    @Autowired
    public void setAuthMapper(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Autowired
    public void setUserMapper(AuthUserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
