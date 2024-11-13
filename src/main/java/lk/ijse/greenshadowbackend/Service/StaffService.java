package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto staffDto);

    StaffStatus getStaffById(String id);

    List<StaffDto> getAllStaff();
}
