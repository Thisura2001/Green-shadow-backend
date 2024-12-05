package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.LogDto;
import lk.ijse.greenshadowbackend.Dto.LogStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Exception.LogNotFoundException;
import lk.ijse.greenshadowbackend.Service.LogService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/v1/log")
@CrossOrigin
public class LogController {
    @Autowired
    private LogService logService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SCIENTIST')")
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
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public LogStatus getLogById(@PathVariable ("id") String id){
        if (!Regex.idValidator(id).matches()){
            return new SelectedErrorStatusCode(1,"Log not found");
        }
        return logService.getLogById(id);
    }
    @GetMapping
    public List<LogDto> getAllLog(){
        return logService.getAllLog();
    }
    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SCIENTIST')")
    public ResponseEntity<Void>updateLog(@RequestPart ("log_date") String log_date,
                                         @RequestPart ("log_details") String log_details,
                                         @RequestPart ("observed_image") MultipartFile observed_image,
                                         @PathVariable("id") String id
    ){
        String base64 = "";
        try {
            byte[] bytes = observed_image.getBytes();
            base64 = Base64.getEncoder().encodeToString(bytes);

            LogDto logDto = new LogDto();

            logDto.setId(id);
            logDto.setLog_date(log_date);
            logDto.setLog_details(log_details);
            logDto.setObserved_image(base64);
            logService.updateLog(id,logDto);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SCIENTIST')")
    public ResponseEntity<Void>deleteLog(@PathVariable ("id") String id){
        try {
            if (!Regex.idValidator(id).matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.deleteLog(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
