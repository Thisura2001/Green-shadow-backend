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
        fieldDto.setFieldId(AppUtil.generateFieldId());
        FieldEntity save = fieldRepo.save(mapping.toFieldEntity(fieldDto));
        if (save==null){
            throw new RuntimeException("Field Save Failed");
        }
    }

    @Override
    public FieldStatus getFieldById(String fieldId) {
        if (fieldRepo.existsById(fieldId)){
            FieldEntity referenceById = fieldRepo.getReferenceById(fieldId);
            return mapping.toFieldDTO(referenceById);
        }else {
            return new SelectedErrorStatusCode(2,"Field not found !!");
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        return mapping.toFieldDTOList(fieldRepo.findAll());
    }

    @Override
    public void DeleteFields(String fieldId) {
        Optional<FieldEntity> byId = fieldRepo.findById(fieldId);
        if (!byId.isPresent()){
            throw new FieldNotFoundException("Can't find Field");
        }else {
            fieldRepo.deleteById(fieldId);
        }
    }

    @Override
    public void updateField(FieldDto fieldDto) {
        Optional<FieldEntity> byId = fieldRepo.findById(fieldDto.getFieldId());
        if (!byId.isPresent()){
            throw new FieldNotFoundException("Can't find Field");
        }else {
           byId.get().setFieldName(fieldDto.getFieldName());
           byId.get().setLocation(fieldDto.getLocation());
           byId.get().setExtend(fieldDto.getExtend());
           byId.get().setFieldImg1(fieldDto.getFieldImg1());
           byId.get().setFieldImg2(fieldDto.getFieldImg2());
        }
    }
}
