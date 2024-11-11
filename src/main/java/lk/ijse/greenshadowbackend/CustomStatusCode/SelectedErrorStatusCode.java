package lk.ijse.greenshadowbackend.CustomStatusCode;

import lk.ijse.greenshadowbackend.Dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatusCode implements CropStatus, FieldStatus, EquipmentStatus, StaffStatus, VehicleStatus
,LogStatus
{
    private int statusCode;
    private String statusMessage;
}
