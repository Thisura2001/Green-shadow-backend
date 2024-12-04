package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Exception.StaffNotFoundException;
import lk.ijse.greenshadowbackend.Service.FieldService;
import lk.ijse.greenshadowbackend.Service.StaffService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>>saveStaff(@RequestBody StaffDto staffDto) {
        try {
            String nextId = staffService.generateNextId();
            staffService.saveStaff(staffDto);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("staffId", nextId);
            return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getStaffById(@PathVariable ("id") String id){
        if (!Regex.idValidator(id).matches()){
            return new SelectedErrorStatusCode(1,"Staff not found");
        }
        return staffService.getStaffById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto>getAllStaff(){
        return staffService.getAllStaff();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>DeleteStaff(@PathVariable ("id") String id){
       try {
           if (!Regex.idValidator(id).matches()){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           staffService.deleteStaff(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }catch (StaffNotFoundException e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>updateStaff(@PathVariable String id,@RequestBody StaffDto staffDto){
        try {
            if (!Regex.idValidator(id).matches()|| staffDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(id,staffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{staffId}/assign/{fieldId}")
    public ResponseEntity<Void>assignFieldToStaff(@PathVariable String staffId,@PathVariable String fieldId){
        try {
            if (!Regex.idValidator(staffId).matches()|| !Regex.idValidator(fieldId).matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.assignFieldToStaff(staffId,fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{staffId}/remove/{fieldId}")
    public ResponseEntity<Void>removeFieldFromStaff(@PathVariable String staffId,@PathVariable String fieldId){
        try {
            if (!Regex.idValidator(staffId).matches()|| !Regex.idValidator(fieldId).matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.removeFieldFromStaff(staffId,fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
