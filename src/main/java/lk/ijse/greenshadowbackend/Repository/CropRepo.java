package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Entity.Impl.CropEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepo extends JpaRepository<CropEntity,String> {
    @Query(value = "SELECT * FROM crop WHERE crop_id = (SELECT crop_id FROM crop ORDER BY CAST(SUBSTRING(crop_id, 6) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    CropEntity findLastRowNative();
}
