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
        if (vehicleRepo.existsById(vehicleCode)){
            VehicleEntity referenceById = vehicleRepo.getReferenceById(vehicleCode);
            return mapping.toVehicleDTO(referenceById);
        }
        return new SelectedErrorStatusCode(2,"Vehicle not found");
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return mapping.toVehicleDTOList(vehicleRepo.findAll());
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDto vehicleDto) {
        Optional<VehicleEntity> byId = vehicleRepo.findById(vehicleCode);
        if (!byId.isPresent()){
            throw new RuntimeException("vehicle not found");
        }else {
            StaffEntity staffEntity = staffRepo.findById(vehicleDto.getStaff())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + vehicleDto.getStaff()));
            byId.get().setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            byId.get().setVehicleCategory(vehicleDto.getVehicleCategory());
            byId.get().setFuelType(vehicleDto.getFuelType());
            byId.get().setStatus(vehicleDto.getStatus());
            byId.get().setStaff(staffEntity);
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
