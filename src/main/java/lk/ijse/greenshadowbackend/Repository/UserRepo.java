package lk.ijse.greenshadowbackend.Repository;

import lk.ijse.greenshadowbackend.Entity.Impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
}
