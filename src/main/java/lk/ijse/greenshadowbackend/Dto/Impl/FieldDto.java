package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDto implements FieldStatus {
    private String fieldId;
    private String fieldName;
    private Point location;
    private Double extend;
    private String fieldImg1;
    private String fieldImg2;
    private List<CropDto> crops;
    private List<StaffDto> allocated_staff;
}
