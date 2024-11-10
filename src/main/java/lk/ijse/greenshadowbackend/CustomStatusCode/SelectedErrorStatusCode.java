package lk.ijse.greenshadowbackend.CustomStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatusCode {
    private int statusCode;
    private String statusMessage;
}
