package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Crop")
public class CropEntity implements SuperEntity {
    @Id
    private String cropId;
    private String commonName;
    private String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImg;
    private String category;
    private String season;
//    @ManyToOne
//    @JoinColumn(name = "fieldId")
//    private FieldEntity field;
}
