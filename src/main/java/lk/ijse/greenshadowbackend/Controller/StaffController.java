package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Exception.StaffNotFoundException;
import lk.ijse.greenshadowbackend.Service.StaffService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveStaff(@RequestBody StaffDto staffDto){
        try {
            staffService.saveStaff(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getStaffById(@PathVariable ("id") String id){
        if (!Regex.staffIdMatcher(id)){
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
           if (!Regex.staffIdMatcher(id)){
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
}
