package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//설정파일
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //시큐리티 기본설정으로 오버라이딩해서 내가 바꾸고 싶다(시큐리티 설정파일 활성화)
@Configuration //IoC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 파일을 맞추고, 메모리에 등록하고 바꿔치기(@EnableWebSecurity)하고
	
	//해시설정
	@Bean //리턴값을 ioc에 등록
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); //왜 싱글톤으로 관리하는 지?
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //기억 : form 태그 요청만 가능한 것을 비활성화 한다.
		
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()
		.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') ")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') ")
		.anyRequest().permitAll() // 이쪽으로 들어오면 인증이 필요해. 다른모든요청은 퍼밋올
		.and()
		.formLogin()
		.loginPage("/loginForm") //user로 주소요청했을 때 인증이 안됫으면 /loginForm 여기로 리다이렉션
		.loginProcessingUrl("/loginProc") //loginForm이 요청했을 때 loginProc 얘가 기능 수행한다.
		.defaultSuccessUrl("/") //그냥 loginForm에서 요청했을 때 어느 페이지로 갈지 
		.and()
		.logout()
		.logoutSuccessUrl("/logoutProc"); 
	}
}
