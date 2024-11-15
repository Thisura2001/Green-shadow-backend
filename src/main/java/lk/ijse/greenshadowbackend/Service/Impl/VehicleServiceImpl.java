package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.VehicleDto;
import lk.ijse.greenshadowbackend.Dto.VehicleStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.VehicleEntity;
import lk.ijse.greenshadowbackend.Repository.VehicleRepo;
import lk.ijse.greenshadowbackend.Service.VehicleService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        vehicleDto.setVehicle_code(AppUtil.generateVehicleId());
        VehicleEntity save = vehicleRepo.save(mapping.toVehicleEntity(vehicleDto));
        if (save == null) {
            throw new RuntimeException("Failed to save vehicle");
        }
    }

    @Override
    public VehicleStatus getVehicleById(String vehicleCode) {
        if (vehicleRepo.existsById(vehicleCode)){
            VehicleEntity referenceById = vehicleRepo.getReferenceById(vehicleCode);
            return mapping.toVehicleDTO(referenceById);
        }
        return new SelectedErrorStatusCode(2,"Vehicle not found");
    }
}
