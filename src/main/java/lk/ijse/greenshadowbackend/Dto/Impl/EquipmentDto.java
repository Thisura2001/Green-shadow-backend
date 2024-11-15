package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.Entity.EquipmentType;
import lk.ijse.greenshadowbackend.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto implements EquipmentStatus {
    private String eqId;
    private String name;
    private EquipmentType equipmentType;
    private Status status;
    private String staff;
    private String field;
}
