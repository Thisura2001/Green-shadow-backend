package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Dto.Impl.FieldDto;
import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepo extends JpaRepository<FieldEntity,String> {
    @Query(value = "SELECT * FROM field WHERE field_id = (SELECT field_id FROM field ORDER BY CAST(SUBSTRING(field_id, 7) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    FieldEntity findLastRowNative();
}
