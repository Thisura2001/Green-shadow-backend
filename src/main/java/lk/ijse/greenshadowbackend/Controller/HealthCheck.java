package lk.ijse.greenshadowbackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthCheck")
public class HealthCheck {
    @GetMapping
    public String healthCheck(){
        return "Server is up and running";
    }
}
