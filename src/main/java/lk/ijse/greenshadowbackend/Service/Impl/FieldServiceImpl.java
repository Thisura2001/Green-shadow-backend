package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.FieldStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import lk.ijse.greenshadowbackend.Exception.CropNotFoundException;
import lk.ijse.greenshadowbackend.Exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.Repository.FieldRepo;
import lk.ijse.greenshadowbackend.Service.FieldService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDto fieldDto) {
       int number = 0;
       FieldEntity field = fieldRepo.findLastRowNative();
       if (field!=null){
           String[] parts = field.getFieldId().split("-");
           number = Integer.parseInt(parts[1]);
       }
       fieldDto.setFieldId("FIELD-"+(number+1));
       field = mapping.toFieldEntity(fieldDto);
       fieldRepo.save(field);
    }

    @Override
    public FieldStatus getFieldById(String fieldId) {
        if (fieldRepo.existsById(fieldId)) {
            FieldEntity fieldEntity = fieldRepo.getReferenceById(fieldId);
            FieldDto fieldDto = new FieldDto();
            fieldDto.setFieldId(fieldEntity.getFieldId());
            fieldDto.setFieldName(fieldEntity.getFieldName());
            fieldDto.setLocation(fieldEntity.getLocation());
            fieldDto.setExtend(fieldEntity.getExtend());
            fieldDto.setFieldImg1(fieldEntity.getFieldImg1());
            fieldDto.setFieldImg2(fieldEntity.getFieldImg2());
            return fieldDto;
        } else {
            return new SelectedErrorStatusCode(2, "Field not found !!");
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        List<FieldEntity> fieldEntities = fieldRepo.findAll();
        List<FieldDto> fieldDtos = new ArrayList<>();

        for (FieldEntity entity : fieldEntities) {
            FieldDto dto = new FieldDto();
            dto.setFieldId(entity.getFieldId());
            dto.setFieldName(entity.getFieldName());
            dto.setLocation(entity.getLocation());
            dto.setExtend(entity.getExtend());
            dto.setFieldImg1(entity.getFieldImg1());
            dto.setFieldImg2(entity.getFieldImg2());
            fieldDtos.add(dto);
        }

        return fieldDtos;
    }

    @Override
    public void DeleteFields(String fieldId) {
        Optional<FieldEntity> optionalField = fieldRepo.findById(fieldId);
        if (!optionalField.isPresent()) {
            throw new FieldNotFoundException("Can't find Field");
        } else {
            fieldRepo.deleteById(fieldId);
        }
    }

    @Override
    public void updateField(FieldDto fieldDto) {
        Optional<FieldEntity> optionalField = fieldRepo.findById(fieldDto.getFieldId());
        if (!optionalField.isPresent()) {
            throw new FieldNotFoundException("Can't find Field");
        } else {
            FieldEntity fieldEntity = optionalField.get();
            fieldEntity.setFieldName(fieldDto.getFieldName());
            fieldEntity.setLocation(fieldDto.getLocation());
            fieldEntity.setExtend(fieldDto.getExtend());
            fieldEntity.setFieldImg1(fieldDto.getFieldImg1());
            fieldEntity.setFieldImg2(fieldDto.getFieldImg2());
            fieldRepo.save(fieldEntity); // Save updated entity back to the repository
        }
    }

}
