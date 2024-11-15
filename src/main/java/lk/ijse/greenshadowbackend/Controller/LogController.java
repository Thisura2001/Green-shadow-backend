package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.Dto.Impl.LogDto;
import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;
import lk.ijse.greenshadowbackend.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("api/v1/log")
public class LogController {
    @Autowired
    private LogService logService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveLog(@RequestPart ("log_date") String log_date,
                                       @RequestPart ("log_details") String log_details,
                                       @RequestPart ("observed_image") MultipartFile observed_image
                                       ){
        String base64 = "";
        try {
            byte[] bytes = observed_image.getBytes();
            base64 = Base64.getEncoder().encodeToString(bytes);

            LogDto logDto = new LogDto();

            logDto.setLog_date(log_date);
            logDto.setLog_details(log_details);
            logDto.setObserved_image(base64);
            logService.saveLog(logDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
