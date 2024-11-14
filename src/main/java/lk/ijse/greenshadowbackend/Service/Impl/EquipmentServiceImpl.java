package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.Dto.Impl.EquipmentDto;
import lk.ijse.greenshadowbackend.Entity.Impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.Repository.EquipmentRepo;
import lk.ijse.greenshadowbackend.Service.EquipmentService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
