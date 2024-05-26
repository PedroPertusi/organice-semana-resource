package organice.semana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
    "organice.lembrete"
})
@EnableDiscoveryClient
@SpringBootApplication
public class SemanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemanaApplication.class, args);
    }
    
}
