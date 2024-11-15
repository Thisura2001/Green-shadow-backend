package lk.ijse.greenshadowbackend.Controller;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.VehicleDto;
import lk.ijse.greenshadowbackend.Dto.VehicleStatus;
import lk.ijse.greenshadowbackend.Exception.DataPersistException;
import lk.ijse.greenshadowbackend.Service.VehicleService;
import lk.ijse.greenshadowbackend.Util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveVehicle(@RequestBody VehicleDto vehicleDto){
        try {
            vehicleService.saveVehicle(vehicleDto);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{vehicle_code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getVehicleById(@PathVariable ("vehicle_code") String vehicle_code){
        if (!Regex.vehicleCodeMatcher(vehicle_code)){
            return new SelectedErrorStatusCode(1,"invalid vehicle code");
        }
        return vehicleService.getVehicleById(vehicle_code);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDto>getAllVehicle(){
        return vehicleService.getAllVehicles();
    }
}
