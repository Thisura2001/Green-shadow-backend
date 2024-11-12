package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.FieldStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;

public interface FieldService {
    void saveField(FieldDto fieldDto);

    FieldStatus getFieldById(String fieldId);
}
