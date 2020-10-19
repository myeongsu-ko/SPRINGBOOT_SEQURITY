package com.cos.security.config.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

//princpal:접근주체(인증주체)
@Service
public class PrincipalDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepositoty;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 요청됨");
		
		User user = userRepositoty.findByUsername(username);
		if(user==null) {
			return null;
		}else {
			return new PrincipalDetails(user);
		}
	}

}
