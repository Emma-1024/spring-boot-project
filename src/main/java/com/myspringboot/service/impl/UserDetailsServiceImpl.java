/*
 * Copyright (C)2023, emma Wu
 * All rights reserved.
 */
package com.myspringboot.service.impl;

import com.myspringboot.mapper.MenuMapper;
import com.myspringboot.mapper.UserMapper;
import com.myspringboot.model.User;
import com.myspringboot.vo.LoginUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Query user information
        User user = userMapper.getByName(username);
        // No user is queried, throw exception
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("The user name or password is incorrect");
        }
        // TODO Query current user's Authorization information
        List<String> list = menuMapper.getPermsByUserId(user.getId());

        // Encapsulate the data into UserDetails and return
        return new LoginUser(user, list);
    }
}
