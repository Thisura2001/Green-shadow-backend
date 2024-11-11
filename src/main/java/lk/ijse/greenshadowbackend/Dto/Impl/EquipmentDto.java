package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.Entity.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto implements EquipmentStatus {
    private String id;
    private String name;
    private EquipmentType equipmentType;
    private StaffDto assigned_staff;
    private FieldDto assigned_field;
}
