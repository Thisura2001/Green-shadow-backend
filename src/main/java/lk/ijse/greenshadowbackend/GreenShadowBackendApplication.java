package lk.ijse.greenshadowbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
public class GreenShadowBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenShadowBackendApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
