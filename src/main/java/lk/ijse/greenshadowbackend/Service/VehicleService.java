package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.Impl.VehicleDto;
import lk.ijse.greenshadowbackend.Dto.VehicleStatus;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);

    VehicleStatus getVehicleById(String vehicleCode);

    List<VehicleDto> getAllVehicles();
}
