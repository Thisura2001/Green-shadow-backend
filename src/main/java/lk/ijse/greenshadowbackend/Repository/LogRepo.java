package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Entity.Impl.FieldEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends JpaRepository<LogEntity,String> {
    @Query(value = "SELECT * FROM monitoring_log WHERE id = (SELECT id FROM monitoring_log ORDER BY CAST(SUBSTRING(id, 5) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    LogEntity findLastRowNative();
}
