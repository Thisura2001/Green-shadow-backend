package lk.ijse.greenshadowbackend.Dto.Impl;

import lk.ijse.greenshadowbackend.Dto.UserStatus;
import lk.ijse.greenshadowbackend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements UserStatus {
    private String user_id;
    private String email;
    private String password;
    private Role role;
}
