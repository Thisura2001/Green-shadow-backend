package lk.ijse.greenshadowbackend.CustomStatus;

import lk.ijse.greenshadowbackend.Dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorStatus  implements UserStatus {
    private int statusCode;
    private String statusMessage;
}
