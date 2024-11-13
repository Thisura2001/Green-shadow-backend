package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.StaffEntity;
import lk.ijse.greenshadowbackend.Exception.StaffNotFoundException;
import lk.ijse.greenshadowbackend.Repository.StaffRepo;
import lk.ijse.greenshadowbackend.Service.StaffService;
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

    @Override
    public List<StaffDto> getAllStaff() {
         return mapping.toStaffDTOList(staffRepo.findAll());
    }

    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> byId = staffRepo.findById(id);
        if (!byId.isPresent()){
            throw new RuntimeException("staff not found");
        }else {
            staffRepo.deleteById(id);
        }
    }

    @Override
    public void updateStaff(String id, StaffDto staffDto) {
        Optional<StaffEntity> byId = staffRepo.findById(id);
        if (!byId.isPresent()){
            throw new StaffNotFoundException("staff Not found");
        }else {
            byId.get().setFirstName(staffDto.getFirstName());
            byId.get().setDesignation(staffDto.getDesignation());
            byId.get().setGender(staffDto.getGender());
            byId.get().setJoined_date(staffDto.getJoined_date());
            byId.get().setDob(staffDto.getDob());
            byId.get().setAddress(staffDto.getAddress());
            byId.get().setContact_no(staffDto.getContact_no());
            byId.get().setEmail(staffDto.getEmail());
            byId.get().setRole(staffDto.getRole());
        }
    }
}
