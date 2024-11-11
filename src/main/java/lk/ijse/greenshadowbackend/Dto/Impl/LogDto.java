package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.LogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDto implements LogStatus {
    private String id;
    private String log_date;
    private String log_details;
    private String observed_image;
    private List<FieldDto> fields;
    private List<CropDto> crops;
    private List<StaffDto> staff;
}

