package tata.test.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(
    info =
        @Info(
            title = "API Gateway",
            version = "1.0",
            description = "Documentation API Gateway v1.0"))
public class TestTataGatewayServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(TestTataGatewayServerApplication.class, args);
  }
}
