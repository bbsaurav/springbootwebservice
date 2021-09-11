package com.springbootwsaxis2embeddedtomcat.stockquoteservice.main;

import com.springbootwsaxis2embeddedtomcat.stockquoteservice.config.Axis2WebAppInitializer;
import com.springbootwsaxis2embeddedtomcat.stockquoteservice.endpoints.StockQouteServiceEndpoint;
import com.springbootwsaxis2embeddedtomcat.stockquoteservice.repository.StockQuoteServiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
	Axis2WebAppInitializer.class,
	StockQuoteServiceRepository.class,
	StockQouteServiceEndpoint.class
})
public class StockQuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteServiceApplication.class, args);
	}

}
