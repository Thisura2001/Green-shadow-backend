package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto);

    EquipmentStatus getEquipmentById(String eqId);

    List<EquipmentDto> getAllEquipment();

    void deletEquipment(String eqId);

    void updateEquipment(String eqId, EquipmentDto equipmentDto);
}
