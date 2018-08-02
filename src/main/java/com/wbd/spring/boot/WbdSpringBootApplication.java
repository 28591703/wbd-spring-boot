package com.wbd.spring.boot;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * spring boot 项目的入口为  ***Application类中的 main方法
 * 该类必须声明注解为@SpringBootApplication，
 * 启动项目：利用SpringApplication.run(***Application.class,args) 方法
*  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年7月28日
 */
@RestController
@MapperScan(basePackages= {"com.spring.boot.ibatis.mapper"}) 
@SpringBootApplication
public class WbdSpringBootApplication {
	
	@Value("${wbd.user}")
	private String user;
	
	@Value("${wbd.password}")
	private String pwd;
	

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(WbdSpringBootApplication.class);
		//关闭banner
		//sa.setBannerMode(Banner.Mode.OFF);
		sa.run(args);
	
	}
	@RequestMapping("/index")
	String test() {
		return "user  is:"+user+"and   pwd is :"+pwd;
	}
	
	
	
	/****************HTTPS转向********************/
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			
			
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint sc = new SecurityConstraint();
				sc.setUserConstraint("confidential");
				SecurityCollection scs = new SecurityCollection();
				scs.addPattern("/*");
				sc.addCollection(scs);
				context.addConstraint(sc);
			}
			
			
		};
		
		tomcat.addAdditionalTomcatConnectors(httpConnecor());
		
		return tomcat;
	}
	private Connector httpConnecor() {
		
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setRedirectPort(9999);
		return connector;
		
	}
	
	
}
