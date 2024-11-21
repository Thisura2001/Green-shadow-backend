package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.EquipmentDto;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Exception.EquipmentNotFoundException;
import lk.ijse.greenshadowbackend.Service.EquipmentService;
import lk.ijse.greenshadowbackend.Service.StaffService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveEquipment(@RequestBody EquipmentDto equipmentDto){
        try {
            equipmentService.saveEquipment(equipmentDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = ("/{eqId}"),produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getEquipmentById(@PathVariable ("eqId") String eqId){
        if (!Regex.idValidator(eqId).matches()){
            return new SelectedErrorStatusCode(1,"equipment not found");
        }
        return equipmentService.getEquipmentById(eqId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDto>getAllEquipment(){
        return equipmentService.getAllEquipment();
    }
    @DeleteMapping(value = "/{eqId}")
    public ResponseEntity<Void>DeleteEquipment(@PathVariable ("eqId") String eqId){
        try {
            if (!Regex.idValidator(eqId).matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.deletEquipment(eqId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{eqId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>updateEquipment(@PathVariable ("eqId") String eqId,@RequestBody EquipmentDto equipmentDto){
        try {
            if (!Regex.idValidator(eqId).matches()|| equipmentDto==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
                equipmentService.updateEquipment(eqId,equipmentDto);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
