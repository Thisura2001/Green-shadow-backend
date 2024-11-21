package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Entity.Impl.LogEntity;
import lk.ijse.greenshadowbackend.Entity.Impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepository<StaffEntity,String> {
    @Query(value = "SELECT * FROM staff WHERE id = (SELECT id FROM staff ORDER BY CAST(SUBSTRING(id, 7) AS UNSIGNED) DESC LIMIT 1);", nativeQuery = true)
    StaffEntity findLastRowNative();
}
