package com.springbootwsjaxb.stockquoteservice.main;

import com.springbootwsjaxb.stockquoteservice.config.WebServiceConfig;
import com.springbootwsjaxb.stockquoteservice.endpoints.StockQuoteServiceEndpoint;
import com.springbootwsjaxb.stockquoteservice.repository.StockQuoteServiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
	WebServiceConfig.class,
	StockQuoteServiceRepository.class,
	StockQuoteServiceEndpoint.class
})
public class StockQuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteServiceApplication.class, args);
	}

}
