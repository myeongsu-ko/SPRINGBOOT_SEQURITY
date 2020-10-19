package com.cos.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security.config.auth.PrincipalDetails;
import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	// username, password, email
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		//비밀번호 암호화
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		user.setRole("ROLE_USER");
		userRepository.save(user);
		return "index";
	}
	
	
	//세션만 있으면 출입가능
	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalDetails princiql) {
		return "user 페이지 : "+princiql.getUser().getEmail();
	}
	
	//세션만 있으면 출입가능
	@GetMapping("/manager")
	@ResponseBody
	public String manager(@AuthenticationPrincipal PrincipalDetails princiql) {
		return "manager 페이지 : "+princiql.getUser().getEmail();
	}
	
	//세션만 있으면 출입가능
	@GetMapping("/admin")
	@ResponseBody
	public String admin(@AuthenticationPrincipal PrincipalDetails princiql) {
		return "admin 페이지 : "+princiql.getUser().getEmail();
	}
}
