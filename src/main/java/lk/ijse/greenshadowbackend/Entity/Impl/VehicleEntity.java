package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lk.ijse.greenshadowbackend.Entity.Status;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicle_code;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private StaffEntity staff;
}
