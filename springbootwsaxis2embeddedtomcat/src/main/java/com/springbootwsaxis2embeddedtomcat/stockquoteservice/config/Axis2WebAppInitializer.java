package com.springbootwsaxis2embeddedtomcat.stockquoteservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis2.deployment.WarBasedAxisConfigurator;
import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import java.util.Set;

@Configuration
@PropertySource("classpath:application.properties")
@Slf4j
public class Axis2WebAppInitializer implements ServletContextInitializer {

  private static final String SERVICES_MAPPING = "/services/*";
  @Autowired
	Axis2ServiceRegister axis2ServiceRegister;

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    log.info("Axis2WebAppInitializer Startup");
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("AxisServlet", new AxisServlet());
    dispatcher.setLoadOnStartup(1);
    dispatcher.setInitParameter(WarBasedAxisConfigurator.PARAM_AXIS2_REPOSITORY_PATH, axis2ServiceRegister.getRepoPath());
    Set<String> mappingConflicts = dispatcher.addMapping(SERVICES_MAPPING);
    if (!mappingConflicts.isEmpty()) { 
      for (String s : mappingConflicts) { 
          log.error("Mapping conflict: {}", s); 
      } 
      throw new ServletException("'AxisServlet' could not be mapped to '" + SERVICES_MAPPING + "'"); 
  }
  }

}