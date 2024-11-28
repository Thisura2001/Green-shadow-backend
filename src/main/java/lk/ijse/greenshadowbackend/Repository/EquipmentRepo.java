package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Entity.Impl.EquipmentEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepo extends JpaRepository<EquipmentEntity,String> {
    @Query(value = "SELECT * FROM equipment WHERE eq_id = (SELECT eq_id FROM equipment ORDER BY CAST(SUBSTRING(eq_id,11) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    EquipmentEntity findLastRowNative();
}
