package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.Entity.EquipmentType;
import lk.ijse.greenshadowbackend.Entity.Status;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String eqId;
    private String name;
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "id")
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "fieldId")
    private FieldEntity field;
}
