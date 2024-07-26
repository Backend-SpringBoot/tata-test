package tata.test.discovery.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TataTestDiscoveryServerApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(TataTestDiscoveryServerApplication.class).run(args);
  }
}
