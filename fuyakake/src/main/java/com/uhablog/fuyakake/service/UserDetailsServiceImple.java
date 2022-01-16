package com.uhablog.fuyakake.service;

import java.util.ArrayList;
import java.util.List;

import com.uhablog.fuyakake.entity.UserMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private LoginService service;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // ユーザー情報取得
        UserMaster loginUser  = service.getLoginUser(username);

        // ユーザーが存在しない場合
        if(loginUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        // 権限List作成
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        // UserDetails 作成
        UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(), loginUser.getPassword(), authorities);

        return userDetails;
    }
}
