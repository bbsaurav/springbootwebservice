package com.springbootwsaxis2embeddedtomcat.stockquoteservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Axis2ServiceRegister {

  private File repo = new File(System.getProperty("java.io.tmpdir"), "axis2repo");
  private List<Axis2ServiceInfo> resources = new ArrayList<Axis2ServiceInfo>();

  @PostConstruct
	public void init() {
		log.info("Axis2ServiceRegister init called");
		if (!repo.exists()) {
			repo.mkdirs();
		}
		loadServices();
	}

  @PreDestroy
	public void cleanup() throws IOException {
		log.info("Axis2ServiceRegister cleanup called");
		if (repo.exists()) {
			FileUtils.deleteDirectory(repo);
		}
	}

  private void loadServices() {
		try {
			registerServiceXml("services.xml", "StockQuoteService");
      registerServiceWsdl("StockQuoteService.wsdl", "StockQuoteService");
		} catch (IOException e) {
			log.error("error when load service: {}", e);
		}
	}

  public String getRepoPath() {
    log.info("RepoPath {}", repo.getAbsolutePath());
		return repo.getAbsolutePath();
	}

  public void registerServiceXml(String fileName, String serviceName) throws IOException {
    Resource resource = new ClassPathResource(fileName);
		File serviceDir = new File(repo, "services/" + serviceName + "/META-INF");
		serviceDir.mkdirs();
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(serviceDir, fileName))) {
			IOUtils.copy(resource.getInputStream(), fileOutputStream);
    }

    Axis2ServiceInfo info = new Axis2ServiceInfo();
		info.setName(serviceName);
		info.setResource(resource);
		resources.add(info);
	}

  public void registerServiceWsdl(String fileName, String serviceName) throws IOException {
    Resource resource = new ClassPathResource(fileName);
		File serviceDir = new File(repo, "services/" + serviceName + "/META-INF");
		serviceDir.mkdirs();
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(serviceDir, fileName))) {
			IOUtils.copy(resource.getInputStream(), fileOutputStream);
    }

    Axis2ServiceInfo info = new Axis2ServiceInfo();
		info.setName(serviceName);
		info.setResource(resource);
		resources.add(info);
	}
}
