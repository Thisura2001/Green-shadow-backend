package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.CropStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.CropDto;

public interface CropService {
    void saveCrop(CropDto cropDto);

    CropStatus getCropById(String cropId);
}
