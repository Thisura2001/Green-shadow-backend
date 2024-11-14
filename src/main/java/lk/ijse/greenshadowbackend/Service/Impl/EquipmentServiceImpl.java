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
import java.util.Optional;

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

    @Override
    public void deletEquipment(String eqId) {
        Optional<EquipmentEntity> byId = equipmentRepo.findById(eqId);
        if (!byId.isPresent()){
            throw new RuntimeException("can't find Equipment");
        }
        equipmentRepo.deleteById(eqId);
    }

    @Override
    public void updateEquipment(String eqId, EquipmentDto equipmentDto) {
        Optional<EquipmentEntity> byId = equipmentRepo.findById(eqId);
        if (!byId.isPresent()){
            throw new RuntimeException("can't find Equipment");
        }else {
            byId.get().setName(equipmentDto.getName());
            byId.get().setEquipmentType(equipmentDto.getEquipmentType());
        }
    }
}
