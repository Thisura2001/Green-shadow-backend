package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.Entity.Designation;
import lk.ijse.greenshadowbackend.Entity.Gender;
import lk.ijse.greenshadowbackend.Entity.Role;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String id;
    private String firstName;
    @Enumerated(EnumType.STRING)
    private Designation designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "staff",cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles;
    @ManyToMany
    @JoinTable(name = "Field_Staff_assignment",joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "fieldId"))
    private List<FieldEntity> fields;
    @OneToMany(mappedBy = "staff",cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicle;
}
