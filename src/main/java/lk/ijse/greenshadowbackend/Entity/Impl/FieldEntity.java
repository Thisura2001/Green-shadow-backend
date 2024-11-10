package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldId;
    private String fieldName;
    private Point location;
    private Double extend;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImg1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImg2;
}
