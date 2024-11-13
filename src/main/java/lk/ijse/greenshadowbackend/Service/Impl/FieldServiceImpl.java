package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.FieldStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import lk.ijse.greenshadowbackend.Repository.FieldRepo;
import lk.ijse.greenshadowbackend.Service.FieldService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
