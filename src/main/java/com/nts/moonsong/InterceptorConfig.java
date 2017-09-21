/**
 * 
 */
package com.nts.moonsong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nts.moonsong.auth.interceptor.AuthCheckInterceptor;

/**
 * @author Naver 송주용
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Autowired
	AuthCheckInterceptor authCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(authCheckInterceptor)
			.addPathPatterns("/**");
	}
}
