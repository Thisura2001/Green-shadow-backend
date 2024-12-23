package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.FieldStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;

import java.util.List;

public interface FieldService {
    void saveField(FieldDto fieldDto);

    FieldStatus getFieldById(String fieldId);

    List<FieldDto> getAllFields();

    void DeleteFields(String fieldId);

    void updateField(FieldDto fieldDto);
}
