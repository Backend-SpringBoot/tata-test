package tata.test.base.contenedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients(basePackages = "tata.test")
@SpringBootApplication(scanBasePackages = "tata.test")
@EnableJpaRepositories(basePackages = {"tata.test"})
@EntityScan(basePackages = {"tata.test"})
public class TataTestServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(TataTestServiceApplication.class, args);
  }
}
