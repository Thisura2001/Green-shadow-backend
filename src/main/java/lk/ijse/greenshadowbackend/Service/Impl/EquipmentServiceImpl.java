package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.Dto.Impl.EquipmentDto;
import lk.ijse.greenshadowbackend.Entity.Impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.Repository.EquipmentRepo;
import lk.ijse.greenshadowbackend.Service.EquipmentService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        equipmentDto.setEqId(AppUtil.generateEquipmentId());
        EquipmentEntity save = equipmentRepo.save(mapping.toEquipmentEntity(equipmentDto));
        if (save == null) {
            throw new RuntimeException("Failed to save equipment");
        }
    }

    @Override
    public EquipmentStatus getEquipmentById(String eqId) {
        if (equipmentRepo.existsById(eqId)){
            EquipmentEntity referenceById = equipmentRepo.getReferenceById(eqId);
            return mapping.toEquipmentDTO(referenceById);
        }
        return new SelectedErrorStatusCode(2,"Equipment not found");
    }

    @Override
    public List<EquipmentDto> getAllEquipment() {
        List<EquipmentEntity> all = equipmentRepo.findAll();
        return mapping.toEquipmentDTOList(all);
    }
}
