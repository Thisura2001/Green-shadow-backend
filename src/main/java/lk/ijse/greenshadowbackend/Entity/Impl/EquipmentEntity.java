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
    @JoinColumn(name = "id")  // Foreign key to StaffEntity
    private StaffEntity staff;

    // Many-to-one relationship with FieldEntity, and ensure foreign key column is defined
    @ManyToOne
    @JoinColumn(name = "fieldId", foreignKey = @ForeignKey(name = "FK_field_equipment"))
    private FieldEntity field;
}
