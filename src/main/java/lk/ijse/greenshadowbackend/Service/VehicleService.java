package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.Impl.VehicleDto;
import lk.ijse.greenshadowbackend.Dto.VehicleStatus;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);

    VehicleStatus getVehicleById(String vehicleCode);
}
