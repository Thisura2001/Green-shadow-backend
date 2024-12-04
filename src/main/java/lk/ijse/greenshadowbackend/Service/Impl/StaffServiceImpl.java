package lk.ijse.greenshadowbackend.Service.Impl;

import lk.ijse.greenshadowbackend.CustomStatusCode.SelectedErrorStatusCode;
import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;
import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.StaffEntity;
import lk.ijse.greenshadowbackend.Exception.StaffNotFoundException;
import lk.ijse.greenshadowbackend.Repository.FieldRepo;
import lk.ijse.greenshadowbackend.Repository.StaffRepo;
import lk.ijse.greenshadowbackend.Service.StaffService;
import lk.ijse.greenshadowbackend.Util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    private FieldRepo fieldRepo;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDto staffDto) {
        StaffEntity staff;
        staffDto.setId(generateNextId());

        staff = mapping.toStaffEntity(staffDto);
        staffRepo.save(staff);

    }

    @Override
    public String generateNextId() {
        int number = 0;
        StaffEntity staff = staffRepo.findLastRowNative();
        if (staff != null) {
            String[] parts = staff.getId().split("-");
            number = Integer.parseInt(parts[1]);
        }
        return "STAFF-" + (number + 1);
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
        System.out.println("satffid: "+id);
        Optional<StaffEntity> byId = staffRepo.findById(id);
        if (!byId.isPresent()){
            throw   new RuntimeException("staff not found");
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

    @Override
    public void assignFieldToStaff(String staffId, String fieldCode) {
        StaffEntity fetchedStaff = staffRepo.findById(staffId).orElseThrow(() -> new RuntimeException("Staff id not found"));
        FieldEntity fetchedField = fieldRepo.findById(fieldCode).orElseThrow(() -> new RuntimeException("Field id not found"));
        fetchedStaff.addField(fetchedField);
        staffRepo.save(fetchedStaff);
    }

    @Override
    public void removeFieldFromStaff(String staffId, String fieldCode) {
        StaffEntity fetchedStaff = staffRepo.findById(staffId).orElseThrow(() -> new RuntimeException("Staff id not found"));
        FieldEntity fetchedField = fieldRepo.findById(fieldCode).orElseThrow(() -> new RuntimeException("Field id not found"));
        fetchedStaff.removeField(fetchedField);
        staffRepo.save(fetchedStaff);
    }
}
