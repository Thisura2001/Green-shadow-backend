package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.VehicleDto;
import lk.ijse.greenshadowbackend.Dto.VehicleStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.StaffEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.VehicleEntity;
import lk.ijse.greenshadowbackend.Repository.StaffRepo;
import lk.ijse.greenshadowbackend.Repository.VehicleRepo;
import lk.ijse.greenshadowbackend.Service.VehicleService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffRepo staffRepo;
    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        int number = 0;
        VehicleEntity vehicle = vehicleRepo.findLastRowNative();
        if (vehicle != null){
            String[] parts = vehicle.getVehicle_code().split("-");
            number = Integer.parseInt(parts[1]);
        }
        vehicleDto.setVehicle_code("VEHICLE-"+(number+1));
        vehicle = mapping.toVehicleEntity(vehicleDto);
        vehicleRepo.save(vehicle);
    }

    @Override
    public VehicleStatus getVehicleById(String vehicleCode) {
        Optional<VehicleEntity> vehicleOptional = vehicleRepo.findById(vehicleCode);
        if (vehicleOptional.isPresent()) {
            VehicleEntity vehicleEntity = vehicleOptional.get();
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicle_code(vehicleEntity.getVehicle_code());
            vehicleDto.setLicensePlateNumber(vehicleEntity.getLicensePlateNumber());
            vehicleDto.setVehicleCategory(vehicleEntity.getVehicleCategory());
            vehicleDto.setFuelType(vehicleEntity.getFuelType());
            vehicleDto.setStatus(vehicleEntity.getStatus());
            vehicleDto.setStaff(vehicleEntity.getStaff().getId());

            return vehicleDto;
        }
        return new SelectedErrorStatusCode(2, "Vehicle not found");
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<VehicleEntity> vehicles = vehicleRepo.findAll();
        List<VehicleDto> vehicleDtos = new ArrayList<>();

        for (VehicleEntity vehicleEntity : vehicles) {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setVehicle_code(vehicleEntity.getVehicle_code());
            vehicleDto.setLicensePlateNumber(vehicleEntity.getLicensePlateNumber());
            vehicleDto.setVehicleCategory(vehicleEntity.getVehicleCategory());
            vehicleDto.setFuelType(vehicleEntity.getFuelType());
            vehicleDto.setStatus(vehicleEntity.getStatus());
            vehicleDto.setStaff(vehicleEntity.getStaff().getId()); // Assuming staff is associated correctly

            vehicleDtos.add(vehicleDto);
        }

        return vehicleDtos;
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDto vehicleDto) {
        Optional<VehicleEntity> vehicleOptional = vehicleRepo.findById(vehicleCode);
        if (!vehicleOptional.isPresent()) {
            throw new RuntimeException("Vehicle not found");
        } else {
            VehicleEntity vehicleEntity = vehicleOptional.get();
            StaffEntity staffEntity = staffRepo.findById(vehicleDto.getStaff())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + vehicleDto.getStaff()));

            vehicleEntity.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            vehicleEntity.setVehicleCategory(vehicleDto.getVehicleCategory());
            vehicleEntity.setFuelType(vehicleDto.getFuelType());
            vehicleEntity.setStatus(vehicleDto.getStatus());
            vehicleEntity.setStaff(staffEntity);

            vehicleRepo.save(vehicleEntity);
        }
    }


    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> byId = vehicleRepo.findById(vehicleCode);
        if (!byId.isPresent()){
            throw new RuntimeException("Vehicle Not Found");
        }
        vehicleRepo.deleteById(vehicleCode);
    }
}
