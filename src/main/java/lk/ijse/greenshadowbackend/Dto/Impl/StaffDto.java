package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.StaffStatus;
import lk.ijse.greenshadowbackend.Entity.Designation;
import lk.ijse.greenshadowbackend.Entity.Gender;
import lk.ijse.greenshadowbackend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto implements StaffStatus {
    private String id;
    private String firstName;
    private Designation designation;
    private Gender gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    private String email;
    private Role role;
    private List<VehicleDto> vehicles;
}
