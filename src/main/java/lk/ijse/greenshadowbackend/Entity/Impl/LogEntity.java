package lk.ijse.greenshadowbackend.Entity.Impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.greenshadowbackend.Entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
