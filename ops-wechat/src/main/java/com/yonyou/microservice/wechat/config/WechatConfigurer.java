package com.yonyou.microservice.wechat.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yonyou.microservice.wechat.interceptor.OpenIdInterceptor;
/**
 * 
 * @author Richard
 *
 */
@Configuration
public class WechatConfigurer extends WebMvcConfigurerAdapter{

	@Bean
	@LoadBalanced
	public  RestTemplate restTemplate(){
		 return new RestTemplate(); 
	}
	 
	@Bean
    public OpenIdInterceptor componentAuthInterceptor() {
         return new OpenIdInterceptor();
    }
	
	
	
	
	
//	/**
//    *
//    * @author LiuJun
//    * @date 2016年12月7日
//    * @param registry
//    * (non-Javadoc)
//    * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
//    */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/views/askingPrice").setViewName("/views/askingPrice/askingPrice"); 
//    }





//    /**
//    *
//    * @author LiuJun
//    * @date 2016年12月7日
//    * @param registry
//    * (non-Javadoc)
//    * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
//    */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/views/**");
//        registry.addResourceHandler("/js/**");
//        registry.addResourceHandler("/components/**");
//        registry.addResourceHandler("/images/**");
//    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
		InterceptorRegistration ir=registry.addInterceptor(componentAuthInterceptor());
		ir.addPathPatterns("/**");
//        ir.excludePathPatterns("/wechat/api/authorization/**");
//        ir.excludePathPatterns("/wechat/api/dealer/msg/**");
//        ir.excludePathPatterns("/wechat/api/attention/**");
        
        super.addInterceptors(registry);
    }
}
