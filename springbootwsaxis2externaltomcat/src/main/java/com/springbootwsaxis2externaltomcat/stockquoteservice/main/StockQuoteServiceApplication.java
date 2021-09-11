package com.springbootwsaxis2externaltomcat.stockquoteservice.main;

import com.springbootwsaxis2externaltomcat.stockquoteservice.endpoints.StockQouteServiceEndpoint;
import com.springbootwsaxis2externaltomcat.stockquoteservice.repository.StockQuoteServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {
	StockQuoteServiceRepository.class,
	StockQouteServiceEndpoint.class
})
public class StockQuoteServiceApplication extends SpringBootServletInitializer {

	private static final String SERVICES_MAPPING = "/services/*";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StockQuoteServiceApplication.class);
	}

	@Bean
	public ServletRegistrationBean<AxisServlet> servletRegistrationBean() throws IOException {
		ServletRegistrationBean<AxisServlet> bean = new ServletRegistrationBean<AxisServlet>(new AxisServlet(), SERVICES_MAPPING);
		log.info("Loading AxisServlet bean on startup");
		bean.setLoadOnStartup(1);
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteServiceApplication.class, args);
	}

}
