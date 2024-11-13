package lk.ijse.greenshadowbackend.Service;

import lk.ijse.greenshadowbackend.Dto.Impl.StaffDto;
import lk.ijse.greenshadowbackend.Dto.StaffStatus;

public interface StaffService {
    void saveStaff(StaffDto staffDto);

    StaffStatus getStaffById(String id);
}
