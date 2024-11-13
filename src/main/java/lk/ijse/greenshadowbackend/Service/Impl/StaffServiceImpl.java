package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.StaffEntity;
import lk.ijse.greenshadowbackend.Repository.StaffRepo;
import lk.ijse.greenshadowbackend.Service.StaffService;
import lk.ijse.greenshadowbackend.Util.AppUtil;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDto staffDto) {
        staffDto.setId(AppUtil.generateStaffId());
        StaffEntity save = staffRepo.save(mapping.toStaffEntity(staffDto));
        if (save == null) {
            throw new RuntimeException("Failed to save staff");
        }
    }

    @Override
    public StaffStatus getStaffById(String id) {
        if (staffRepo.existsById(id)){
            StaffEntity referenceById = staffRepo.getReferenceById(id);
            return mapping.toStaffDTO(referenceById);
        }
        return new SelectedErrorStatusCode(2,"StaffNotFound");
    }
}
