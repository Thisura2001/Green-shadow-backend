package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldId;
    private String fieldName;
    private String location;
    private String extend;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImg1;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImg2;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipmentEntity> equipment;

    @ManyToMany(mappedBy = "fields")
    private List<StaffEntity> allocated_staff;
}
