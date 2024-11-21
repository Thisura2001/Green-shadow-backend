package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Entity.Impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleEntity,String> {
    @Query(value = "SELECT * FROM vehicle WHERE vehicle_id = (SELECT vehicle_id FROM vehicle ORDER BY CAST(SUBSTRING(vehicle_id, 7) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    VehicleEntity findLastRowNative();
}
