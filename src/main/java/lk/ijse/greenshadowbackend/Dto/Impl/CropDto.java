package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDto implements CropStatus {
    private String cropId;
    private String commonName;
    private String scientificName;
    private String cropImg;
    private String category;
    private String season;
    private FieldDto fieldDto;
}
