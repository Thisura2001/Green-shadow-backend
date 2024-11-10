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
@Table(name = "monitoring_log")
public class LogEntity implements SuperEntity {
    @Id
    private String id;
    private String log_date;
    private String log_details;
    @Column(columnDefinition = "LONGTEXT")
    private String observed_image;
    @OneToMany(mappedBy = "fieldId",cascade = CascadeType.ALL)
    private List<FieldEntity> fields;
    @OneToMany(mappedBy = "cropId",cascade = CascadeType.ALL)
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<StaffEntity> staff;
}
